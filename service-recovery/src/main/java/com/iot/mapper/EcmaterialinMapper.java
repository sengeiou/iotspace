package com.iot.mapper;

import com.iot.bean.Ecmaterialin;
import com.iot.bean.Ecmaterialinv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EcmaterialinMapper {
    @Select({
            "${sql}"
    })
    List<Ecmaterialin> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecmaterialin> selectAll();

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Ecmaterialin> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    Ecmaterialin selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ecmaterialinv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin"
    })
    @Results(value = {
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "cmaterialcategoryid", property = "cmaterialcategory",
                    one = @One(select = "com.iot.mapper.EcmaterialcategoryMapper.selectByPrimaryKey"))
    })
    List<Ecmaterialinv> selectVAll();

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "cmaterialcategoryid", property = "cmaterialcategory",
                    one = @One(select = "com.iot.mapper.EcmaterialcategoryMapper.selectByPrimaryKey"))
    })
    List<Ecmaterialinv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, cmaterialcategoryid, number, weight, source, time, note",
            "from ecmaterialin",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="cmaterialcategoryid", property="cmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "cmaterialcategoryid", property = "cmaterialcategory",
                    one = @One(select = "com.iot.mapper.EcmaterialcategoryMapper.selectByPrimaryKey"))
    })
    Ecmaterialinv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into ecmaterialin (id, treatmentplantid, cmaterialcategoryid, ",
        "number, weight, ",
        "source, time, note)",
        "values (#{id,jdbcType=INTEGER}, #{treatmentplantid,jdbcType=INTEGER}, ",
        "#{cmaterialcategoryid,jdbcType=INTEGER}, ",
        "#{number,jdbcType=INTEGER}, #{weight,jdbcType=DOUBLE}, #{source,jdbcType=VARCHAR},",
        "#{time,jdbcType=TIMESTAMP}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ecmaterialin record);

    @Update({
        "update ecmaterialin",
        "set treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
          "cmaterialcategoryid = #{cmaterialcategoryid,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
            "weight = #{weight,jdbcType=DOUBLE},",
            "source = #{source,jdbcType=VARCHAR},",
            "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ecmaterialin record);

    @Delete({
            "delete from ecmaterialin",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ecmaterialin",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /**
     * 入库重量按照分类统计
     * @param sql
     * @return
     */
    @Select({
            "select sum(IFNULL(t1.weight,0)) value, t2.name name ",
            "from ecmaterialin t1, ecmaterialcategory t2 ",
            "where t1.cmaterialcategoryid = t2.id  and ${sql} ",
            "GROUP BY t2.name"
    })
    List<Map<String,Object>> sumWeightGroupByCategory(@Param("sql") String sql);

    /**
     * 入库重量统计 按照来源分组
     * @param sql
     * @return
     */
    @Select({
            "select sum(IFNULL(weight,0)) value, source name ",
            "from ecmaterialin ",
            "where ${sql} ",
            "group by source"
    })
    List<Map<String,Object>> sumWeightGroupBySource(@Param("sql") String sql);

    /**
     *  某一类别入库，按月统计
     * @param treatmentplantid
     * @param cmaterialcategoryid
     * @param year
     * @return
     */
    @Select({
            "select sum(IFNULL(t1.weight,0)) value, DATE_FORMAT(t1.time,'%m') name ",
            "from ecmaterialin t1, ecmaterialcategory t2 ",
            "where t1.cmaterialcategoryid = t2.id ",
            "  and t1.treatmentplantid = #{treatmentplantid,jdbcType=INTEGER} ",
            "  and t2.id = #{cmaterialcategoryid,jdbcType=INTEGER} ",
            "  and year(t1.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year)) ",
            "group by DATE_FORMAT(t1.time,'%m')"
    })
    List<Map<String,Object>> sumWeightGroupByMonth(@Param("treatmentplantid") Integer treatmentplantid,@Param("cmaterialcategoryid") Integer cmaterialcategoryid,@Param("year") Integer year);}