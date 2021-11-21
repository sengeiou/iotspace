package com.iot.mapper;

import com.iot.bean.Enterprise;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EnterpriseMapper {
    @Select({
            "${sql}"
    })
    List<Enterprise> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, code, username, type, dealtype, phone, time, address, department, departmentphone, note, province, city, plat",
            "from enterprise"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="dealtype", property="dealtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentphone", property="departmentphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    List<Enterprise> selectAll();

    @Select({
            "select",
            "id, name, code, username, type, dealtype, phone, time, address, department, departmentphone, note, province, city, plat",
            "from enterprise",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="dealtype", property="dealtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentphone", property="departmentphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    List<Enterprise> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, code, username, type, dealtype, phone, time, address, department, departmentphone, note, province, city, plat",
            "from enterprise",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="dealtype", property="dealtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentphone", property="departmentphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    Enterprise selectByPrimaryKey(Integer id);

    @Insert({
        "insert into enterprise (id, name, code, ",
        "username, type, dealtype, phone, province, city, plat, ",
        "time, address, department, departmentphone, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{dealtype,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{plat,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP}, #{address,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR}, #{departmentphone,jdbcType=VARCHAR},  #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Enterprise record);

    @Update({
        "update enterprise",
        "set name = #{name,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
           "dealtype = #{dealtype,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "address = #{address,jdbcType=VARCHAR},",
         "department = #{department,jdbcType=VARCHAR},",
          "departmentphone = #{departmentphone,jdbcType=VARCHAR},",
           "province = #{province,jdbcType=VARCHAR},",
           "city = #{city,jdbcType=VARCHAR},",
           "plat = #{plat,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Enterprise record);

    @Delete({
            "delete from enterprise",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from enterprise",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}