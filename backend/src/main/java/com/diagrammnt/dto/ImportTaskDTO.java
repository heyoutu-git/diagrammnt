package com.diagrammnt.dto;

import lombok.Data;

@Data
public class ImportTaskDTO {
    private String taskId;
    private String dirPath;
    private int totalFiles;
    private int successCount;
    private int failCount;
    private int currentIndex;
    private boolean running;
    private boolean done;
    private String message;
}
