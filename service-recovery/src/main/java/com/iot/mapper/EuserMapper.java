package com.iot.mapper;

import com.iot.bean.Euser;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EuserMapper {
    @Select({
            "${sql}"
    })
    List<Euser> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, username, password, phone, type, belongid, time, note, role, realname, openid",
            "from euser"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)

    })
    List<Euser> selectAll();

    @Select({
            "select",
            "id, username, password, phone, type, belongid, time, note, role, realname, openid",
            "from euser",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)
    })
    List<Euser> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, username, password, phone, type, belongid, time, note, role, realname, openid",
            "from euser",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)
    })
    Euser selectByPrimaryKey(Integer id);

    @Insert({
        "insert into euser (id, username, password, ",
        "phone, type, realname, openid, ",
        "belongid, time, role, note)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{realname,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, ",
        "#{belongid,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, #{role,jdbcType=VARCHAR},  #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Euser record);

    @Update({
        "update euser",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
           "role = #{role,jdbcType=VARCHAR},",
          "belongid = #{belongid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
            "realname = #{realname,jdbcType=VARCHAR},",
            "openid = #{openid,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Euser record);

    @Delete({
            "delete from euser",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from euser",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}