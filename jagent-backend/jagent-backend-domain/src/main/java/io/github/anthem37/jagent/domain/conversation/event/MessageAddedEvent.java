package io.github.anthem37.jagent.domain.conversation.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.conversation.model.ConversationMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息添加事件
 */
@Getter
@AllArgsConstructor
public class MessageAddedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "MessageAdded";

    /**
     * 会话ID
     */
    private final String conversationId;

    /**
     * 消息
     */
    private final ConversationMessage message;
}

