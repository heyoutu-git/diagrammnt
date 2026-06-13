package com.diagrammnt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("search_history")
public class SearchHistory {
    @TableId(type = IdType.AUTO) private Long id;
    @TableField("image_id") private Long imageId;
    @TableField("search_type") private String searchType;
    @TableField("search_keyword") private String searchKeyword;
    @TableField("result_count") private Integer resultCount;
    @TableField(value = "created_at", fill = FieldFill.INSERT) private LocalDateTime createdAt;
}
