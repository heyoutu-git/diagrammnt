package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import com.diagrammnt.common.PageResult;
import com.diagrammnt.dto.DuplicateActionDTO;
import com.diagrammnt.vo.ImageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/duplicates")
@RequiredArgsConstructor
public class DuplicateController {
    @GetMapping
    public ApiResponse<PageResult<ImageVO>> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(new PageResult<>(page, size, 0, 0, Collections.emptyList()));
    }
    @PostMapping("/detect")
    public ApiResponse<Map<String, Object>> detect() { return ApiResponse.success(Map.of("totalGroups", 0, "totalDuplicates", 0)); }
    @PostMapping("/action")
    public ApiResponse<Void> action(@RequestBody DuplicateActionDTO dto) { return ApiResponse.success(null); }
}
