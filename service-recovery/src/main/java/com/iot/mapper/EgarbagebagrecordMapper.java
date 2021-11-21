package com.iot.mapper;

import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EgarbagebagrecordMapper {
    @Select({
            "${sql}"
    })
    List<Egarbagebagrecord> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR)
    })
    List<Egarbagebagrecord> selectAll();

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR)
    })
    List<Egarbagebagrecord> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR)
    })
    Egarbagebagrecord selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Egarbagebagrecordv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    List<Egarbagebagrecordv> selectVAll();

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    List<Egarbagebagrecordv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, userid, garbagebagid, status, time, note, belongid, belongtype, belongname",
            "from egarbagebagrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column="garbagebagid", property="garbagebagid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "garbagebagid", property = "garbagebag",
                    one = @One(select = "com.iot.mapper.EgarbagebagMapper.selectVByPrimaryKey"))
    })
    Egarbagebagrecordv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into egarbagebagrecord (id, userid, ",
        "garbagebagid, status, ",
        "time, belongid, belongtype, belongname, note)",
        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{garbagebagid,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{belongid,jdbcType=INTEGER}, #{belongtype,jdbcType=VARCHAR}, #{belongname,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Egarbagebagrecord record);

    @Update({
        "update egarbagebagrecord",
        "set userid = #{userid,jdbcType=INTEGER},",
          "garbagebagid = #{garbagebagid,jdbcType=INTEGER},",
            "belongid = #{belongid,jdbcType=INTEGER},",
            "belongtype = #{belongtype,jdbcType=VARCHAR},",
            "belongname = #{belongname,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Egarbagebagrecord record);

    @Delete({
            "delete from egarbagebagrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from egarbagebagrecord",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}