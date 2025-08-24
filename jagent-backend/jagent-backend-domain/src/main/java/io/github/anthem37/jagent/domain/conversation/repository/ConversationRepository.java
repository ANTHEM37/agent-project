package io.github.anthem37.jagent.domain.conversation.repository;

import io.github.anthem37.ddd.domain.repository.IRepository;
import io.github.anthem37.jagent.domain.conversation.model.Conversation;

import java.util.List;
import java.util.Optional;

/**
 * 会话仓储接口
 * 定义会话的持久化操作契约
 */
public interface ConversationRepository extends IRepository<Conversation, String> {

    /**
     * 根据ID查找会话
     */
    Optional<Conversation> findById(String conversationId);

    /**
     * 删除会话
     */
    void delete(String conversationId);

    /**
     * 根据会话ID查找会话
     */
    Optional<Conversation> findBySessionId(String sessionId);

    /**
     * 根据记忆ID查找会话列表
     */
    List<Conversation> findByMemoryId(String memoryId);

    /**
     * 查找活跃的会话
     */
    List<Conversation> findActiveConversations();

    /**
     * 查找所有会话
     */
    List<Conversation> findAll();

    /**
     * 检查会话是否存在
     */
    boolean existsById(String conversationId);

    /**
     * 检查会话ID是否存在
     */
    boolean existsBySessionId(String sessionId);

    /**
     * 统计活跃会话数量
     */
    long countActiveConversations();
}