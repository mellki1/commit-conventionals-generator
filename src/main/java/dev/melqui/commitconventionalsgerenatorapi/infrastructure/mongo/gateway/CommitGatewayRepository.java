package dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.gateway;

import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.Commit;
import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.DynamoService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommitGatewayRepository extends DynamoService {

    @Inject
    DynamoDbAsyncClient dynamoDbClient;

    public Uni<List<Commit>> listAllCommits() {
        return Multi.createFrom()
                .completionStage(
                        dynamoDbClient
                                .scan(scanRequest())
                )
                .onItem()
                .transform(ScanResponse::items)
                .map(item -> item.stream().map(Commit::from).collect(Collectors.toList()))
                .toUni();
    }

    public Uni<Commit> getRequest(String id) {
        return Multi.createFrom()
                .completionStage(
                        dynamoDbClient
                                .getItem(GetItemRequest.builder()
                                        .key(Map.of("id", AttributeValue.builder().s(id).build()))
                                        .tableName(getTableName())
                                        .build())
                )
                .onItem()
                .transform(GetItemResponse::item)
                .map(Commit::from)
                .toUni();
    }

    public Uni<Commit> persistCommit(Commit commit) {
        commit.setId(UUID.randomUUID().toString());
        var putRequest = putRequest(commit);
        return Multi.createFrom()
                .completionStage(dynamoDbClient.putItem(putRequest))
                .map(putItemResponse ->
                        getRequest(commit.getId())
                                .await()
                                .asOptional()
                                .atMost(Duration.ofSeconds(1))
                                .orElseThrow()
                )
                .toUni();
    }
}
