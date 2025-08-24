package io.github.anthem37.jagent.domain.agent.repository;

import io.github.anthem37.ddd.domain.repository.IRepository;
import io.github.anthem37.jagent.domain.agent.model.Agent;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentStateEnum;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentTypeEnum;

import java.util.List;
import java.util.Optional;

/**
 * 智能体仓储接口
 * 定义智能体的持久化操作契约
 */
public interface AgentRepository extends IRepository<Agent, String> {

    /**
     * 根据ID查找智能体
     */
    Optional<Agent> findById(String agentId);

    /**
     * 删除智能体
     */
    void delete(String agentId);

    /**
     * 根据名称查找智能体
     */
    Optional<Agent> findByName(String name);

    /**
     * 根据状态查找智能体列表
     */
    List<Agent> findByState(AgentStateEnum state);

    /**
     * 根据类型查找智能体列表
     */
    List<Agent> findByType(AgentTypeEnum type);

    /**
     * 查找所有智能体
     */
    List<Agent> findAll();

    /**
     * 查找运行中的智能体数量
     */
    long countByState(AgentStateEnum state);

    /**
     * 检查智能体是否存在
     */
    boolean existsById(String agentId);

    /**
     * 检查名称是否已存在
     */
    boolean existsByName(String name);
}