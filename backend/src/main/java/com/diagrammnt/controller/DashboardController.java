package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import com.diagrammnt.service.ImageService;
import com.diagrammnt.vo.DashboardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final ImageService imageService;
    @GetMapping
    public ApiResponse<DashboardVO> dashboard() { return ApiResponse.success(imageService.getDashboard()); }
}
