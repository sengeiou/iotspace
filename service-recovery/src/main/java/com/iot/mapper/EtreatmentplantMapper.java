package com.iot.mapper;

import com.iot.bean.Etreatmentplant;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtreatmentplantMapper {
    @Select({
            "${sql}"
    })
    List<Etreatmentplant> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, code, socialcode, batch, province, city, plat",
            "from etreatmentplant"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="socialcode", property="socialcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="batch", property="batch", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    List<Etreatmentplant> selectAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, code, socialcode, batch, province, city, plat",
            "from etreatmentplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="socialcode", property="socialcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="batch", property="batch", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    List<Etreatmentplant> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, code, socialcode, batch, province, city, plat",
            "from etreatmentplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="socialcode", property="socialcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="batch", property="batch", jdbcType=JdbcType.VARCHAR),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR)
    })
    Etreatmentplant selectByPrimaryKey(Integer id);

    @Insert({
        "insert into etreatmentplant (id, name, address, code, socialcode, batch, province, city, plat, ",
        "leadername, leaderphone, img, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{socialcode,jdbcType=VARCHAR}, #{batch,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{plat,jdbcType=VARCHAR}, #{leadername,jdbcType=VARCHAR},",
        "#{leaderphone,jdbcType=VARCHAR}, #{img,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etreatmentplant record);

    @Update({
        "update etreatmentplant",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "leadername = #{leadername,jdbcType=VARCHAR},",
          "leaderphone = #{leaderphone,jdbcType=VARCHAR},",
          "img = #{img,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "socialcode = #{socialcode,jdbcType=VARCHAR},",
          "batch = #{batch,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "plat = #{plat,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etreatmentplant record);

    @Delete({
            "delete from etreatmentplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etreatmentplant",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Update({
            "update etreatmentplant",
            "set province = #{province,jdbcType=VARCHAR},",
            "city = #{city,jdbcType=VARCHAR},",
            "plat = #{plat,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateProvince(Etreatmentplant record);
}