package io.github.anthem37.jagent.domain.agent.valueobject;

/**
 * 智能体类型枚举
 */
public enum AgentTypeEnum {

    /**
     * ReAct智能体 - 基于推理和行动循环
     */
    REACT("ReAct Agent", "基于推理和行动循环的智能体"),

    /**
     * 工具调用智能体 - 专门处理工具调用
     */
    TOOL_CALLING("Tool Calling Agent", "专门处理工具调用的智能体"),

    /**
     * 规划智能体 - 负责任务分解和规划
     */
    PLANNING("Planning Agent", "负责任务分解和规划的智能体"),

    /**
     * 浏览器智能体 - 专门处理浏览器操作
     */
    BROWSER("Browser Agent", "专门处理浏览器操作的智能体"),

    /**
     * 代码智能体 - 专门处理代码相关任务
     */
    CODE("Code Agent", "专门处理代码相关任务的智能体"),

    /**
     * 数据库智能体 - 专门处理数据库操作
     */
    DATABASE("Database Agent", "专门处理数据库操作的智能体"),

    /**
     * 文件系统智能体 - 专门处理文件系统操作
     */
    FILESYSTEM("Filesystem Agent", "专门处理文件系统操作的智能体"),

    /**
     * 通用智能体 - 可以处理多种类型的任务
     */
    GENERAL("General Agent", "可以处理多种类型任务的通用智能体");

    private final String displayName;
    private final String description;

    AgentTypeEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * 根据显示名称获取智能体类型
     */
    public static AgentTypeEnum fromDisplayName(String displayName) {
        for (AgentTypeEnum type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown agent type: " + displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}