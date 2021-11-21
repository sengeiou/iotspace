package com.iot.mapper;

import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EtreatmentplantproductMapper {

    @Select({
            "${sql}"
    })
    List<Etreatmentplantproduct> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Etreatmentplantproduct> selectAll();

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Etreatmentplantproduct> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Etreatmentplantproduct selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etreatmentplantproductv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    List<Etreatmentplantproductv> selectVAll();

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    List<Etreatmentplantproductv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, weight, number, categoryid, time, treatmentplantid, note",
            "from etreatmentplantproduct",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "categoryid",property = "category",
                    one = @One(select = "com.iot.mapper.EcategoryMapper.selectByPrimaryKey"))
    })
    Etreatmentplantproductv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into etreatmentplantproduct (id, weight, number, categoryid, ",
            "time, treatmentplantid, note)",
            "values (#{id,jdbcType=INTEGER}, #{weight,jdbcType=DOUBLE}, #{number,jdbcType=INTEGER}, #{categoryid,jdbcType=INTEGER}, ",
            "#{time,jdbcType=TIMESTAMP}, #{treatmentplantid,jdbcType=INTEGER}, ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etreatmentplantproduct record);

    @Update({
            "update etreatmentplantproduct",
            "set weight = #{weight,jdbcType=DOUBLE},",
            "number = #{number,jdbcType=INTEGER},",
            "categoryid = #{categoryid,jdbcType=INTEGER},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etreatmentplantproduct record);

    @Delete({
            "delete from etreatmentplantproduct",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etreatmentplantproduct",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Select({
            "select sum(IFNULL(t2.weight,0)) value, t1.name name ",
            "from ecategory t1, etreatmentplantproduct t2 ",
            "where ${sql}"
    })
    List<Map<String,Object>> sumWeightGroupByCategory(@Param("sql") String sql);
}