-- Import checkpoint table
CREATE TABLE IF NOT EXISTS import_checkpoint (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id VARCHAR(64) NOT NULL,
    source_path VARCHAR(1024) NOT NULL,
    status TINYINT DEFAULT 1,
    created_at DATETIME DEFAULT NOW(),
    INDEX idx_task_id (task_id),
    UNIQUE KEY uk_task_path (task_id, source_path(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
