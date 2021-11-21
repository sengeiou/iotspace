package com.iot.mapper;

import com.iot.bean.Esupervisionunit;
import com.iot.bean.Esupervisionunitv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EsupervisionunitMapper {
    @Select({
            "${sql}"
    })
    List<Esupervisionunit> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Esupervisionunit> selectAll();

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Esupervisionunit> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Esupervisionunit selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Esupervisionunitv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "pid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey"))
    })
    List<Esupervisionunitv> selectVAll();

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "pid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey"))
    })
    List<Esupervisionunitv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, level, pid, note",
            "from esupervisionunit",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "pid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey"))
    })
    Esupervisionunitv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into esupervisionunit (id, name, ",
        "address, level, pid, ",
        "note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, #{pid,jdbcType=INTEGER}, ",
        "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Esupervisionunit record);

    @Update({
        "update esupervisionunit",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Esupervisionunit record);

    @Delete({
            "delete from esupervisionunit",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from esupervisionunit",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}