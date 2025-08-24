package io.github.anthem37.jagent.domain.task.valueobject;

/**
 * 任务优先级枚举
 */
public enum TaskPriorityEnum {

    /**
     * 低优先级
     */
    LOW(1),

    /**
     * 普通优先级
     */
    NORMAL(2),

    /**
     * 高优先级
     */
    HIGH(3),

    /**
     * 紧急优先级
     */
    URGENT(4);

    private final int level;

    TaskPriorityEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    /**
     * 比较优先级
     */
    public boolean isHigherThan(TaskPriorityEnum other) {
        return this.level > other.level;
    }
}
