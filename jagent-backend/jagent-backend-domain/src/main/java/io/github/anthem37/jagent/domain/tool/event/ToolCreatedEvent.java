package io.github.anthem37.jagent.domain.tool.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.tool.valueobject.ToolTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具创建事件
 */
@Getter
@AllArgsConstructor
public class ToolCreatedEvent implements IDomainEvent {
    /**
     * 事件类型
     */
    private final String eventType = "ToolCreated";

    /**
     * 工具ID
     */
    private final String toolId;

    /**
     * 工具名称
     */
    private final String name;

    /**
     * 工具类型
     */
    private final ToolTypeEnum type;

}
