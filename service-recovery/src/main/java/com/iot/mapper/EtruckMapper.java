package com.iot.mapper;

import com.iot.bean.Etruck;
import com.iot.bean.Etruckv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtruckMapper {
    @Select({
            "${sql}"
    })
    List<Etruck> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruck> selectAll();

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruck> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER)
    })
    Etruck selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etruckv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck"
    })
    @Results(value = {
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey")),
            @Result(column = "vehicleid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EvehicleMapper.selectByPrimaryKey"))
    })
    List<Etruckv> selectVAll();

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey")),
            @Result(column = "vehicleid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EvehicleMapper.selectByPrimaryKey"))
    })
    List<Etruckv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, vehicleid, time, userid, note, status, type, transportcompanyid",
            "from etruck",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="vehicleid", property="vehicleid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey")),
            @Result(column = "vehicleid", property = "vehicle",
                    one = @One(select = "com.iot.mapper.EvehicleMapper.selectByPrimaryKey"))
    })
    Etruckv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into etruck (id, vehicleid, time, ",
        "userid, transportcompanyid, ",
        "status, type, note)",
        "values (#{id,jdbcType=INTEGER}, #{vehicleid,jdbcType=INTEGER}, ",
        "#{time,jdbcType=TIMESTAMP}, ",
        "#{userid,jdbcType=INTEGER}, #{transportcompanyid,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR},",
        "#{type,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etruck record);

    @Update({
        "update etruck",
        "set vehicleid = #{vehicleid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "userid = #{userid,jdbcType=INTEGER},",
            "status = #{status,jdbcType=VARCHAR},",
            "type = #{type,jdbcType=VARCHAR},",
            "transportcompanyid = #{transportcompanyid,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etruck record);

    @Delete({
            "delete from etruck",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etruck",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}