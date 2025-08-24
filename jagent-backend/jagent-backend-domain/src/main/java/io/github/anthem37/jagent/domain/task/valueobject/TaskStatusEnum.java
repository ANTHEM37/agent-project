package io.github.anthem37.jagent.domain.task.valueobject;

/**
 * 任务状态枚举
 */
public enum TaskStatusEnum {

    /**
     * 已创建 - 任务已创建但未开始执行
     */
    CREATED,

    /**
     * 运行中 - 任务正在执行
     */
    RUNNING,

    /**
     * 暂停 - 任务暂停执行
     */
    PAUSED,

    /**
     * 已完成 - 任务成功完成
     */
    COMPLETED,

    /**
     * 失败 - 任务执行失败
     */
    FAILED,

    /**
     * 已取消 - 任务被取消
     */
    CANCELLED;

    /**
     * 判断是否为最终状态
     */
    public boolean isFinalStatus() {
        return this == COMPLETED || this == FAILED || this == CANCELLED;
    }

    /**
     * 判断是否可以执行
     */
    public boolean canExecute() {
        return this == RUNNING;
    }
}

