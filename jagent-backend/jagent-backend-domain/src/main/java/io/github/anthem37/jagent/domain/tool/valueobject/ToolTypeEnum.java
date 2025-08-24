package io.github.anthem37.jagent.domain.tool.valueobject;

/**
 * 工具类型枚举
 */
public enum ToolTypeEnum {

    /**
     * 浏览器工具
     */
    BROWSER("浏览器工具", "用于网页浏览和自动化操作"),

    /**
     * 代码工具
     */
    CODE("代码工具", "用于代码编写、分析和执行"),

    /**
     * 数据库工具
     */
    DATABASE("数据库工具", "用于数据库查询和操作"),

    /**
     * 文件系统工具
     */
    FILESYSTEM("文件系统工具", "用于文件和目录操作"),

    /**
     * 文本处理工具
     */
    TEXT_PROCESSING("文本处理工具", "用于文本分析和处理"),

    /**
     * API调用工具
     */
    API_CALLING("API调用工具", "用于调用外部API服务"),

    /**
     * 搜索工具
     */
    SEARCH("搜索工具", "用于信息搜索和检索"),

    /**
     * 计算工具
     */
    CALCULATION("计算工具", "用于数学计算和数据分析"),

    /**
     * 文档生成工具
     */
    DOCUMENT_GENERATION("文档生成工具", "用于生成各种格式的文档"),

    /**
     * 其他工具
     */
    OTHER("其他工具", "其他类型的工具");

    private final String displayName;
    private final String description;

    ToolTypeEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}