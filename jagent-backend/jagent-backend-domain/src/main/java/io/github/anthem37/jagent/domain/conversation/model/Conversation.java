package io.github.anthem37.jagent.domain.conversation.model;

import io.github.anthem37.ddd.domain.model.AbstractAggregateRoot;
import io.github.anthem37.jagent.domain.conversation.event.ConversationClearedEvent;
import io.github.anthem37.jagent.domain.conversation.event.ConversationCreatedEvent;
import io.github.anthem37.jagent.domain.conversation.event.ConversationEndedEvent;
import io.github.anthem37.jagent.domain.conversation.event.MessageAddedEvent;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 会话聚合根
 * 管理智能体与用户之间的对话历史
 */
@Getter
public class Conversation extends AbstractAggregateRoot<String> {

    private String sessionId;
    private String memoryId;
    private List<ConversationMessage> messages;
    private LocalDateTime startTime;
    private LocalDateTime lastActiveTime;
    private boolean isActive;

    // 私有构造函数
    private Conversation(String conversationId, String sessionId) {
        super(conversationId);
        this.sessionId = sessionId;
        this.messages = new ArrayList<>();
        this.startTime = LocalDateTime.now();
        this.lastActiveTime = LocalDateTime.now();
        this.isActive = true;
    }

    /**
     * 创建新会话
     */
    public static Conversation create(String sessionId) {
        String conversationId = UUID.randomUUID().toString();
        Conversation conversation = new Conversation(conversationId, sessionId);

        // 发布会话创建事件
        conversation.addDomainEvent(new ConversationCreatedEvent(conversationId, sessionId));

        return conversation;
    }

    /**
     * 添加用户消息
     */
    public void addUserMessage(String content) {
        ConversationMessage message = ConversationMessage.createUserMessage(content);
        this.messages.add(message);
        this.lastActiveTime = LocalDateTime.now();

        addDomainEvent(new MessageAddedEvent(this.getId(), message));
    }

    /**
     * 添加助手消息
     */
    public void addAssistantMessage(String content) {
        ConversationMessage message = ConversationMessage.createAssistantMessage(content);
        this.messages.add(message);
        this.lastActiveTime = LocalDateTime.now();

        addDomainEvent(new MessageAddedEvent(this.getId(), message));
    }

    /**
     * 添加系统消息
     */
    public void addSystemMessage(String content) {
        ConversationMessage message = ConversationMessage.createSystemMessage(content);
        this.messages.add(message);
        this.lastActiveTime = LocalDateTime.now();

        addDomainEvent(new MessageAddedEvent(this.getId(), message));
    }

    /**
     * 结束会话
     */
    public void end() {
        if (this.isActive) {
            this.isActive = false;
            this.lastActiveTime = LocalDateTime.now();
            addDomainEvent(new ConversationEndedEvent(this.getId()));
        }
    }

    /**
     * 清除消息历史
     */
    public void clearMessages() {
        this.messages.clear();
        this.lastActiveTime = LocalDateTime.now();
        addDomainEvent(new ConversationClearedEvent(this.getId()));
    }

    /**
     * 获取最新的N条消息
     */
    public List<ConversationMessage> getRecentMessages(int count) {
        if (count <= 0 || messages.isEmpty()) {
            return new ArrayList<>();
        }

        int fromIndex = Math.max(0, messages.size() - count);
        return new ArrayList<>(messages.subList(fromIndex, messages.size()));
    }

    /**
     * 获取消息总数
     */
    public int getMessageCount() {
        return messages.size();
    }

    /**
     * 获取会话持续时间（分钟）
     */
    public long getDurationMinutes() {
        return Duration.between(startTime, lastActiveTime).toMinutes();
    }

    /**
     * 设置记忆ID
     */
    public void setMemoryId(String memoryId) {
        this.memoryId = memoryId;
        this.lastActiveTime = LocalDateTime.now();
    }

    public List<ConversationMessage> getMessages() {
        return new ArrayList<>(messages);
    }

}