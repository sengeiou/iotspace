package com.iot.mapper;

import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EbarcodeMapper {
    @Select({
            "${sql}"
    })
    List<Ebarcode> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ebarcode> selectAll();

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ebarcode> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Ebarcode selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ebarcodev> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Ebarcodev> selectVAll();

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))

    })
    List<Ebarcodev> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, hospitalid, number, time, note",
            "from ebarcode",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    Ebarcodev selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into ebarcode (id, hospitalid, number, time, ",
        "note)",
        "values (#{id,jdbcType=INTEGER}, #{hospitalid,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, ",
        "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ebarcode record);

    @Update({
        "update ebarcode",
        "set hospitalid = #{hospitalid,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ebarcode record);

    @Delete({
            "delete from ebarcode",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ebarcode",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}