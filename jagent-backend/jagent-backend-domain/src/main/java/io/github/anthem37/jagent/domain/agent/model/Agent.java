package io.github.anthem37.jagent.domain.agent.model;

import io.github.anthem37.ddd.domain.model.AbstractAggregateRoot;
import io.github.anthem37.jagent.domain.agent.event.*;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentStateEnum;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentTypeEnum;
import io.github.anthem37.jagent.domain.conversation.model.Conversation;
import io.github.anthem37.jagent.domain.task.model.Task;
import io.github.anthem37.jagent.domain.tool.model.Tool;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 智能体聚合根
 * 负责管理智能体的生命周期和行为
 */
@Getter
public class Agent extends AbstractAggregateRoot<String> {

    private String name;
    private String description;
    private AgentTypeEnum type;
    private AgentStateEnum state;
    private Map<String, Object> configuration;
    private List<Tool> availableTools;
    private Conversation currentConversation;
    private int maxSteps;
    private int currentStep;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 私有构造函数，强制使用工厂方法
    private Agent(String agentId, String name, String description, AgentTypeEnum type) {
        super(agentId);
        this.name = name;
        this.description = description;
        this.type = type;
        this.state = AgentStateEnum.IDLE;
        this.availableTools = new ArrayList<>();
        this.maxSteps = 50; // 默认最大步数
        this.currentStep = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 创建新的智能体
     */
    public static Agent create(String name, String description, AgentTypeEnum type) {
        String agentId = UUID.randomUUID().toString();
        return new Agent(agentId, name, description, type);
    }

    /**
     * 启动智能体执行任务
     */
    public void startExecution(Task task) {
        validateCanStart();
        this.state = AgentStateEnum.RUNNING;
        this.currentStep = 0;
        this.updatedAt = LocalDateTime.now();

        // 发布领域事件
        addDomainEvent(new AgentStartedEvent(this.getId(), task.getId()));
    }

    /**
     * 执行单步操作
     */
    public void executeStep() {
        validateCanExecuteStep();
        this.currentStep++;
        this.updatedAt = LocalDateTime.now();

        if (this.currentStep >= this.maxSteps) {
            this.state = AgentStateEnum.MAX_STEPS_REACHED;
            addDomainEvent(new AgentMaxStepsReachedEvent(this.getId()));
        }
    }

    /**
     * 完成任务执行
     */
    public void completeExecution() {
        validateCanComplete();
        this.state = AgentStateEnum.COMPLETED;
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new AgentCompletedEvent(this.getId()));
    }

    /**
     * 暂停执行
     */
    public void pause() {
        if (this.state == AgentStateEnum.RUNNING) {
            this.state = AgentStateEnum.PAUSED;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new AgentPausedEvent(this.getId()));
        }
    }

    /**
     * 恢复执行
     */
    public void resume() {
        if (this.state == AgentStateEnum.PAUSED) {
            this.state = AgentStateEnum.RUNNING;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new AgentResumedEvent(this.getId()));
        }
    }

    /**
     * 停止执行
     */
    public void stop() {
        if (this.state == AgentStateEnum.RUNNING || this.state == AgentStateEnum.PAUSED) {
            this.state = AgentStateEnum.STOPPED;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new AgentStoppedEvent(this.getId()));
        }
    }

    /**
     * 添加可用工具
     */
    public void addTool(Tool tool) {
        if (!this.availableTools.contains(tool)) {
            this.availableTools.add(tool);
            this.updatedAt = LocalDateTime.now();
        }
    }

    /**
     * 移除工具
     */
    public void removeTool(Tool tool) {
        if (this.availableTools.remove(tool)) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    /**
     * 更新配置
     */
    public void updateConfiguration(Map<String, Object> newConfiguration) {
        this.configuration = newConfiguration;
        this.updatedAt = LocalDateTime.now();
    }

    // 验证方法
    private void validateCanStart() {
        if (this.state != AgentStateEnum.IDLE && this.state != AgentStateEnum.COMPLETED && this.state != AgentStateEnum.STOPPED) {
            throw new IllegalStateException("Agent cannot start from state: " + this.state);
        }
    }

    private void validateCanExecuteStep() {
        if (this.state != AgentStateEnum.RUNNING) {
            throw new IllegalStateException("Agent cannot execute step from state: " + this.state);
        }
    }

    private void validateCanComplete() {
        if (this.state != AgentStateEnum.RUNNING) {
            throw new IllegalStateException("Agent cannot complete from state: " + this.state);
        }
    }

    // 包内可见的 setter 方法
    void setCurrentConversation(Conversation conversation) {
        this.currentConversation = conversation;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 设置最大步数
     */
    public void setMaxSteps(int maxSteps) {
        if (maxSteps <= 0) {
            throw new IllegalArgumentException("Max steps must be positive");
        }
        this.maxSteps = maxSteps;
        this.updatedAt = LocalDateTime.now();
    }

}