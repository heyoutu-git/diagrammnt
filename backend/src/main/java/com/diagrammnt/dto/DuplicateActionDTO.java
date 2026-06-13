package com.diagrammnt.dto;

import lombok.Data;
import java.util.List;

@Data
public class DuplicateActionDTO {
    private String action;
    private List<Long> imageIds;
    private Long primaryImageId;
}
