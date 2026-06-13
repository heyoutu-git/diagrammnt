package com.diagrammnt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("import_checkpoint")
public class ImportCheckpoint {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("task_id") private String taskId;
    @TableField("source_path") private String sourcePath;
    private Integer status;
    @TableField(value = "created_at", fill = FieldFill.INSERT) private LocalDateTime createdAt;
}
