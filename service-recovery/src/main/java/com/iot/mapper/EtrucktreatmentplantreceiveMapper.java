package com.iot.mapper;

import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtrucktreatmentplantreceiveMapper {
    @Select({
            "${sql}"
    })
    List<Etrucktreatmentplantreceive> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive"
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
    List<Etrucktreatmentplantreceive> selectAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive",
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
    List<Etrucktreatmentplantreceive> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive",
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
    Etrucktreatmentplantreceive selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etrucktreatmentplantreceivev> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive"
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
    List<Etrucktreatmentplantreceivev> selectVAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive",
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
    List<Etrucktreatmentplantreceivev> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, treatmentplantid",
            "from etrucktreatmentplantreceive",
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
    Etrucktreatmentplantreceivev selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into etrucktreatmentplantreceive (id, truckid, submituserid, ",
        "confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time,  status, ",
        "treatmentplantid, note)",
        "values (#{id,jdbcType=INTEGER}, #{truckid,jdbcType=INTEGER}, ",
        "#{submituserid,jdbcType=INTEGER}, ",
        "#{confirmuserid,jdbcType=INTEGER}, #{plasticnumber,jdbcType=INTEGER}, #{plasticweight,jdbcType=DOUBLE}, #{glassnumber,jdbcType=INTEGER}, #{glassweight,jdbcType=DOUBLE}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{treatmentplantid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etrucktreatmentplantreceive record);

    @Update({
        "update etrucktreatmentplantreceive",
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
    int update(Etrucktreatmentplantreceive record);

    @Delete({
            "delete from etrucktreatmentplantreceive",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etrucktreatmentplantreceive",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);


    @Select({
            "select t2.id trucktreatmentplantreceiveid, t4.realname transportname, ",
            "t5.number carnumber, t3.name transportcompanyname, t2.status status,t2.time time, ",
            "t2.plasticweight plasticweight,t2.plasticnumber plasticnumber,t2.glassweight glassweight, ",
            "t2.glassnumber glassnumber, t7.name productionplantname,t6.plasticweight productionplantplasticweight, ",
            "t6.plasticnumber productionplantplasticnumber, t6.glassweight productionplantglassweight, ",
            "t6.glassnumber productionplantglassnumber",
            "from etruck t1 join etrucktreatmentplantreceive t2 on t1.id = t2.truckid",
            "join etransportcompany t3 on t1.transportcompanyid = t3.id",
            "join euser t4 on t1.userid = t4.id",
            "join evehicle t5 on t1.vehicleid = t5.id",
            "left join etruckproductionplant t6 on t1.id = t6.truckid",
            "left join eproductionplant t7 on t6.productionplantid = t7.id",
            "where ${sql}"
    })
    @Results({
            @Result(column="trucktreatmentplantreceiveid", property="trucktreatmentplantreceiveid", jdbcType=JdbcType.INTEGER),
            @Result(column="transportname", property="transportname", jdbcType=JdbcType.VARCHAR),
            @Result(column="carnumber", property="carnumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="transportcompanyname", property="transportcompanyname", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="productionplantname", property="productionplantname", jdbcType=JdbcType.VARCHAR),
            @Result(column="productionplantplasticnumber", property="productionplantplasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="productionplantplasticweight", property="productionplantplasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="productionplantglassnumber", property="productionplantglassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="productionplantglassweight", property="productionplantglassweight", jdbcType=JdbcType.DOUBLE)
    })
    List<EtreatmentplantOutBoundVo> selectOutBoundVoBySql(@Param("sql") String sql);
}