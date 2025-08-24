package io.github.anthem37.jagent.domain.task.repository;

import io.github.anthem37.ddd.domain.repository.IRepository;
import io.github.anthem37.jagent.domain.task.model.Task;
import io.github.anthem37.jagent.domain.task.valueobject.TaskStatusEnum;

import java.util.List;
import java.util.Optional;

/**
 * 任务仓储接口
 * 定义任务的持久化操作契约
 */
public interface TaskRepository extends IRepository<Task, String> {

    /**
     * 根据ID查找任务
     */
    Optional<Task> findById(String taskId);

    /**
     * 根据状态查找任务列表
     */
    List<Task> findByStatus(TaskStatusEnum status);

    /**
     * 根据会话ID查找任务列表
     */
    List<Task> findBySessionId(String sessionId);

    /**
     * 根据记忆ID查找任务列表
     */
    List<Task> findByMemoryId(String memoryId);

    /**
     * 查找所有任务
     */
    List<Task> findAll();

    /**
     * 根据状态统计任务数量
     */
    long countByStatus(TaskStatusEnum status);

    /**
     * 检查任务是否存在
     */
    boolean existsById(String taskId);

    /**
     * 查找运行中的任务
     */
    List<Task> findRunningTasks();

    /**
     * 查找已完成的任务
     */
    List<Task> findCompletedTasks();

    /**
     * 查找失败的任务
     */
    List<Task> findFailedTasks();
}