package com.iot.mapper;

import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EplatMapper {
    @Select({
            "${sql}"
    })
    List<Eplat> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Eplat> selectAll();

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Eplat> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Eplat selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Eplatv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "cityid",property = "city",
                    one = @One(select = "com.iot.mapper.EcityMapper.selectVByPrimaryKey"))
    })
    List<Eplatv> selectVAll();

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "cityid",property = "city",
                    one = @One(select = "com.iot.mapper.EcityMapper.selectVByPrimaryKey"))
    })
    List<Eplatv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, cityid, longitude, latitude, note",
            "from eplat",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="cityid", property="cityid", jdbcType=JdbcType.INTEGER),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "cityid",property = "city",
                    one = @One(select = "com.iot.mapper.EcityMapper.selectVByPrimaryKey"))
    })
    Eplatv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into eplat (id, item, ",
        "cityid, longitude, ",
        "latitude, note)",
        "values (#{id,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
        "#{cityid,jdbcType=INTEGER}, #{longitude,jdbcType=VARCHAR}, ",
        "#{latitude,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Eplat record);

    @Update({
        "update eplat",
        "set item = #{item,jdbcType=VARCHAR},",
          "cityid = #{cityid,jdbcType=INTEGER},",
          "longitude = #{longitude,jdbcType=VARCHAR},",
          "latitude = #{latitude,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Eplat record);

    @Delete({
            "delete from eplat",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from eplat",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}