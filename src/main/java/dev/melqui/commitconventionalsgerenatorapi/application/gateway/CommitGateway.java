package dev.melqui.commitconventionalsgerenatorapi.application.gateway;

import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.Commit;
import io.quarkus.arc.DefaultBean;
import io.smallrye.mutiny.Uni;

import java.util.List;

@DefaultBean
public interface CommitGateway {
    Uni<List<Commit>> listAllCommits();
    Uni<Commit> persistCommit(Commit commit);

    Uni<Commit> getCommit(String id);

    void deleteCommit(String commitId);
}
