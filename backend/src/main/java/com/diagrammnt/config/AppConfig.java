package com.diagrammnt.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Configuration
public class AppConfig {
    private String nasOriginalsFullPath;
    private String nasThumbnailsFullPath;
    private String nasPreviewsFullPath;
    private int localThumbnailSize;
    private int localPreviewMaxSize;
    private String imageAllowedFormats;
    private long imageMaxUploadSizeMb;

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.configure().directory(System.getProperty("user.dir")).filename("diagram.env").load();
        String nasPath = dotenv.get("NAS_MOUNT_PATH", "/mnt/nas/product-images");
        nasOriginalsFullPath = nasPath + "/originals";
        nasThumbnailsFullPath = nasPath + "/thumbnails";
        nasPreviewsFullPath = nasPath + "/previews";
        localThumbnailSize = Integer.parseInt(dotenv.get("THUMBNAIL_SIZE", "300"));
        localPreviewMaxSize = Integer.parseInt(dotenv.get("PREVIEW_MAX_SIZE", "1200"));
        imageAllowedFormats = dotenv.get("IMAGE_ALLOWED_FORMATS", "jpg,jpeg,png,gif,bmp,webp,psd,tiff,tif");
        imageMaxUploadSizeMb = Long.parseLong(dotenv.get("IMAGE_MAX_UPLOAD_SIZE_MB", "50"));
        log.info("AppConfig loaded: nasPath={}", nasPath);
    }
}
