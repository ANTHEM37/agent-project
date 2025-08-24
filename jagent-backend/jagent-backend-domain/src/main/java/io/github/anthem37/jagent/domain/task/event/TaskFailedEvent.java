package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务失败事件
 */
@Getter
@AllArgsConstructor
public class TaskFailedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskFailed";

    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 错误信息
     */
    private final String errorMessage;

}
