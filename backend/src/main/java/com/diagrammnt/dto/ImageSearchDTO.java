package com.diagrammnt.dto;

import lombok.Data;

@Data
public class ImageSearchDTO {
    private String keyword;
    private String dhash;
    private String featureVector;
    private Integer page;
    private Integer size;
}
