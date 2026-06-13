package com.diagrammnt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("duplicate_group")
public class DuplicateGroup {
    @TableId(type = IdType.AUTO) private Long id;
    @TableField("primary_image_id") private Long primaryImageId;
    @TableField("duplicate_image_id") private Long duplicateImageId;
    @TableField("match_type") private String matchType;
    @TableField("match_score") private Double matchScore;
    @TableField(value = "created_at", fill = FieldFill.INSERT) private LocalDateTime createdAt;
}
