package io.github.anthem37.jagent.domain.agent.event;


import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentEventTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 智能体暂停事件
 */
@Getter
@AllArgsConstructor
public class AgentPausedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = AgentEventTypeEnum.AGENT_PAUSED.name();

    /**
     * 智能体ID
     */
    private final String agentId;
}