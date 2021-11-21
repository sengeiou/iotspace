package com.iot.mapper;

import com.iot.bean.Eoperate;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EoperateMapper {
    @Select({
            "${sql}"
    })
    List<Eoperate> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, type",
            "from eoperate"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Eoperate> selectAll();

    @Select({
            "select",
            "id, item, file, time, note, type",
            "from eoperate",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Eoperate> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, type",
            "from eoperate",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    Eoperate selectByPrimaryKey(Integer id);

    @Insert({
        "insert into eoperate (id, item, ",
        "file, time, type, ",
        "note)",
        "values (#{id,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
        "#{file,jdbcType=VARCHAR},  ",
        "#{time,jdbcType=TIMESTAMP}, #{type,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Eoperate record);

    @Update({
        "update eoperate",
        "set item = #{item,jdbcType=VARCHAR},",
          "file = #{file,jdbcType=VARCHAR},",
           "type = #{type,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Eoperate record);

    @Delete({
            "delete from eoperate",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from eoperate",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}