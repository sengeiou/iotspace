package com.iot.mapper;

import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtreatmentplantoutboundrecordMapper {

    @Select({
            "${sql}"
    })
    List<Etreatmentplantoutboundrecord> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etreatmentplantoutboundrecord> selectAll();

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etreatmentplantoutboundrecord> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    Etreatmentplantoutboundrecord selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etreatmentplantoutboundrecordv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    List<Etreatmentplantoutboundrecordv> selectVAll();

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    List<Etreatmentplantoutboundrecordv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, trucktreatmentplantreceiveid, note, treatmentplantid",
            "from etreatmentplantoutboundrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    Etreatmentplantoutboundrecordv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into etreatmentplantoutboundrecord (id, weight, number, categoryid, ",
            "time, trucktreatmentplantreceiveid, treatmentplantid, note)",
            "values (#{id,jdbcType=INTEGER}, #{weight,jdbcType=DOUBLE}, #{number,jdbcType=INTEGER}, #{categoryid,jdbcType=INTEGER}, ",
            "#{time,jdbcType=TIMESTAMP}, #{trucktreatmentplantreceiveid,jdbcType=INTEGER}, #{treatmentplantid,jdbcType=INTEGER}, ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etreatmentplantoutboundrecord record);

    @Update({
            "update etreatmentplantoutboundrecord",
            "set weight = #{weight,jdbcType=DOUBLE},",
            "number = #{number,jdbcType=INTEGER},",
            "categoryid = #{categoryid,jdbcType=INTEGER},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "trucktreatmentplantreceiveid = #{trucktreatmentplantreceiveid,jdbcType=INTEGER},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etreatmentplantoutboundrecord record);

    @Delete({
            "delete from etreatmentplantoutboundrecord",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etreatmentplantoutboundrecord",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}