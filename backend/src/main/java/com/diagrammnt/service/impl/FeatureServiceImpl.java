package com.diagrammnt.service.impl;

import com.diagrammnt.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FeatureServiceImpl implements FeatureService {
    @Override
    public float[] extractFeature(byte[] imageBytes) {
        log.debug("Feature extraction: {} bytes", imageBytes != null ? imageBytes.length : 0);
        return new float[0];
    }

    @Override
    public float cosineSimilarity(float[] a, float[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return 0f;
        float dot = 0, normA = 0, normB = 0;
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) { dot += a[i] * b[i]; normA += a[i] * a[i]; normB += b[i] * b[i]; }
        return (float) (dot / (Math.sqrt(normA) * Math.sqrt(normB) + 1e-8));
    }
}
