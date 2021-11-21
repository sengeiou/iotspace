package com.iot.mapper;

import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EvehicleMapper {
    @Select({
            "${sql}"
    })
    List<Evehicle> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Evehicle> selectAll();

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Evehicle> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Evehicle selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Evehiclev> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "transportcompanyid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectByPrimaryKey"))
    })
    List<Evehiclev> selectVAll();

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "transportcompanyid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectByPrimaryKey"))
    })
    List<Evehiclev> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, number, transportcompanyid, note",
            "from evehicle",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "transportcompanyid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectByPrimaryKey"))
    })
    Evehiclev selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into evehicle (id, number, ",
        "transportcompanyid, note)",
        "values (#{id,jdbcType=INTEGER}, #{number,jdbcType=VARCHAR}, ",
        "#{transportcompanyid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Evehicle record);

    @Update({
        "update evehicle",
        "set number = #{number,jdbcType=VARCHAR},",
          "transportcompanyid = #{transportcompanyid,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Evehicle record);

    @Delete({
            "delete from evehicle",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from evehicle",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /*sql查询车辆数量*/
    @Select({
            "select count(1) count from evehicle",
            "where ${sql}"

    })
    Integer vehicleNumBySql(@Param("sql") String sql);

}