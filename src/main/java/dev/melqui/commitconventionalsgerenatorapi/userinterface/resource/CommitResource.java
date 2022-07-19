package dev.melqui.commitconventionalsgerenatorapi.userinterface.resource;

import dev.melqui.commitconventionalsgerenatorapi.application.dto.CommitDto;
import dev.melqui.commitconventionalsgerenatorapi.application.usecase.CommitUseCase;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/commit")
@JBossLog
public class CommitResource {

    @Inject
    CommitUseCase commitUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<CommitDto>> listAll() {
        return commitUseCase.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<CommitDto>  persist(CommitDto commitDTO) {
        return commitUseCase.persist(commitDTO);
    }
}