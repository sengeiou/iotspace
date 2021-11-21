package com.iot.mapper;

import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EcategoryMapper {

    @Select({
            "${sql}"
    })
    List<Ecategory> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecategory> selectAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecategory> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Ecategory selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ecategoryv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Ecategoryv> selectVAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Ecategoryv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note",
            "from ecategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    Ecategoryv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into ecategory (id, name, ",
            "treatmentplantid, note)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{treatmentplantid,jdbcType=INTEGER}, ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ecategory record);

    @Update({
            "update ecategory",
            "set name = #{name,jdbcType=VARCHAR},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ecategory record);

    @Delete({
            "delete from ecategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ecategory",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}