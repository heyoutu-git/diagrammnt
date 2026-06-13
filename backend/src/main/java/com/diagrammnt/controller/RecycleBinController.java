package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import com.diagrammnt.common.PageResult;
import com.diagrammnt.service.ImageService;
import com.diagrammnt.vo.ImageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recycle")
@RequiredArgsConstructor
public class RecycleBinController {
    private final ImageService imageService;
    @GetMapping
    public ApiResponse<PageResult<ImageVO>> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(imageService.getRecycleBin(page, size));
    }
    @PostMapping("/{id}/restore")
    public ApiResponse<Void> restore(@PathVariable Long id) { imageService.restoreImage(id); return ApiResponse.success(null); }
}
