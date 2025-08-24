package io.github.anthem37.jagent.domain.task.model;

import io.github.anthem37.ddd.domain.model.AbstractAggregateRoot;
import io.github.anthem37.jagent.domain.task.event.*;
import io.github.anthem37.jagent.domain.task.valueobject.TaskPriorityEnum;
import io.github.anthem37.jagent.domain.task.valueobject.TaskStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 任务聚合根
 * 负责管理任务的生命周期和执行状态
 */
public class Task extends AbstractAggregateRoot<String> {

    private String title;
    private String description;
    private String userInput;
    private TaskStatusEnum status;
    private TaskPriorityEnum priority;
    private String sessionId;
    private String memoryId;
    private List<TaskStep> steps;
    private String result;
    private String errorMessage;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 私有构造函数
    private Task(String taskId, String title, String description, String userInput) {
        super(taskId);
        this.title = title;
        this.description = description;
        this.userInput = userInput;
        this.status = TaskStatusEnum.CREATED;
        this.priority = TaskPriorityEnum.NORMAL;
        this.steps = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 创建新任务
     */
    public static Task create(String title, String description, String userInput) {
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId, title, description, userInput);

        // 发布任务创建事件
        task.addDomainEvent(new TaskCreatedEvent(taskId, title, userInput));

        return task;
    }

    /**
     * 开始任务
     */
    public void start() {
        validateCanStart();
        this.status = TaskStatusEnum.RUNNING;
        this.startTime = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new TaskStartedEvent(this.getId()));
    }

    /**
     * 添加任务步骤
     */
    public void addStep(String stepDescription, String stepType) {
        TaskStep step = new TaskStep(stepDescription, stepType);
        this.steps.add(step);
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new TaskStepAddedEvent(this.getId(), step));
    }

    /**
     * 完成任务步骤
     */
    public void completeStep(int stepIndex, String result) {
        validateStepIndex(stepIndex);
        TaskStep step = this.steps.get(stepIndex);
        step.complete(result);
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new TaskStepCompletedEvent(this.getId(), step));
    }

    /**
     * 完成任务
     */
    public void complete(String result) {
        validateCanComplete();
        this.status = TaskStatusEnum.COMPLETED;
        this.result = result;
        this.endTime = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new TaskCompletedEvent(this.getId(), result));
    }

    /**
     * 任务失败
     */
    public void fail(String errorMessage) {
        this.status = TaskStatusEnum.FAILED;
        this.errorMessage = errorMessage;
        this.endTime = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        addDomainEvent(new TaskFailedEvent(this.getId(), errorMessage));
    }

    /**
     * 暂停任务
     */
    public void pause() {
        if (this.status == TaskStatusEnum.RUNNING) {
            this.status = TaskStatusEnum.PAUSED;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new TaskPausedEvent(this.getId()));
        }
    }

    /**
     * 恢复任务
     */
    public void resume() {
        if (this.status == TaskStatusEnum.PAUSED) {
            this.status = TaskStatusEnum.RUNNING;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new TaskResumedEvent(this.getId()));
        }
    }

    /**
     * 取消任务
     */
    public void cancel() {
        if (this.status == TaskStatusEnum.RUNNING || this.status == TaskStatusEnum.PAUSED) {
            this.status = TaskStatusEnum.CANCELLED;
            this.endTime = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new TaskCancelledEvent(this.getId()));
        }
    }

    // 验证方法
    private void validateCanStart() {
        if (this.status != TaskStatusEnum.CREATED) {
            throw new IllegalStateException("Task cannot start from status: " + this.status);
        }
    }

    private void validateCanComplete() {
        if (this.status != TaskStatusEnum.RUNNING) {
            throw new IllegalStateException("Task cannot complete from status: " + this.status);
        }
    }

    private void validateStepIndex(int stepIndex) {
        if (stepIndex < 0 || stepIndex >= this.steps.size()) {
            throw new IllegalArgumentException("Invalid step index: " + stepIndex);
        }
    }

    // Getter 方法
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserInput() {
        return userInput;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public TaskPriorityEnum getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     */
    public void setPriority(TaskPriorityEnum priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }

    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置会话ID
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        this.updatedAt = LocalDateTime.now();
    }

    public String getMemoryId() {
        return memoryId;
    }

    /**
     * 设置记忆ID
     */
    public void setMemoryId(String memoryId) {
        this.memoryId = memoryId;
        this.updatedAt = LocalDateTime.now();
    }

    public List<TaskStep> getSteps() {
        return new ArrayList<>(steps);
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取执行时长（毫秒）
     */
    public Long getExecutionDurationMillis() {
        if (startTime == null) return null;
        LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
        return java.time.Duration.between(startTime, end).toMillis();
    }

    /**
     * 判断任务是否已完成
     */
    public boolean isFinished() {
        return status == TaskStatusEnum.COMPLETED || status == TaskStatusEnum.FAILED || status == TaskStatusEnum.CANCELLED;
    }

}