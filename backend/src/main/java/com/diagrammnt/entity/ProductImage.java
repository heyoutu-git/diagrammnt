package com.diagrammnt.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product_image")
public class ProductImage {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("file_name") private String fileName;
    @TableField("file_path") private String filePath;
    @TableField("file_size") private Long fileSize;
    @TableField("file_md5") private String fileMd5;
    @TableField("file_sha256") private String fileSha256;
    private Integer width;
    private Integer height;
    private String format;
    @TableField("color_mode") private String colorMode;
    private String dhash;
    private String phash;
    @TableField("feature_vector") private byte[] featureVector;
    @TableField("feature_model") private String featureModel;
    @TableField("quality_score") private BigDecimal qualityScore;
    @TableField("sharpness_score") private BigDecimal sharpnessScore;
    @TableField("resolution_score") private BigDecimal resolutionScore;
    private Integer status;
    @TableField("is_duplicate") private Integer isDuplicate;
    @TableField("duplicate_of") private Long duplicateOf;
    @TableField("source_path") private String sourcePath;
    @TableField("import_batch") private String importBatch;
    @TableField("thumbnail_path") private String thumbnailPath;
    @TableField("preview_path") private String previewPath;
    @TableField(value = "created_at", fill = FieldFill.INSERT) private LocalDateTime createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE) private LocalDateTime updatedAt;
}
