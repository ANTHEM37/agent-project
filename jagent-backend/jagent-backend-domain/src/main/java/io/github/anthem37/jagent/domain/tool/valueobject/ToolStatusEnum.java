package io.github.anthem37.jagent.domain.tool.valueobject;

/**
 * 工具状态枚举
 */
public enum ToolStatusEnum {

    /**
     * 激活状态 - 工具可以正常使用
     */
    ACTIVE,

    /**
     * 非激活状态 - 工具暂时不可用
     */
    INACTIVE,

    /**
     * 维护状态 - 工具正在维护
     */
    MAINTENANCE,

    /**
     * 已弃用 - 工具不再推荐使用
     */
    DEPRECATED
}