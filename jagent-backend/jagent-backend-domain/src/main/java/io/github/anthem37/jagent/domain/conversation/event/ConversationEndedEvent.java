package io.github.anthem37.jagent.domain.conversation.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会话结束事件
 */
@Getter
@AllArgsConstructor
public class ConversationEndedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "ConversationEnded";

    /**
     * 会话ID
     */
    private final String conversationId;
}
