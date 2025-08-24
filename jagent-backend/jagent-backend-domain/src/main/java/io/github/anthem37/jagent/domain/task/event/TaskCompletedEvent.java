package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务完成事件
 */
@Getter
@AllArgsConstructor
public class TaskCompletedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskCompleted";

    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 任务结果
     */
    private final String result;
}
