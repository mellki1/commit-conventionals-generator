package dev.melqui.commitconventionalsgerenatorapi.application.gateway;

import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.Commit;
import io.quarkus.arc.DefaultBean;

import java.util.List;

@DefaultBean
public interface CommitGateway {
    List<Commit> listAllCommits();
    Commit persistCommit(Commit commit);
}
