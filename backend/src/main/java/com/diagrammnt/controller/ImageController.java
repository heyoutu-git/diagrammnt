package com.diagrammnt.controller;

import com.diagrammnt.common.ApiResponse;
import com.diagrammnt.common.PageResult;
import com.diagrammnt.dto.ImageQueryDTO;
import com.diagrammnt.dto.ImportTaskDTO;
import com.diagrammnt.service.ImageService;
import com.diagrammnt.vo.DashboardVO;
import com.diagrammnt.vo.ImageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ApiResponse<ImageVO> upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String subDir, @RequestParam(required = false) String importBatch) {
        return ApiResponse.success(imageService.uploadImage(file, subDir, importBatch));
    }

    @PostMapping("/query")
    public ApiResponse<PageResult<ImageVO>> query(@RequestBody ImageQueryDTO query) {
        return ApiResponse.success(imageService.queryImages(query));
    }

    @GetMapping("/{id}")
    public ApiResponse<ImageVO> detail(@PathVariable Long id) {
        return ApiResponse.success(imageService.getImageDetail(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        imageService.softDelete(id);
        return ApiResponse.success(null);
    }

    @PostMapping("/batch-delete")
    public ApiResponse<Void> batchDelete(@RequestBody Map<String, List<Long>> body) {
        imageService.batchDelete(body.get("ids"));
        return ApiResponse.success(null);
    }

    @PostMapping("/{id}/restore")
    public ApiResponse<Void> restore(@PathVariable Long id) {
        imageService.restoreImage(id);
        return ApiResponse.success(null);
    }

    @GetMapping("/recycle-bin")
    public ApiResponse<PageResult<ImageVO>> recycleBin(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(imageService.getRecycleBin(page, size));
    }

    @PostMapping("/import-directory")
    public ApiResponse<Map<String, String>> importDirectory(@RequestBody Map<String, String> body) {
        String taskId = imageService.startAsyncImport(body.get("dirPath"), body.get("importBatch"));
        return ApiResponse.success(Map.of("taskId", taskId));
    }

    @GetMapping("/import-status/{taskId}")
    public ApiResponse<ImportTaskDTO> importStatus(@PathVariable String taskId) {
        return ApiResponse.success(imageService.getImportStatus(taskId));
    }

    @GetMapping("/dashboard")
    public ApiResponse<DashboardVO> dashboard() {
        return ApiResponse.success(imageService.getDashboard());
    }
}
