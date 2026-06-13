package com.diagrammnt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("image_tag")
public class ImageTag {
    @TableId(type = IdType.AUTO) private Long id;
    @TableField("image_id") private Long imageId;
    @TableField("tag_name") private String tagName;
    @TableField(value = "created_at", fill = FieldFill.INSERT) private LocalDateTime createdAt;
}
