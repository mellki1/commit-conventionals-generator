package dev.melqui.commitconventionalsgerenatorapi.application.usecase;

import dev.melqui.commitconventionalsgerenatorapi.application.dto.CommitDto;
import dev.melqui.commitconventionalsgerenatorapi.application.mapper.CommitMapper;
import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.gateway.CommitGatewayRepository;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@JBossLog
public class CommitUseCase {

    @Inject
    CommitGatewayRepository commitGateway;

    @Inject
    CommitMapper commitMapper;

    public Uni<List<CommitDto>> listAll() {
        return commitGateway.listAllCommits()
                .map(commits -> commits.stream().map(commitMapper::toDto).collect(Collectors.toList()));
    }

    public Uni<CommitDto> persist(CommitDto commitDTO) {
        return commitGateway.persistCommit(commitMapper.toEntity(commitDTO))
                .map(commitMapper::toDto);
    }
}
