package io.github.anthem37.jagent.domain.task.model;

import io.github.anthem37.ddd.domain.model.AbstractEntity;
import io.github.anthem37.jagent.domain.task.valueobject.TaskStepStatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 任务步骤实体
 * 表示任务执行过程中的单个步骤
 */
@Getter
public class TaskStep extends AbstractEntity<String> {

    // Getter 方法
    private String description;
    private String stepType;
    private TaskStepStatusEnum status;
    private String result;
    private String errorMessage;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;

    // 包内可见构造函数
    TaskStep(String description, String stepType) {
        super(UUID.randomUUID().toString());
        this.description = description;
        this.stepType = stepType;
        this.status = TaskStepStatusEnum.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * 开始执行步骤
     */
    public void start() {
        if (this.status == TaskStepStatusEnum.PENDING) {
            this.status = TaskStepStatusEnum.RUNNING;
            this.startTime = LocalDateTime.now();
        }
    }

    /**
     * 完成步骤
     */
    public void complete(String result) {
        if (this.status == TaskStepStatusEnum.RUNNING) {
            this.status = TaskStepStatusEnum.COMPLETED;
            this.result = result;
            this.endTime = LocalDateTime.now();
        }
    }

    /**
     * 步骤失败
     */
    public void fail(String errorMessage) {
        this.status = TaskStepStatusEnum.FAILED;
        this.errorMessage = errorMessage;
        this.endTime = LocalDateTime.now();
    }

    /**
     * 获取步骤执行时长（毫秒）
     */
    public Long getExecutionDurationMillis() {
        if (startTime == null) return null;
        LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
        return java.time.Duration.between(startTime, end).toMillis();
    }
}