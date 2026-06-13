package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import com.diagrammnt.common.PageResult;
import com.diagrammnt.vo.ImageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    @PostMapping("/by-image")
    public ApiResponse<PageResult<ImageVO>> searchByImage(@RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(new PageResult<>(page, size, 0, 0, Collections.emptyList()));
    }
    @GetMapping("/by-keyword")
    public ApiResponse<PageResult<ImageVO>> searchByKeyword(@RequestParam String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(new PageResult<>(page, size, 0, 0, Collections.emptyList()));
    }
}
