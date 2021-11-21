package com.iot.mapper;

import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EinfusionbagMapper {
    @Select({
            "${sql}"
    })
    List<Einfusionbag> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Einfusionbag> selectAll();

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Einfusionbag> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Einfusionbag selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Einfusionbagv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    List<Einfusionbagv> selectVAll();

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    List<Einfusionbagv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, name, garbagebagid, time, status, note",
            "from einfusionbag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    Einfusionbagv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into einfusionbag (id, barcode, ",
        "name, garbagebagid, time, ",
        "status, note)",
        "values (#{id,jdbcType=INTEGER}, #{barcode,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{garbagebagid,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Einfusionbag record);

    @Update({
        "update einfusionbag",
        "set barcode = #{barcode,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "garbagebagid = #{garbagebagid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Einfusionbag record);

    @Delete({
            "delete from einfusionbag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from einfusionbag",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}