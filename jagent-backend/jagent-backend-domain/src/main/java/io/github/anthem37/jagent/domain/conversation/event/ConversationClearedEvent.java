package io.github.anthem37.jagent.domain.conversation.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会话清除事件
 */
@Getter
@AllArgsConstructor
public class ConversationClearedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "ConversationCleared";

    /**
     * 会话ID
     */
    private final String conversationId;
}
