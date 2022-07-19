package dev.melqui.commitconventionalsgerenatorapi.infrastructure.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
    private String id;
    private String type;
    private Integer sprintNumber;
    private String taskId;
    private String message;

    public static Commit from(Map<String, AttributeValue> item) {
        Commit commit = new Commit();
        if (item != null && !item.isEmpty()) {
            commit.setId(item.get("id").s());
            commit.setType(item.get("type").s());
            commit.setSprintNumber(item.get("sprint_number") != null ? Integer.parseInt(item.get("sprint_number").n()) : null);
            commit.setTaskId(item.get("task_id").s());
            commit.setMessage(item.get("message").s());
        }
        return commit;
    }
}
