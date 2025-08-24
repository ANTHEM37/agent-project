package io.github.anthem37.jagent.domain.agent.event;

/**
 * @author zhihao.mao
 * @version 1.0.0
 * @date 2025/8/23 20:26
 */

import io.github.anthem37.ddd.domain.event.IDomainEvent;
import io.github.anthem37.jagent.domain.agent.valueobject.AgentEventTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 智能体停止事件
 */
@Getter
@AllArgsConstructor
public class AgentStoppedEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    private final String eventType = AgentEventTypeEnum.AGENT_STOPPED.name();

    /**
     * 智能体ID
     */
    private final String agentId;
}