package com.diagrammnt.service;

import com.diagrammnt.common.PageResult;
import com.diagrammnt.dto.ImageQueryDTO;
import com.diagrammnt.dto.ImportTaskDTO;
import com.diagrammnt.vo.DashboardVO;
import com.diagrammnt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ImageService {
    ImageVO uploadImage(MultipartFile file, String subDir, String importBatch);
    PageResult<ImageVO> queryImages(ImageQueryDTO query);
    ImageVO getImageDetail(Long id);
    void softDelete(Long id);
    void batchDelete(List<Long> ids);
    void restoreImage(Long id);
    PageResult<ImageVO> getRecycleBin(int page, int size);
    String startAsyncImport(String dirPath, String importBatch);
    ImportTaskDTO getImportStatus(String taskId);
    DashboardVO getDashboard();
}
