package com.diagrammnt.dto;

import lombok.Data;

@Data
public class BatchTaskDTO {
    private String taskId;
    private String type;
    private int total;
    private int processed;
    private boolean running;
    private String message;
}
