package io.github.anthem37.jagent.domain.tool.model;

import io.github.anthem37.ddd.domain.model.AbstractAggregateRoot;
import io.github.anthem37.jagent.domain.tool.event.*;
import io.github.anthem37.jagent.domain.tool.valueobject.ToolStatusEnum;
import io.github.anthem37.jagent.domain.tool.valueobject.ToolTypeEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * 工具聚合根
 * 代表智能体可以使用的工具
 */
@Getter
public class Tool extends AbstractAggregateRoot<String> {

    /**
     * 工具名称
     */
    private String name;
    /**
     * 工具描述
     */
    private String description;
    /**
     * 工具类型
     */
    private ToolTypeEnum type;
    /**
     * 工具状态
     */
    private ToolStatusEnum status;
    /**
     * 工具函数名称
     */
    private String functionName;
    /**
     * 工具参数
     */
    private Map<String, Object> parameters;
    /**
     * 工具配置
     */
    private Map<String, Object> configuration;
    /**
     * 工具版本
     */
    private String version;
    /**
     * 工具创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 工具更新时间
     */
    private LocalDateTime updatedAt;

    // 私有构造函数
    private Tool(String toolId, String name, String description, ToolTypeEnum type, String functionName) {
        super(toolId);
        this.name = name;
        this.description = description;
        this.type = type;
        this.functionName = functionName;
        this.status = ToolStatusEnum.ACTIVE;
        this.version = "1.0.0";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 创建新工具
     */
    public static Tool create(String name, String description, ToolTypeEnum type, String functionName) {
        String toolId = UUID.randomUUID().toString();
        Tool tool = new Tool(toolId, name, description, type, functionName);

        // 发布工具创建事件
        tool.addDomainEvent(new ToolCreatedEvent(toolId, name, type));

        return tool;
    }

    /**
     * 激活工具
     */
    public void activate() {
        if (this.status == ToolStatusEnum.INACTIVE) {
            this.status = ToolStatusEnum.ACTIVE;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new ToolActivatedEvent(this.getId()));
        }
    }

    /**
     * 停用工具
     */
    public void deactivate() {
        if (this.status == ToolStatusEnum.ACTIVE) {
            this.status = ToolStatusEnum.INACTIVE;
            this.updatedAt = LocalDateTime.now();
            addDomainEvent(new ToolDeactivatedEvent(this.getId()));
        }
    }

    /**
     * 更新参数
     */
    public void updateParameters(Map<String, Object> newParameters) {
        this.parameters = newParameters;
        this.updatedAt = LocalDateTime.now();
        addDomainEvent(new ToolParametersUpdatedEvent(this.getId()));
    }

    /**
     * 更新配置
     */
    public void updateConfiguration(Map<String, Object> newConfiguration) {
        this.configuration = newConfiguration;
        this.updatedAt = LocalDateTime.now();
        addDomainEvent(new ToolConfigurationUpdatedEvent(this.getId()));
    }

    /**
     * 更新版本
     */
    public void updateVersion(String newVersion) {
        if (newVersion == null || newVersion.trim().isEmpty()) {
            throw new IllegalArgumentException("Version cannot be null or empty");
        }
        this.version = newVersion;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 判断工具是否可用
     */
    public boolean isAvailable() {
        return this.status == ToolStatusEnum.ACTIVE;
    }

}