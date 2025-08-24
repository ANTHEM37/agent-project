-- JAgent智能体数据库初始化脚本

-- 创建智能体表
CREATE TABLE IF NOT EXISTS agent (
    id VARCHAR(64) PRIMARY KEY COMMENT '智能体ID',
    name VARCHAR(255) NOT NULL UNIQUE COMMENT '智能体名称',
    description TEXT COMMENT '智能体描述',
    type VARCHAR(50) NOT NULL COMMENT '智能体类型',
    state VARCHAR(50) NOT NULL DEFAULT 'IDLE' COMMENT '智能体状态',
    configuration JSON COMMENT '智能体配置',
    max_steps INT NOT NULL DEFAULT 50 COMMENT '最大执行步数',
    current_step INT NOT NULL DEFAULT 0 COMMENT '当前执行步数',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name),
    INDEX idx_type (type),
    INDEX idx_state (state),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体表';

-- 创建任务表
CREATE TABLE IF NOT EXISTS task (
    id VARCHAR(64) PRIMARY KEY COMMENT '任务ID',
    title VARCHAR(255) NOT NULL COMMENT '任务标题',
    description TEXT COMMENT '任务描述',
    user_input TEXT NOT NULL COMMENT '用户输入',
    status VARCHAR(50) NOT NULL DEFAULT 'CREATED' COMMENT '任务状态',
    priority VARCHAR(50) NOT NULL DEFAULT 'NORMAL' COMMENT '任务优先级',
    session_id VARCHAR(64) COMMENT '会话ID',
    memory_id VARCHAR(64) COMMENT '记忆ID',
    result TEXT COMMENT '任务结果',
    error_message TEXT COMMENT '错误信息',
    start_time TIMESTAMP NULL COMMENT '开始时间',
    end_time TIMESTAMP NULL COMMENT '结束时间',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_status (status),
    INDEX idx_session_id (session_id),
    INDEX idx_memory_id (memory_id),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务表';

-- 创建任务步骤表
CREATE TABLE IF NOT EXISTS task_step (
    id VARCHAR(64) PRIMARY KEY COMMENT '步骤ID',
    task_id VARCHAR(64) NOT NULL COMMENT '任务ID',
    description TEXT NOT NULL COMMENT '步骤描述',
    step_type VARCHAR(100) NOT NULL COMMENT '步骤类型',
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING' COMMENT '步骤状态',
    result TEXT COMMENT '步骤结果',
    error_message TEXT COMMENT '错误信息',
    start_time TIMESTAMP NULL COMMENT '开始时间',
    end_time TIMESTAMP NULL COMMENT '结束时间',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_task_id (task_id),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at),
    FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务步骤表';

-- 创建工具表
CREATE TABLE IF NOT EXISTS tool (
    id VARCHAR(64) PRIMARY KEY COMMENT '工具ID',
    name VARCHAR(255) NOT NULL UNIQUE COMMENT '工具名称',
    description TEXT COMMENT '工具描述',
    type VARCHAR(50) NOT NULL COMMENT '工具类型',
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE' COMMENT '工具状态',
    function_name VARCHAR(255) NOT NULL COMMENT '函数名称',
    parameters JSON COMMENT '工具参数',
    configuration JSON COMMENT '工具配置',
    version VARCHAR(50) NOT NULL DEFAULT '1.0.0' COMMENT '版本',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name),
    INDEX idx_type (type),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工具表';

-- 创建会话表
CREATE TABLE IF NOT EXISTS conversation (
    id VARCHAR(64) PRIMARY KEY COMMENT '会话ID',
    session_id VARCHAR(64) NOT NULL UNIQUE COMMENT '会话标识',
    memory_id VARCHAR(64) COMMENT '记忆ID',
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    last_active_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后活跃时间',
    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否活跃',
    INDEX idx_session_id (session_id),
    INDEX idx_memory_id (memory_id),
    INDEX idx_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话表';

-- 创建会话消息表
CREATE TABLE IF NOT EXISTS conversation_message (
    id VARCHAR(64) PRIMARY KEY COMMENT '消息ID',
    conversation_id VARCHAR(64) NOT NULL COMMENT '会话ID',
    content TEXT NOT NULL COMMENT '消息内容',
    role VARCHAR(20) NOT NULL COMMENT '消息角色',
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
    metadata TEXT COMMENT '元数据',
    INDEX idx_conversation_id (conversation_id),
    INDEX idx_role (role),
    INDEX idx_timestamp (timestamp),
    FOREIGN KEY (conversation_id) REFERENCES conversation(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话消息表';

-- 创建智能体工具关联表
CREATE TABLE IF NOT EXISTS agent_tool (
    agent_id VARCHAR(64) NOT NULL COMMENT '智能体ID',
    tool_id VARCHAR(64) NOT NULL COMMENT '工具ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (agent_id, tool_id),
    FOREIGN KEY (agent_id) REFERENCES agent(id) ON DELETE CASCADE,
    FOREIGN KEY (tool_id) REFERENCES tool(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体工具关联表';

-- 插入一些初始数据
INSERT INTO tool (id, name, description, type, function_name) VALUES
('tool-001', '文本生成工具', '用于生成各种类型的文本内容', 'TEXT_PROCESSING', 'generateText'),
('tool-002', '代码分析工具', '用于分析和理解代码结构', 'CODE', 'analyzeCode'),
('tool-003', '搜索工具', '用于在互联网上搜索信息', 'SEARCH', 'searchWeb'),
('tool-004', '计算工具', '用于执行数学计算', 'CALCULATION', 'calculate')
ON DUPLICATE KEY UPDATE name=VALUES(name);