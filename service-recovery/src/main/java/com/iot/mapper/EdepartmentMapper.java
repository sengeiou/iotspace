package com.iot.mapper;

import com.iot.bean.Edepartment;
import com.iot.bean.Edepartmentv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EdepartmentMapper {
    @Select({
            "${sql}"
    })
    List<Edepartment> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Edepartment> selectAll();

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Edepartment> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Edepartment selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Edepartmentv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Edepartmentv> selectVAll();

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Edepartmentv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, hospitalid, leadername, leaderphone, note",
            "from edepartment",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    Edepartmentv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into edepartment (id, name, ",
        "hospitalid, leadername, ",
        "leaderphone, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{hospitalid,jdbcType=INTEGER}, #{leadername,jdbcType=VARCHAR}, ",
        "#{leaderphone,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Edepartment record);

    @Update({
        "update edepartment",
        "set name = #{name,jdbcType=VARCHAR},",
          "hospitalid = #{hospitalid,jdbcType=INTEGER},",
          "leadername = #{leadername,jdbcType=VARCHAR},",
          "leaderphone = #{leaderphone,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Edepartment record);

    @Delete({
            "delete from edepartment",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from edepartment",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /*医院科室数量*/
    @Select({
            "select count(1) count",
            "from edepartment",
            "where hospitalid = #{hospitalid,jdbcType=INTEGER}"
    })
    Integer countByhospitalid(Integer hospitalid);
}