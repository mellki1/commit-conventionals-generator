package dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class DynamoService {

    private static final String TABLE_NAME = "commit";
    private static final String KEY = "id";
    private static final String[] COMMIT_COLUMNS = {"id", "type", "sprint_number", "task_id", "message"};

    public String[] getAttributesToGet() {
        return COMMIT_COLUMNS;
    }

    public String getKey() {
        return KEY;
    }
    public String getTableName() {
        return TABLE_NAME;
    }

    public ScanRequest scanRequest() {
        return ScanRequest.builder()
                .tableName(getTableName())
                .attributesToGet(getAttributesToGet())
                .limit(5)
                .build();
    }


    protected PutItemRequest putRequest(Commit commit) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(COMMIT_COLUMNS[0], AttributeValue.builder().s(commit.getId()).build());
        item.put(COMMIT_COLUMNS[1], AttributeValue.builder().s(commit.getType()).build());
        item.put(COMMIT_COLUMNS[2], AttributeValue.builder().n(commit.getSprintNumber().toString()).build());
        item.put(COMMIT_COLUMNS[3], AttributeValue.builder().s(commit.getTaskId()).build());
        item.put(COMMIT_COLUMNS[4], AttributeValue.builder().s(commit.getMessage()).build());
        return PutItemRequest.builder().tableName(getTableName()).item(item).build();
    }

}
