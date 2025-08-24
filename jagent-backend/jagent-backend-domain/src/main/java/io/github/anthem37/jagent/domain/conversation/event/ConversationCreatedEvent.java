package io.github.anthem37.jagent.domain.conversation.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会话创建事件
 */
@Getter
@AllArgsConstructor
public class ConversationCreatedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "ConversationCreated";

    /**
     * 会话ID
     */
    private final String conversationId;

    /**
     * 会话ID
     */
    private final String sessionId;
}
