package io.github.anthem37.jagent.domain.tool.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具参数更新事件
 */
@Getter
@AllArgsConstructor
public class ToolParametersUpdatedEvent implements IDomainEvent {
    
    /**
     * 事件类型
     */
    private final String eventType = "ToolParametersUpdated";

    /**
     * 工具ID
     */
    private final String toolId;
}

