package io.github.anthem37.jagent.domain.agent.event;

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentEventTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 智能体完成事件
 */
@Getter
@AllArgsConstructor
public class AgentCompletedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = AgentEventTypeEnum.AGENT_COMPLETED.name();

    /**
     * 智能体ID
     */
    private String agentId;

}

