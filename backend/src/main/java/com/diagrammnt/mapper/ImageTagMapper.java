package com.diagrammnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diagrammnt.entity.ImageTag;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ImageTagMapper extends BaseMapper<ImageTag> {
    @Select("SELECT * FROM image_tag WHERE image_id = #{imageId}")
    List<ImageTag> findByImageId(@Param("imageId") Long imageId);

    @Delete("DELETE FROM image_tag WHERE image_id = #{imageId}")
    int deleteByImageId(@Param("imageId") Long imageId);
}
