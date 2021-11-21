package com.iot.mapper;

import com.iot.bean.Eproductionplant;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EproductionplantMapper {
    @Select({
            "${sql}"
    })
    List<Eproductionplant> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img",
            "from eproductionplant"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
    })
    List<Eproductionplant> selectAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img",
            "from eproductionplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
    })
    List<Eproductionplant> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img",
            "from eproductionplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR)
    })
    Eproductionplant selectByPrimaryKey(Integer id);

    @Insert({
        "insert into eproductionplant (id, name, address, ",
        "leadername, leaderphone, img, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{leadername,jdbcType=VARCHAR},",
        "#{leaderphone,jdbcType=VARCHAR},#{img,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Eproductionplant record);

    @Update({
        "update eproductionplant",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "leadername = #{leadername,jdbcType=VARCHAR},",
          "leaderphone = #{leaderphone,jdbcType=VARCHAR},",
            "img = #{img,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Eproductionplant record);

    @Delete({
            "delete from eproductionplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from eproductionplant",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}