package com.iot.mapper;

import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EtrucktreatmentplantMapper {
    @Select({
            "${sql}"
    })
    List<Etrucktreatmentplant> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etrucktreatmentplant> selectAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etrucktreatmentplant> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    Etrucktreatmentplant selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etrucktreatmentplantv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant"
    })
    @Results(value = {
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Etrucktreatmentplantv> selectVAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Etrucktreatmentplantv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    Etrucktreatmentplantv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into etrucktreatmentplant (id, truckid, submituserid, ",
        "confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time,  status, ",
        "treatmentplantid, note)",
        "values (#{id,jdbcType=INTEGER}, #{truckid,jdbcType=INTEGER}, ",
        "#{submituserid,jdbcType=INTEGER}, ",
        "#{confirmuserid,jdbcType=INTEGER}, #{plasticnumber,jdbcType=INTEGER}, #{plasticweight,jdbcType=DOUBLE}, #{glassnumber,jdbcType=INTEGER}, #{glassweight,jdbcType=DOUBLE}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{treatmentplantid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etrucktreatmentplant record);

    @Update({
        "update etrucktreatmentplant",
        "set truckid = #{truckid,jdbcType=INTEGER},",
          "submituserid = #{submituserid,jdbcType=INTEGER},",
          "confirmuserid = #{confirmuserid,jdbcType=INTEGER},",
            "plasticnumber = #{plasticnumber,jdbcType=INTEGER},",
            "plasticweight = #{plasticweight,jdbcType=DOUBLE},",
            "glassnumber = #{glassnumber,jdbcType=INTEGER},",
            "glassweight = #{glassweight,jdbcType=DOUBLE},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etrucktreatmentplant record);

    @Delete({
            "delete from etrucktreatmentplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etrucktreatmentplant",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Select({
            "select t2.id id, t2.truckid truckid, t2.submituserid submituserid, t2.confirmuserid confirmuserid,",
            "t2.plasticnumber plasticnumber, t2.plasticweight plasticweight, t2.glassnumber glassnumber,",
            "t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.treatmentplantid treatmentplantid",
            "from etruck t1, etrucktreatmentplant t2",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Etrucktreatmentplantv> selectVByStatusAndSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(glassweight,0))",
            "from etrucktreatmentplant",
            "where ${sql}"
            })
   Integer sumGlassweightBySql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(plasticweight,0))",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    Integer sumPlasticweightBySql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(glassnumber,0)) value, DATE_FORMAT(time,'%m') name",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    List<Map<String, Object>> yearGlassnumberGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(plasticnumber,0)) value, DATE_FORMAT(time,'%m') name",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    List<Map<String, Object>> yearPlasticnumberGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(glassweight,0)) value, DATE_FORMAT(time,'%m') name",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    List<Map<String, Object>> yearGlassweightGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(plasticweight,0)) value, DATE_FORMAT(time,'%m') name",
            "from etrucktreatmentplant",
            "where ${sql}"
    })
    List<Map<String, Object>> yearPlasticweightGroupMonthSql(@Param("sql") String sql);


    @Select({
            "select DATE_FORMAT(t1.time,'%Y-%m-%d %H:%i:%s') time, t6.name treatmentplant, t7.name transportcompany,",
            "t5.number number,t1.plasticnumber plasticnumber,t1.plasticweight plasticweight, t1.glassnumber glassnumber,",
            "t1.glassweight glassweight,t2.realname submituser, t3.realname confirmuser,t1.note note",
            "from etrucktreatmentplant t1",
            "left join euser t2 on t1.submituserid = t2.id",
            "left join euser t3 on t1.confirmuserid = t3.id",
            "left join etruck t4 on t1.truckid = t4.id",
            "left join evehicle t5 on t4.vehicleid = t5.id",
            "left join etreatmentplant t6 on t1.treatmentplantid = t6.id",
            "left join etransportcompany t7 on t4.transportcompanyid = t7.id",
            "where ${sql}"
    })
    List<Map<String, Object>> trucktreatmentplantList(@Param("sql") String sql);

    @Select({
            "select sum(IFNULL(t1.plasticnumber,0)) plasticnumber, sum(IFNULL(t1.plasticweight,0)) plasticweight,",
            "sum(IFNULL(t1.glassnumber,0)) glassnumber, sum(IFNULL(t1.glassweight,0)) glassweight",
            "from etrucktreatmentplant t1",
            "left join euser t2 on t1.submituserid = t2.id",
            "left join euser t3 on t1.confirmuserid = t3.id",
            "left join etruck t4 on t1.truckid = t4.id",
            "left join evehicle t5 on t4.vehicleid = t5.id",
            "left join etreatmentplant t6 on t1.treatmentplantid = t6.id",
            "left join etransportcompany t7 on t4.transportcompanyid = t7.id",
            "where ${sql}"
    })
    Map<String, Object> trucktreatmentplantSum(@Param("sql") String sql);



}