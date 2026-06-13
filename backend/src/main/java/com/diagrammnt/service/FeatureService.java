package com.diagrammnt.service;

public interface FeatureService {
    float[] extractFeature(byte[] imageBytes);
    float cosineSimilarity(float[] a, float[] b);
}
