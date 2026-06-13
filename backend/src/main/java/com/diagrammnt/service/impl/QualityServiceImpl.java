package com.diagrammnt.service.impl;

import com.diagrammnt.entity.ProductImage;
import com.diagrammnt.service.QualityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
public class QualityServiceImpl implements QualityService {
    @Override
    public void assessQuality(ProductImage image, byte[] imageBytes) {
        log.debug("Quality assessment: id={}", image != null ? image.getId() : null);
        if (image != null) {
            image.setQualityScore(new BigDecimal("50.00"));
            image.setSharpnessScore(new BigDecimal("50.00"));
            image.setResolutionScore(new BigDecimal("50.00"));
        }
    }
}
