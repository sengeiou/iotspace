package com.iot.mapper;

import com.iot.bean.Etradeuser;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtradeuserMapper {
    @Select({
            "${sql}"
    })
    List<Etradeuser> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, username, password, phone, type, province, time, note, role, realname, openid",
            "from etradeuser"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)

    })
    List<Etradeuser> selectAll();

    @Select({
            "select",
            "id, username, password, phone, type, province, time, note, role, realname, openid",
            "from etradeuser",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)
    })
    List<Etradeuser> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, username, password, phone, type, province, time, note, role, realname, openid",
            "from etradeuser",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="realname", property="realname", jdbcType=JdbcType.VARCHAR),
            @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR)
    })
    Etradeuser selectByPrimaryKey(Integer id);

    @Insert({
        "insert into etradeuser (id, username, password, ",
        "phone, type, realname, openid, ",
        "province, time, role, note)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{realname,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, #{role,jdbcType=VARCHAR},  #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etradeuser record);

    @Update({
        "update etradeuser",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
           "role = #{role,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
            "realname = #{realname,jdbcType=VARCHAR},",
            "openid = #{openid,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etradeuser record);

    @Delete({
            "delete from etradeuser",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etradeuser",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}