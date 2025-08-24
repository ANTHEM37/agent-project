package io.github.anthem37.jagent.domain.conversation.model;

import io.github.anthem37.ddd.domain.model.AbstractEntity;
import io.github.anthem37.jagent.domain.conversation.valueobject.MessageRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 会话消息实体
 */
@Getter
public class ConversationMessage extends AbstractEntity<String> {

    private final String content;
    private final MessageRoleEnum role;
    private final LocalDateTime timestamp;
    @Setter
    private String metadata;

    // 私有构造函数
    private ConversationMessage(String content, MessageRoleEnum role) {
        super(UUID.randomUUID().toString());
        this.content = content;
        this.role = role;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 创建用户消息
     */
    public static ConversationMessage createUserMessage(String content) {
        return new ConversationMessage(content, MessageRoleEnum.USER);
    }

    /**
     * 创建助手消息
     */
    public static ConversationMessage createAssistantMessage(String content) {
        return new ConversationMessage(content, MessageRoleEnum.ASSISTANT);
    }

    /**
     * 创建系统消息
     */
    public static ConversationMessage createSystemMessage(String content) {
        return new ConversationMessage(content, MessageRoleEnum.SYSTEM);
    }

}