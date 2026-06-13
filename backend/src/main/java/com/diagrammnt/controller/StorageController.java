package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {
    @GetMapping("/stats")
    public ApiResponse<Map<String, Object>> stats() { return ApiResponse.success(Map.of("totalSize", 0L, "imageCount", 0)); }
}
