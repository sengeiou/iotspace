package com.iot.mapper;

import com.iot.bean.Eprovince;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EprovinceMapper {
    @Select({
            "${sql}"
    })
    List<Eprovince> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, note",
            "from eprovince"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Eprovince> selectAll();

    @Select({
            "select",
            "id, item, note",
            "from eprovince",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Eprovince> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, note",
            "from eprovince",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Eprovince selectByPrimaryKey(Integer id);

    @Insert({
        "insert into eprovince (id, item, ",
        "note)",
        "values (#{id,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
        "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Eprovince record);

    @Update({
        "update eprovince",
        "set item = #{item,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Eprovince record);

    @Delete({
            "delete from eprovince",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from eprovince",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}