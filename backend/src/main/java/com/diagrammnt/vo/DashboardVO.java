package com.diagrammnt.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DashboardVO {
    private long totalImages;
    private long normalCount;
    private long lowQualityCount;
    private long duplicateCount;
    private long deletedCount;
    private List<Map<String, Object>> formatStats;
}
