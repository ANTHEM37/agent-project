package io.github.anthem37.jagent.domain.agent.valueobject;

/**
 * 智能体状态枚举
 */
public enum AgentStateEnum {

    /**
     * 空闲状态 - 智能体已创建但未开始执行
     */
    IDLE,

    /**
     * 运行中 - 智能体正在执行任务
     */
    RUNNING,

    /**
     * 暂停 - 智能体暂停执行，可以恢复
     */
    PAUSED,

    /**
     * 已完成 - 智能体成功完成任务
     */
    COMPLETED,

    /**
     * 已停止 - 智能体被手动停止
     */
    STOPPED,

    /**
     * 错误状态 - 智能体执行过程中发生错误
     */
    ERROR,

    /**
     * 达到最大步数 - 智能体执行步数达到上限
     */
    MAX_STEPS_REACHED;

    /**
     * 判断是否为最终状态
     */
    public boolean isFinalState() {
        return this == COMPLETED || this == STOPPED || this == ERROR || this == MAX_STEPS_REACHED;
    }

    /**
     * 判断是否可以执行操作
     */
    public boolean canExecute() {
        return this == RUNNING;
    }

    /**
     * 判断是否可以暂停
     */
    public boolean canPause() {
        return this == RUNNING;
    }

    /**
     * 判断是否可以恢复
     */
    public boolean canResume() {
        return this == PAUSED;
    }

    /**
     * 判断是否可以停止
     */
    public boolean canStop() {
        return this == RUNNING || this == PAUSED;
    }
}