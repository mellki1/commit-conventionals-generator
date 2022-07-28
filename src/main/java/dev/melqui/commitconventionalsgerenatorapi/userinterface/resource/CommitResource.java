package dev.melqui.commitconventionalsgerenatorapi.userinterface.resource;

import dev.melqui.commitconventionalsgerenatorapi.application.dto.CommitDto;
import dev.melqui.commitconventionalsgerenatorapi.application.usecase.CommitUseCase;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/commit")
@JBossLog
public class CommitResource {

    @Inject
    CommitUseCase commitUseCase;

    @GET
    public Uni<List<CommitDto>> listAll() {
        return commitUseCase.listAll();
    }

    @GET
    public Uni<CommitDto> getCommit(String commitId) {
        return commitUseCase.getCommit(commitId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<CommitDto> persist(CommitDto commitDTO) {
        return commitUseCase.persist(commitDTO);
    }

    @DELETE
    @Path("/{commitId}")
    public void delete(String commitId) {
        commitUseCase.delete(commitId);
    }
}