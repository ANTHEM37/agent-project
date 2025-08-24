package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务暂停事件
 */
@Getter
@AllArgsConstructor
public class TaskPausedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskPaused";

    /**
     * 任务ID
     */
    private final String taskId;

}
