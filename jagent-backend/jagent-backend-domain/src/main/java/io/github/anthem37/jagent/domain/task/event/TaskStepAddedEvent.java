package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.task.model.TaskStep;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务步骤添加事件
 */
@Getter
@AllArgsConstructor
public class TaskStepAddedEvent implements IDomainEvent {
    /**
     * 事件类型
     */
    private final String eventType = "TaskStepAdded";

    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 任务步骤
     */
    private final TaskStep step;

}
