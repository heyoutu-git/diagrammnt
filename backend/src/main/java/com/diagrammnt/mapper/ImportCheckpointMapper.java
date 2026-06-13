package com.diagrammnt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diagrammnt.entity.ImportCheckpoint;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ImportCheckpointMapper extends BaseMapper<ImportCheckpoint> {
    @Select("SELECT source_path FROM import_checkpoint WHERE task_id = #{taskId}")
    List<String> findSourcePathsByTaskId(@Param("taskId") String taskId);

    @Select("SELECT COUNT(*) FROM import_checkpoint WHERE task_id = #{taskId}")
    int countByTaskId(@Param("taskId") String taskId);

    @Select("SELECT task_id FROM import_checkpoint GROUP BY task_id ORDER BY MAX(created_at) DESC")
    List<String> findTaskIdsByDirPattern();

    @Delete("DELETE FROM import_checkpoint WHERE task_id = #{taskId}")
    int deleteByTaskId(@Param("taskId") String taskId);
}
