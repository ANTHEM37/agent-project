package io.github.anthem37.jagent.domain.task.valueobject;

/**
 * 任务步骤状态枚举
 */
public enum TaskStepStatusEnum {
    /**
     * 等待执行
     */
    PENDING,

    /**
     * 执行中
     */
    RUNNING,

    /**
     * 已完成
     */
    COMPLETED,

    /**
     * 执行失败
     */
    FAILED
}
