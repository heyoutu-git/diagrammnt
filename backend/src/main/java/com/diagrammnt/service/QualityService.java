package com.diagrammnt.service;

import com.diagrammnt.entity.ProductImage;

public interface QualityService {
    void assessQuality(ProductImage image, byte[] imageBytes);
}
