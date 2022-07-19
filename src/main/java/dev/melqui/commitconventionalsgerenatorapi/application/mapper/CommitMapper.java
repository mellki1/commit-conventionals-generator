package dev.melqui.commitconventionalsgerenatorapi.application.mapper;

import dev.melqui.commitconventionalsgerenatorapi.application.dto.CommitDto;
import dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo.Commit;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi", builder = @Builder(disableBuilder = true))
public interface CommitMapper {

    @Mapping(source = "type", target = "type")
    @Mapping(source = "sprintNumber", target = "sprintNumber")
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "message", target = "message")
    CommitDto toDto(Commit commit);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "sprintNumber", target = "sprintNumber")
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "message", target = "message")
    Commit toEntity(CommitDto commitDTO);

    List<CommitDto> toDtoList(List<Commit> commits);

}
