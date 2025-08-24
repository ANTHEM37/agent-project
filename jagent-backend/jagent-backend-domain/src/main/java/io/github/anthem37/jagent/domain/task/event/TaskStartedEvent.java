package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务开始事件
 */
@Getter
@AllArgsConstructor
public class TaskStartedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskStarted";

    /**
     * 任务ID
     */
    private final String taskId;

}
