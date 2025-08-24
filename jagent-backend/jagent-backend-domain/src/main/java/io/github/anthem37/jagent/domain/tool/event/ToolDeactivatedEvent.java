package io.github.anthem37.jagent.domain.tool.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具停用事件
 */
@Getter
@AllArgsConstructor
public class ToolDeactivatedEvent implements IDomainEvent {
    
    /**
     * 事件类型
     */
    private final String eventType = "ToolDeactivated";

    /**
     * 工具ID
     */
    private final String toolId;
}
