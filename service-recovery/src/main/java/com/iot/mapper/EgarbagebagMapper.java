package com.iot.mapper;

import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EgarbagebagMapper {
    @Select({
            "${sql}"
    })
    List<Egarbagebag> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Egarbagebag> selectAll();

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Egarbagebag> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    Egarbagebag selectByPrimaryKey(Integer id);

    @Select({
            "select garbagebag.id id, garbagebag.barcode barcode, garbagebag.departmentid departmentid,garbagebag.status status,",
            "garbagebag.type type, garbagebag.belongid belongid, garbagebag.belongtype belongtype, garbagebag.belongname belongname, garbagebag.time time, garbagebag.note note",
            "from ehospital hospital, edepartment department, egarbagebag garbagebag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<Egarbagebag> selectByHospital(@Param("sql") String sql);

    @Select({
            "${sql}"
    })
    List<Egarbagebagv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column = "departmentid", property = "department",
                    one = @One(select = "com.iot.mapper.EdepartmentMapper.selectVByPrimaryKey"))
    })
    List<Egarbagebagv> selectVAll();

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column = "departmentid", property = "department",
                    one = @One(select = "com.iot.mapper.EdepartmentMapper.selectVByPrimaryKey"))
    })
    List<Egarbagebagv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, barcode, departmentid, status, belongid, belongtype, belongname, time, note, type",
            "from egarbagebag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column = "departmentid", property = "department",
                    one = @One(select = "com.iot.mapper.EdepartmentMapper.selectVByPrimaryKey"))
    })
    Egarbagebagv selectVByPrimaryKey(Integer id);

    @Select({
            "select garbagebag.id id, garbagebag.barcode barcode, garbagebag.departmentid departmentid,garbagebag.status status,",
            "garbagebag.type type, garbagebag.belongid belongid, garbagebag.belongtype belongtype, garbagebag.belongname belongname, garbagebag.time time, garbagebag.note note",
            "from ehospital hospital, edepartment department, egarbagebag garbagebag",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="departmentid", property="departmentid", jdbcType=JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongid", property="belongid", jdbcType=JdbcType.INTEGER),
            @Result(column="belongtype", property="belongtype", jdbcType=JdbcType.VARCHAR),
            @Result(column="belongname", property="belongname", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column = "departmentid", property = "department",
                    one = @One(select = "com.iot.mapper.EdepartmentMapper.selectVByPrimaryKey"))
    })
    List<Egarbagebagv> selectVByHospital(@Param("sql") String sql);

    @Insert({
        "insert into egarbagebag (id, barcode, ",
        "departmentid, status, belongid, belongtype, belongname,  ",
        "time, type, note)",
        "values (#{id,jdbcType=INTEGER}, #{barcode,jdbcType=VARCHAR}, ",
        "#{departmentid,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR}, #{belongid,jdbcType=INTEGER}, #{belongtype,jdbcType=VARCHAR}, ",
        "#{belongname,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, #{type,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Egarbagebag record);

    @Update({
        "update egarbagebag",
        "set barcode = #{barcode,jdbcType=VARCHAR},",
          "departmentid = #{departmentid,jdbcType=INTEGER},",
          "status = #{status,jdbcType=VARCHAR},",
          "belongid = #{belongid,jdbcType=INTEGER},",
          "belongtype = #{belongtype,jdbcType=VARCHAR},",
          "belongname = #{belongname,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
           "type = #{type,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Egarbagebag record);

    @Delete({
            "delete from egarbagebag",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from egarbagebag",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Select({
            "select count(egarbagebag.id) value, edepartment.`name` name",
            "from ehospital join edepartment on ehospital.id = edepartment.hospitalid",
            "left join egarbagebag on edepartment.id = egarbagebag.departmentid  and egarbagebag.`status` != '使用中'",
            "where ehospital.id = #{hospitalid,jdbcType=INTEGER}",
            "group by edepartment.id"
    })
    List<Map<String,Object>> countGroupByDepartment(Integer hospitalid);
}