package com.iot.mapper;

import com.iot.bean.Egmaterialout;
import com.iot.bean.Egmaterialoutv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EgmaterialoutMapper {
    @Select({
            "${sql}"
    })
    List<Egmaterialout> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER)
    })
    List<Egmaterialout> selectAll();

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER)
    })
    List<Egmaterialout> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER)
    })
    Egmaterialout selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Egmaterialoutv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout"
    })
    @Results(value = {
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "gmaterialcategoryid", property = "gmaterialcategory",
                    one = @One(select = "com.iot.mapper.EgmaterialcategoryMapper.selectByPrimaryKey"))
    })
    List<Egmaterialoutv> selectVAll();

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "gmaterialcategoryid", property = "gmaterialcategory",
                    one = @One(select = "com.iot.mapper.EgmaterialcategoryMapper.selectByPrimaryKey"))
    })
    List<Egmaterialoutv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, treatmentplantid, gmaterialcategoryid, number, weight, source, time, note,dataid",
            "from egmaterialout",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="gmaterialcategoryid", property="gmaterialcategoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="dataid", property="dataid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey")),
            @Result(column = "gmaterialcategoryid", property = "gmaterialcategory",
                    one = @One(select = "com.iot.mapper.EgmaterialcategoryMapper.selectByPrimaryKey"))

    })
    Egmaterialoutv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into egmaterialout (id, treatmentplantid, gmaterialcategoryid, ",
        "number, weight, ",
        "source, time, dataid, note)",
        "values (#{id,jdbcType=INTEGER}, #{treatmentplantid,jdbcType=INTEGER}, ",
        "#{gmaterialcategoryid,jdbcType=INTEGER}, ",
        "#{number,jdbcType=INTEGER}, #{weight,jdbcType=DOUBLE}, #{source,jdbcType=VARCHAR},",
        "#{time,jdbcType=TIMESTAMP}, #{dataid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Egmaterialout record);

    @Update({
        "update egmaterialout",
        "set treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
          "gmaterialcategoryid = #{gmaterialcategoryid,jdbcType=INTEGER},",
          "dataid = #{dataid,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
            "weight = #{weight,jdbcType=DOUBLE},",
            "source = #{source,jdbcType=VARCHAR},",
            "time = #{time,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Egmaterialout record);

    @Delete({
            "delete from egmaterialout",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from egmaterialout",
            "where id out ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /**
     * 出库重量按照分类统计
     * @param sql
     * @return
     */
    @Select({
            "select sum(IFNULL(t1.weight,0)) value, t2.name name ",
            "from egmaterialout t1, egmaterialcategory t2 ",
            "where t1.gmaterialcategoryid = t2.id  and ${sql} ",
            "GROUP BY t2.name"
    })
    List<Map<String,Object>> sumWeightGroupByCategory(@Param("sql") String sql);

    /**
     * 出库重量统计 按照去向分组
     * @param sql
     * @return
     */
    @Select({
            "select sum(IFNULL(weight,0)) value, source name ",
            "from egmaterialout ",
            "where ${sql} ",
            "group by source"
    })
    List<Map<String,Object>> sumWeightGroupBySource(@Param("sql") String sql);

    /**
     *  某一类别出库，按月统计
     * @param treatmentplantid
     * @param gmaterialcategoryid
     * @param year
     * @return
     */
    @Select({
            "select sum(IFNULL(t1.weight,0)) value, DATE_FORMAT(t1.time,'%m') name ",
            "from egmaterialout t1, egmaterialcategory t2 ",
            "where t1.gmaterialcategoryid = t2.id ",
            "  and t1.treatmentplantid = #{treatmentplantid,jdbcType=INTEGER} ",
            "  and t2.id = #{gmaterialcategoryid,jdbcType=INTEGER} ",
            "  and year(t1.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year)) ",
            "group by DATE_FORMAT(t1.time,'%m')"
    })
    List<Map<String,Object>> sumWeightGroupByMonth(@Param("treatmentplantid") Integer treatmentplantid, @Param("gmaterialcategoryid") Integer gmaterialcategoryid, @Param("year") Integer year);}