package com.diagrammnt.util;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.util.*;

@Slf4j
public class FileUtil {
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("jpg","jpeg","png","psd","webp","bmp","gif","tiff","tif");
    private static final Set<String> SKIP_DIRS = new HashSet<>(Arrays.asList("$recycle.bin","system volume information","recovery","windows","program files","program files (x86)","programdata","msocache","config.msi","thumbnails","previews","temp","recycle"));

    public static List<Path> scanImageFiles(Path dirPath) { return scanImageFiles(dirPath, Collections.emptySet()); }

    public static List<Path> scanImageFiles(Path dirPath, Set<String> skipPaths) {
        if (dirPath == null || !Files.exists(dirPath)) throw new IllegalArgumentException("目录不存在: " + dirPath);
        if (!Files.isDirectory(dirPath)) throw new IllegalArgumentException("不是目录: " + dirPath);
        final Set<String> skip = skipPaths != null ? skipPaths : Collections.emptySet();
        List<Path> files = new ArrayList<>();
        try {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                public FileVisitResult preVisitDirectory(Path d, BasicFileAttributes a) {
                    Path fn = d.getFileName();
                    if (fn == null) return FileVisitResult.CONTINUE;
                    String dn = fn.toString().toLowerCase();
                    if (SKIP_DIRS.contains(dn) || dn.startsWith(".") || !Files.isReadable(d)) return FileVisitResult.SKIP_SUBTREE;
                    return FileVisitResult.CONTINUE;
                }
                public FileVisitResult visitFile(Path f, BasicFileAttributes a) {
                    if (!Files.isRegularFile(f) || !Files.isReadable(f)) return FileVisitResult.CONTINUE;
                    if (!skip.isEmpty() && skip.contains(f.toAbsolutePath().normalize().toString())) return FileVisitResult.CONTINUE;
                    String n = f.getFileName().toString().toLowerCase();
                    for (String ext : IMAGE_EXTENSIONS) if (n.endsWith("." + ext)) { files.add(f); break; }
                    return FileVisitResult.CONTINUE;
                }
                public FileVisitResult visitFileFailed(Path f, IOException e) { return FileVisitResult.SKIP_SUBTREE; }
            });
        } catch (IOException e) { throw new RuntimeException("扫描失败: " + dirPath, e); }
        log.info("[SCAN] {} files found, {} skipped", files.size(), skip.size());
        return files;
    }

    public static Path ensureDirectory(Path dir) {
        try { if (!Files.exists(dir)) Files.createDirectories(dir); return dir; } catch (IOException e) { throw new RuntimeException("创建目录失败: " + dir, e); }
    }

    public static String getExtension(String name) {
        if (name == null || !name.contains(".")) throw new IllegalArgumentException("无法获取扩展名: " + name);
        return name.substring(name.lastIndexOf('.') + 1).toLowerCase();
    }

    public static String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1048576) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1073741824) return String.format("%.1f MB", bytes / 1048576.0);
        return String.format("%.2f GB", bytes / 1073741824.0);
    }
}
