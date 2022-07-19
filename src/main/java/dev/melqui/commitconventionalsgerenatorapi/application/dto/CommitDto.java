package dev.melqui.commitconventionalsgerenatorapi.application.dto;

import dev.melqui.commitconventionalsgerenatorapi.application.type.CommitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommitDto {
    private String id;
    private CommitType type;
    private Integer sprintNumber;
    private String taskId;
    private String message;
}
