package com.iot.mapper;

import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EcityMapper {

    @Select({
            "${sql}"
    })
    List<Ecity> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecity> selectAll();

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecity> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Ecity selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ecityv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "provinceid",property = "province",
                    one = @One(select = "com.iot.mapper.EprovinceMapper.selectByPrimaryKey"))
    })
    List<Ecityv> selectVAll();

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "provinceid",property = "province",
                    one = @One(select = "com.iot.mapper.EprovinceMapper.selectByPrimaryKey"))
    })
    List<Ecityv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, item, provinceid, note",
            "from ecity",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="provinceid", property="provinceid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "provinceid",property = "province",
                    one = @One(select = "com.iot.mapper.EprovinceMapper.selectByPrimaryKey"))
    })
    Ecityv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into ecity (id, item, ",
            "provinceid, note)",
            "values (#{id,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
            "#{provinceid,jdbcType=INTEGER}, ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ecity record);

    @Update({
            "update ecity",
            "set item = #{item,jdbcType=VARCHAR},",
            "provinceid = #{provinceid,jdbcType=INTEGER},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ecity record);

    @Delete({
            "delete from ecity",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ecity",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}