package com.diagrammnt.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ImageVO {
    private Long id;
    private String fileName;
    private Long fileSize;
    private String fileSizeText;
    private Integer width;
    private Integer height;
    private String format;
    private String colorMode;
    private String dhash;
    private BigDecimal qualityScore;
    private Integer status;
    private Integer isDuplicate;
    private Long duplicateOf;
    private String sourcePath;
    private String importBatch;
    private String thumbnailPath;
    private String previewPath;
    private List<String> tags;
    private LocalDateTime createdAt;
}
