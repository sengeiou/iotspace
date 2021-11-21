package com.iot.mapper;

import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EnoticeMapper {
    @Select({
            "${sql}"
    })
    List<Enotice> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    List<Enotice> selectAll();

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    List<Enotice> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    Enotice selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Enoticev> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey"))
    })
    List<Enoticev> selectVAll();

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey"))
    })
    List<Enoticev> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, file, time, note, title, img, importance, type, userid",
            "from enotice",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="importance", property="importance", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
            @Result(column = "userid", property = "user",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey"))
    })
    Enoticev selectVByPrimaryKey(Integer id);


    @Insert({
        "insert into enotice (id, item, title, img, importance, ",
        "file, time, type, userid, ",
        "note)",
        "values (#{id,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR},  #{img,jdbcType=VARCHAR}, #{importance,jdbcType=VARCHAR},",
        "#{file,jdbcType=VARCHAR},  ",
        "#{time,jdbcType=TIMESTAMP}, #{type,jdbcType=VARCHAR}, #{userid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Enotice record);

    @Update({
        "update enotice",
        "set item = #{item,jdbcType=VARCHAR},",
          "file = #{file,jdbcType=VARCHAR},",
         "title = #{title,jdbcType=VARCHAR},",
         "img = #{img,jdbcType=VARCHAR},",
         "importance = #{importance,jdbcType=VARCHAR},",
         "type = #{type,jdbcType=VARCHAR},",
         "userid = #{userid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Enotice record);

    @Delete({
            "delete from enotice",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from enotice",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}