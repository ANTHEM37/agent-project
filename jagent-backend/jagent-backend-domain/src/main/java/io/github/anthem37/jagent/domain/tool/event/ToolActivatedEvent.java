package io.github.anthem37.jagent.domain.tool.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具激活事件
 */
@Getter
@AllArgsConstructor
public class ToolActivatedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = "ToolActivated";

    /**
     * 工具ID
     */
    private final String toolId;
    
}
