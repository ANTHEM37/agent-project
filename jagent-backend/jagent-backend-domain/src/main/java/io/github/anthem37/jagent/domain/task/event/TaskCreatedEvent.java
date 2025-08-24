package io.github.anthem37.jagent.domain.task.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务创建事件
 */
@Getter
@AllArgsConstructor
public class TaskCreatedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "TaskCreated";

    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 任务标题
     */
    private final String title;

    /**
     * 用户输入
     */
    private final String userInput;

}
