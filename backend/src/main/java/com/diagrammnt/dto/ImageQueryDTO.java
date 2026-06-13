package com.diagrammnt.dto;

import lombok.Data;

@Data
public class ImageQueryDTO {
    private Integer page;
    private Integer size;
    private Integer status;
    private String format;
    private Integer isDuplicate;
    private String keyword;
}
