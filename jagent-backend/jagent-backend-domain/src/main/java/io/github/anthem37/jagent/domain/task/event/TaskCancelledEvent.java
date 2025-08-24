package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务取消事件
 */
@Getter
@AllArgsConstructor
public class TaskCancelledEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskCancelled";

    /**
     * 任务ID
     */
    private final String taskId;
}
