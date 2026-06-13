package com.diagrammnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diagrammnt.entity.ProductImage;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductImageMapper extends BaseMapper<ProductImage> {
    @Select("SELECT * FROM product_image WHERE file_md5 = #{md5} LIMIT 1")
    ProductImage findByMd5(@Param("md5") String md5);

    @Select("SELECT * FROM product_image WHERE file_md5 = #{md5} AND source_path = #{sourcePath} LIMIT 1")
    ProductImage findByMd5AndSource(@Param("md5") String md5, @Param("sourcePath") String sourcePath);

    @Select("SELECT * FROM product_image WHERE dhash IS NOT NULL AND status != 3")
    List<ProductImage> findWithDhash();

    @Select("SELECT * FROM product_image WHERE dhash = #{dhash} AND status != 3")
    List<ProductImage> findByDhash(@Param("dhash") String dhash);

    @Select("SELECT * FROM product_image WHERE feature_vector IS NOT NULL AND status != 3")
    List<ProductImage> findWithFeatureVector();

    @Select("<script>SELECT * FROM product_image WHERE 1=1 <if test='status != null'>AND status = #{status}</if> <if test='status == null'>AND status != 3</if> <if test='format != null and format != \"\"'>AND format = #{format}</if> <if test='isDuplicate != null'>AND is_duplicate = #{isDuplicate}</if> <if test='keyword != null and keyword != \"\"'>AND (file_name LIKE CONCAT('%', #{keyword}, '%') OR source_path LIKE CONCAT('%', #{keyword}, '%'))</if> ORDER BY created_at DESC</script>")
    IPage<ProductImage> queryPage(Page<ProductImage> page, @Param("status") Integer status, @Param("format") String format, @Param("isDuplicate") Integer isDuplicate, @Param("keyword") String keyword);

    @Select("SELECT * FROM product_image WHERE status = 2 AND quality_score IS NOT NULL ORDER BY quality_score ASC")
    List<ProductImage> findLowQualityImages();

    @Select("SELECT status, COUNT(*) as cnt FROM product_image GROUP BY status")
    List<Map<String, Object>> countByStatus();

    @Select("SELECT format, COUNT(*) as cnt FROM product_image WHERE status != 3 GROUP BY format")
    List<Map<String, Object>> countByFormat();

    @Select("SELECT * FROM product_image WHERE dhash IS NULL AND status != 3 LIMIT #{limit}")
    List<ProductImage> findPendingDhash(@Param("limit") int limit);

    @Select("SELECT * FROM product_image WHERE feature_vector IS NULL AND status != 3 LIMIT #{limit}")
    List<ProductImage> findPendingFeature(@Param("limit") int limit);

    @Select("SELECT * FROM product_image WHERE quality_score IS NULL AND status != 3 LIMIT #{limit}")
    List<ProductImage> findPendingQuality(@Param("limit") int limit);

    @Select("SELECT * FROM product_image WHERE thumbnail_path IS NULL AND status != 3 LIMIT #{limit}")
    List<ProductImage> findPendingThumbnail(@Param("limit") int limit);

    @Update("UPDATE product_image SET dhash = #{dhash}, updated_at = NOW() WHERE id = #{id}")
    int updateDhash(@Param("id") Long id, @Param("dhash") String dhash);

    @Update("UPDATE product_image SET feature_vector = #{fv}, feature_model = #{fm}, updated_at = NOW() WHERE id = #{id}")
    int updateFeatureVector(@Param("id") Long id, @Param("fv") byte[] fv, @Param("fm") String fm);

    @Update("UPDATE product_image SET is_duplicate = 1, duplicate_of = #{dup}, updated_at = NOW() WHERE id = #{id}")
    int markAsDuplicate(@Param("id") Long id, @Param("dup") Long dup);

    @Update("UPDATE product_image SET is_duplicate = 0, duplicate_of = NULL, updated_at = NOW() WHERE is_duplicate = 1 AND duplicate_of IS NOT NULL")
    int unmarkPerceptualDuplicates();

    @Update("UPDATE product_image SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Select("SELECT * FROM product_image WHERE status = #{status} ORDER BY created_at DESC")
    List<ProductImage> findByStatus(@Param("status") Integer status);
}
