package io.github.anthem37.jagent.domain.agent.valueobject;

/**
 * 智能体事件类型
 */
public enum AgentEventTypeEnum {

    /**
     * 智能体完成事件
     */
    AGENT_COMPLETED,
    /**
     * 智能体最大步数 reached 事件
     */
    AGENT_MAX_STEPS_REACHED,
    /**
     * 智能体暂停事件
     */
    AGENT_PAUSED,
    /**
     * 智能体恢复事件
     */
    AGENT_RESUMED,
    /**
     * 智能体启动事件
     */
    AGENT_STARTED,
    /**
     * 智能体停止事件
     */
    AGENT_STOPPED,
}
