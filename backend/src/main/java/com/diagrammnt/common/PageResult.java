package com.diagrammnt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    private long current;
    private long size;
    private long total;
    private long pages;
    private List<T> records;
}
