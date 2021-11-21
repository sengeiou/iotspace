package com.iot.mapper;

import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EtruckhospitalMapper {
    @Select({
            "${sql}"
    })
    List<Etruckhospital> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruckhospital> selectAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital",
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruckhospital> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital",
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER)
    })
    Etruckhospital selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etruckhospitalv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> selectVAll();

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital",
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital",
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    Etruckhospitalv selectVByPrimaryKey(Integer id);

    @Select({
            "select t2.id, t2.truckid, t2.submituserid, t2.confirmuserid, t2.plasticnumber, t2.plasticweight, t2.glassnumber, t2.glassweight, t2.time, t2.status, t2.note, t2.hospitalid",
            "from etruck t1,etruckhospital t2",
            "where t1.id = t2.truckid",
            " and ${sql}"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> selectVByTransportcompanyId(@Param("sql") String sql);

    @Insert({
        "insert into etruckhospital (id, truckid, submituserid, ",
        "confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time,  status, ",
        "hospitalid, note)",
        "values (#{id,jdbcType=INTEGER}, #{truckid,jdbcType=INTEGER}, ",
        "#{submituserid,jdbcType=INTEGER}, ",
        "#{confirmuserid,jdbcType=INTEGER}, #{plasticnumber,jdbcType=INTEGER}, #{plasticweight,jdbcType=DOUBLE}, #{glassnumber,jdbcType=INTEGER}, #{glassweight,jdbcType=DOUBLE}, #{time,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{hospitalid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etruckhospital record);

    @Update({
        "update etruckhospital",
        "set truckid = #{truckid,jdbcType=INTEGER},",
          "submituserid = #{submituserid,jdbcType=INTEGER},",
          "confirmuserid = #{confirmuserid,jdbcType=INTEGER},",
            "plasticnumber = #{plasticnumber,jdbcType=INTEGER},",
            "plasticweight = #{plasticweight,jdbcType=DOUBLE},",
            "glassnumber = #{glassnumber,jdbcType=INTEGER},",
            "glassweight = #{glassweight,jdbcType=DOUBLE},",
            "hospitalid = #{hospitalid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etruckhospital record);

    @Delete({
            "delete from etruckhospital",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etruckhospital",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

/*统计 */
    @Select({
            "select t4.address name, sum(IFNULL(etruckhospital.plasticweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t1.address address,t1.id t1id, t2.id t2id, t3.id t3id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "join esupervisionunit t3 on  t2.id = t3.pid",
            "where t1.`level` ='省'",
            ") t4",
    "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid in (t4.t3id,t4.t2id,t4.t1id) ",
   " and etruckhospital.status = '已审核'",
    "group by t4.t1id"
    })
    List<Map<String, Object>> statisticProvincePlasticWeight();

    @Select({
            "select t4.address name, sum(IFNULL(etruckhospital.glassweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t1.address address,t1.id t1id, t2.id t2id, t3.id t3id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "join esupervisionunit t3 on  t2.id = t3.pid",
            "where t1.`level` ='省'",
            ") t4",
            "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid in (t4.t3id,t4.t2id,t4.t1id) ",
            " and etruckhospital.status = '已审核'",
            "group by t4.t1id"
    })
    List<Map<String, Object>> statisticProvinceGlassWeight();

    @Select({
            "select t4.address name, sum(IFNULL(etruckhospital.plasticweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t2.address address,t2.id t2id, t3.id t3id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "join esupervisionunit t3 on  t2.id = t3.pid",
            "where t1.`address` = #{address,jdbcType=VARCHAR}",
        ") t4",
    "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid in (t4.t2id,t4.t3id) ",
    "and etruckhospital.status = '已审核'",
    "group by t4.t2id"
    })
    List<Map<String, Object>> statisticCityPlasticWeight(String address);

    @Select({
            "select t4.address name, sum(IFNULL(etruckhospital.glassweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t2.address address,t2.id t2id, t3.id t3id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "join esupervisionunit t3 on  t2.id = t3.pid",
            "where t1.`address` = #{address,jdbcType=VARCHAR}",
            ") t4",
            "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid in (t4.t2id,t4.t3id) ",
            "and etruckhospital.status = '已审核'",
            "group by t4.t2id"
    })
    List<Map<String, Object>> statisticCityGlassWeight(String address);

    @Select({
            "select t3.address name, sum(IFNULL(etruckhospital.plasticweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t2.address address,t2.id t2id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "where t1.`address`= #{address,jdbcType=VARCHAR}) t3",
            "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid = t3.t2id",
            " and etruckhospital.status = '已审核'",
             "group by t3.t2id"
    })
    List<Map<String, Object>> statisticPlatPlasticWeight(String address);

    @Select({
            "select t3.address name, sum(IFNULL(etruckhospital.glassweight,0)) value",
            "from etruckhospital,ehospital,(",
            "select  t2.address address,t2.id t2id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "where t1.`address`= #{address,jdbcType=VARCHAR}) t3",
            "where etruckhospital.hospitalid = ehospital.id and ehospital.supervisionunitid = t3.t2id",
            " and etruckhospital.status = '已审核'",
            "group by t3.t2id"
    })
    List<Map<String, Object>> statisticPlatGlassWeight(String address);
/*区下面的医院*/
    @Select({
            "select ehospital.name name, ehospital.address address,ehospital.id id, ehospital.longitude longitude, ehospital.latitude latitude,sum(IFNULL(etruckhospital.plasticweight,0)) value",
            "from ehospital",
            "left join etruckhospital on etruckhospital.hospitalid = ehospital.id and etruckhospital.status = '已审核'",
            "where ehospital.supervisionunitid = (",
            "select t2.id id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "where t1.`address` = #{paddress,jdbcType=VARCHAR}",
            "and t2.address = #{address,jdbcType=VARCHAR})",
            "group by ehospital.id"
    })
    List<Map<String, Object>> statisticHospitalPlasticWeight(String address,String paddress);

    @Select({
            "select ehospital.name name, ehospital.address address,ehospital.id id,ehospital.longitude longitude, ehospital.latitude latitude, sum(IFNULL(etruckhospital.glassweight,0)) value",
            "from ehospital",
            "left join etruckhospital on etruckhospital.hospitalid = ehospital.id and etruckhospital.status = '已审核'",
            "where ehospital.supervisionunitid = (",
            "select t2.id id from  esupervisionunit t1",
            "join esupervisionunit t2 on t1.id = t2.pid",
            "where t1.`address` = #{paddress,jdbcType=VARCHAR}",
            "and t2.address = #{address,jdbcType=VARCHAR})",
            "group by ehospital.id"
            })
    List<Map<String, Object>> statisticHospitalGlassWeight(String address,String paddress);

    /*全国  重量*/
    @Select({
            "select sum(IFNULL(plasticweight,0)) plasticweight",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'"
    })
    Integer countryPlasticWeight();

    @Select({
            "select sum(IFNULL(glassweight,0)) glassweight",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'"
    })
    Integer countryGlassWeight();
    /*某个省的重量*/

    @Select({
            "SELECT sum(IFNULL(plasticweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))"
    })
    Integer provincePlasticWeight(String address);

    @Select({
            "SELECT sum(IFNULL(glassweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))"
    })
    Integer provinceGlassWeight(String address);
/*某个市的总重量*/
    @Select({
            "SELECT sum(IFNULL(plasticweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))"
    })
    Integer cityPlasticWeight(String address);

    @Select({
            "SELECT sum(IFNULL(glassweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))"
    })
    Integer cityGlassWeight(String address);
/*某个区的总重量*/
    @Select({
            "SELECT sum(IFNULL(etruckhospital.plasticweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1,esupervisionunit t2",
            "where t1.id=t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})"
    })
    Integer platPlasticWeight(String address, String paddress);

    @Select({
            "SELECT sum(IFNULL(etruckhospital.glassweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1,esupervisionunit t2",
            "where t1.id=t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})"
    })
    Integer platGlassWeight(String address, String paddress);

    @Select({
            "SELECT sum(IFNULL(plasticweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
           " and ehospital.id = #{id,jdbcType=INTEGER}"
    })
    Integer hospitalPlasticWeight(Integer id);
/*某个医院的总重量*/
    @Select({
            "SELECT sum(IFNULL(glassweight,0))",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.id = #{id,jdbcType=INTEGER}"
    })
    Integer hospitalGlassWeight(Integer id);

    /*全国 省 市 区 医院最新5条医院收货数据*/
    /*全国*/
    @Select({
            "select",
            "id, truckid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, status, note, hospitalid",
            "from etruckhospital",
            "where status = '已审核'",
            "order by id desc",
            "limit 5"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> countrySelectLimit();
/*省*/
    @Select({
            "SELECT t2.id id, t2.truckid truckid, t2.submituserid submituserid,t2.confirmuserid confirmuserid, t2.plasticnumber plasticnumber, ",
            "t2.plasticweight plasticweight, t2.glassnumber glassnumber, t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.hospitalid hospitalid",
            "from ehospital t1, etruckhospital t2",
            "where t1.id = t2.hospitalid",
            " and t2.status = '已审核'",
            " and t1.supervisionunitid in (",
            "select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address = #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            "order by id desc",
            "limit 5"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> provinceSelectLimit(String address);
/*某个市*/
    @Select({
            "SELECT t2.id id, t2.truckid truckid, t2.submituserid submituserid,t2.confirmuserid confirmuserid, t2.plasticnumber plasticnumber, ",
            "t2.plasticweight plasticweight, t2.glassnumber glassnumber, t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.hospitalid hospitalid",
            "from ehospital t1, etruckhospital t2",
            "where t1.id = t2.hospitalid",
            " and t2.status = '已审核'",
            " and t1.supervisionunitid in (",
            "select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            "order by id desc",
            "limit 5"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> citySelectLimit(String address);
/*某个区最新5条数据*/
    @Select({
            "SELECT t2.id id, t2.truckid truckid, t2.submituserid submituserid,t2.confirmuserid confirmuserid, t2.plasticnumber plasticnumber,",
            "t2.plasticweight plasticweight, t2.glassnumber glassnumber, t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.hospitalid hospitalid",
            "from ehospital t1, etruckhospital t2",
            "where t1.id = t2.hospitalid",
            " and t2.status = '已审核'",
            " and t1.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1,esupervisionunit t2",
            "where t1.id = t2.pid",
            " and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})",
    "order by id desc",
    "limit 5"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> platSelectLimit(String address, String paddress);

    @Select({
            "SELECT t2.id id, t2.truckid truckid, t2.submituserid submituserid,t2.confirmuserid confirmuserid, t2.plasticnumber plasticnumber, ",
            "t2.plasticweight plasticweight, t2.glassnumber glassnumber, t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.hospitalid hospitalid",
            "from ehospital t1, etruckhospital t2",
            "where t1.id = t2.hospitalid",
            " and t2.status = '已审核'",
            " and t1.id = #{id,jdbcType=INTEGER}",
            "order by id desc",
            "limit 5"
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> hospitalSelectLimit(Integer id);

    /*全国  某省市区 重量 某年按月分组查询*/
    @Select({
            "select sum(IFNULL(plasticweight,0)) value,  DATE_FORMAT(t3.time,'%m') name",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'",
            " and year(t3.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(t3.time,'%m')"
    })
    List<Map<String, Object>> countryYearPlasticWeightGroupMonth(Integer year);

    @Select({
            "select sum(IFNULL(glassweight,0)) value, DATE_FORMAT(t3.time,'%m') name",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'",
            " and year(t3.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(t3.time,'%m')"
    })
    List<Map<String, Object>> countryYearGlassWeightGroupMonth(Integer year);

    /*省*/
    @Select({
            "SELECT sum(IFNULL(plasticweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval  #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> provinceYearPlasticWeightGroupMonth(String address, Integer year);

    @Select({
            "SELECT sum(IFNULL(glassweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval  #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> provinceYearGlassWeightGroupMonth(String address, Integer year);
    /*市*/
    @Select({
            "SELECT sum(IFNULL(plasticweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name ",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> cityYearPlasticWeightGroupMonth(String address,Integer year);

    @Select({
            "SELECT sum(IFNULL(glassweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name ",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> cityYearGlassWeightGroupMonth(String address,Integer year);
    /*区*/
    @Select({
            "SELECT sum(IFNULL(etruckhospital.plasticweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1, esupervisionunit t2",
            "where t1.id = t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})",
        "and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
        "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> platYearPlasticWeightGroupMonth(String address, String paddress, Integer year);

    @Select({
            "SELECT sum(IFNULL(etruckhospital.glassweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1, esupervisionunit t2",
            "where t1.id = t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})",
            "and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> platYearGlassWeightGroupMonth(String address, String paddress, Integer year);
    /*医院*/
    @Select({
            "SELECT sum(IFNULL(plasticweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.id = #{id,jdbcType=INTEGER}",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> hospitalYearPlasticWeightGroupMonth(Integer id,Integer year);

    @Select({
            "SELECT sum(IFNULL(glassweight,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.id = #{id,jdbcType=INTEGER}",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> hospitalYearGlassWeightGroupMonth(Integer id,Integer year);

    /*全国 省市区 医院 每年每月 数量*/
    @Select({
            "select sum(IFNULL(plasticnumber,0)) value,  DATE_FORMAT(t3.time,'%m') name",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'",
            " and year(t3.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(t3.time,'%m')"
    })
    List<Map<String, Object>> countryYearPlasticNumberGroupMonth(Integer year);

    @Select({
            "select sum(IFNULL(glassnumber,0)) value, DATE_FORMAT(t3.time,'%m') name",
            "from esupervisionunit t1,ehospital t2,etruckhospital t3",
            "where t1.id = t2.supervisionunitid and t2.id = t3.hospitalid",
            " and t3.status ='已审核'",
            " and year(t3.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(t3.time,'%m')"
    })
    List<Map<String, Object>> countryYearGlassNumberGroupMonth(Integer year);

    /*省*/
    @Select({
            "SELECT sum(IFNULL(plasticnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> provinceYearPlasticNumberGroupMonth(String address,Integer year);

    @Select({
            "SELECT sum(IFNULL(glassnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name ",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in (",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> provinceYearGlassNumberGroupMonth(String address,Integer year);

    /*市*/
    @Select({
            "SELECT sum(IFNULL(plasticnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name ",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> cityYearPlasticNumberGroupMonth(String address,Integer year);

    @Select({
            "SELECT sum(IFNULL(glassnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name ",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid in ( select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> cityYearGlassNumberGroupMonth(String address,Integer year);

   /*区*/

    @Select({
            "SELECT sum(IFNULL(etruckhospital.plasticnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1, esupervisionunit t2",
            "where t1.id = t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})",
            "and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> platYearPlasticNumberGroupMonth(String address,String paddress, Integer year);

    @Select({
            "SELECT sum(IFNULL(etruckhospital.glassnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.supervisionunitid = (",
            "select t2.id id from esupervisionunit t1, esupervisionunit t2",
            "where t1.id = t2.pid and t1.address = #{paddress,jdbcType=VARCHAR} and t2.address = #{address,jdbcType=VARCHAR})",
            "and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> platYearGlassNumberGroupMonth(String address,String paddress, Integer year);
    /*医院*/
    @Select({
        "SELECT sum(IFNULL(plasticnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
        "from ehospital, etruckhospital",
        "where ehospital.id = etruckhospital.hospitalid",
        " and etruckhospital.status = '已审核'",
        " and ehospital.id = #{id,jdbcType=INTEGER}",
        " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
        "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> hospitalYearPlasticNumberGroupMonth(Integer id,Integer year);

    @Select({
            "SELECT sum(IFNULL(glassnumber,0)) value, DATE_FORMAT(etruckhospital.time,'%m') name",
            "from ehospital, etruckhospital",
            "where ehospital.id = etruckhospital.hospitalid",
            " and etruckhospital.status = '已审核'",
            " and ehospital.id = #{id,jdbcType=INTEGER}",
            " and year(etruckhospital.time)=year(date_sub(now(),interval #{year,jdbcType=INTEGER} year))",
            "group by  DATE_FORMAT(etruckhospital.time,'%m')"
    })
    List<Map<String, Object>> hospitalYearGlassNumberGroupMonth(Integer id,Integer year);

    @Select({
            "select t2.id id, t2.truckid truckid, t2.submituserid submituserid, t2.confirmuserid confirmuserid,",
            "t2.plasticnumber plasticnumber, t2.plasticweight plasticweight, t2.glassnumber glassnumber,",
            "t2.glassweight glassweight, t2.time time, t2.status status, t2.note note, t2.hospitalid hospitalid",
            "from etruck t1, etruckhospital t2",
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
            @Result(column="hospitalid", property="hospitalid", jdbcType=JdbcType.INTEGER),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "hospitalid", property = "hospital",
                    one = @One(select = "com.iot.mapper.EhospitalMapper.selectVByPrimaryKey"))
    })
    List<Etruckhospitalv> selectVByStatusAndSql(@Param("sql") String sql);

    /*SQL查询玻璃重量*/

    @Select({
            "SELECT sum(IFNULL(t2.glassweight,0)) glassweight",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    Integer sumGlassweightBySql(@Param("sql") String sql);

    /*SQL查询塑料重量*/
    @Select({
            "SELECT sum(IFNULL(t2.plasticweight,0)) glassweight",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    Integer sumPlasticweightBySql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.glassweight,0)) value, DATE_FORMAT(t2.time,'%m') name",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    List<Map<String, Object>> yearGlassweightGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.plasticweight,0)) value, DATE_FORMAT(t2.time,'%m') name",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    List<Map<String, Object>> yearPlasticweightGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.glassnumber,0)) value, DATE_FORMAT(t2.time,'%m') name",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    List<Map<String, Object>> yearGlassnumberGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.plasticnumber,0)) value, DATE_FORMAT(t2.time,'%m') name",
            "from etruck t1, etruckhospital t2",
            "where ${sql}"
    })
    List<Map<String, Object>> yearPlasticnumberGroupMonthSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.glassweight,0)) value, t3.name name",
            "from etruck t1, etruckhospital t2, etransportcompany t3",
            "where ${sql}"
    })
    List<Map<String, Object>> sumGlassweightSql(@Param("sql") String sql);

    @Select({
            "SELECT sum(IFNULL(t2.plasticweight,0)) value, t3.name name",
            "from etruck t1, etruckhospital t2, etransportcompany t3",
            "where ${sql}"
    })
    List<Map<String, Object>> sumPlasticweightSql(@Param("sql") String sql);

    @Select({
            "select DATE_FORMAT(t1.time,'%Y-%m-%d %H:%i:%s') time, t3.name hospitalname, t7.name transportcompanyname, t6.number number,",
            "t1.plasticnumber plasticnumber, t1.plasticweight plasticweight, t1.glassnumber glassnumber,",
            "t1.glassweight glassweight, t4.realname submituser, t5.realname confirmuser,t1.status status,t8.name treatmentplantname",
            "from etruckhospital t1",
            "left join euser t4 on t1.submituserid = t4.id",
            "left join euser t5 on t1.confirmuserid = t5.id",
            "left join ehospital t3 on t1.hospitalid = t3.id",
            "left join etruck t2 on  t1.truckid = t2.id",
            "left join evehicle t6 on  t2.vehicleid = t6.id",
            "left join etransportcompany t7 on t2.transportcompanyid = t7.id",
            "left join etreatmentplant t8 on t7.treatmentplantid = t8.id",
            "where ${sql}"
    })
    List<Map<String, Object>> truckhospitalList(@Param("sql") String sql);

    @Select({
            "select DATE_FORMAT(t1.time,'%Y-%m-%d %H:%i:%s') time, t3.name hospitalname, t7.name transportcompanyname, t6.number number,",
            "t1.plasticnumber plasticnumber, t1.plasticweight plasticweight, t1.glassnumber glassnumber,",
            "t1.glassweight glassweight, t4.realname submituser, t5.realname confirmuser,t1.status status,t8.name treatmentplantname",
            "from etruckhospital t1",
            "left join euser t4 on t1.submituserid = t4.id",
            "left join euser t5 on t1.confirmuserid = t5.id",
            "left join ehospital t3 on t1.hospitalid = t3.id",
            "left join etruck t2 on  t1.truckid = t2.id",
            "left join evehicle t6 on  t2.vehicleid = t6.id",
            "left join etransportcompany t7 on t2.transportcompanyid = t7.id",
            "left join etreatmentplant t8 on t7.treatmentplantid = t8.id",
            "where ${sql}"
    })
    List<Map<String, Object>> treatmentplantTruckhospitalList(@Param("sql") String sql);

    @Select({
            "select sum(IFNULL(t1.plasticnumber,0)) plasticnumber, sum(IFNULL(t1.plasticweight,0)) plasticweight,",
            "sum(IFNULL(t1.glassnumber,0)) glassnumber, sum(IFNULL(t1.glassweight,0)) glassweight",
            "from etruckhospital t1",
            "left join euser t4 on t1.submituserid = t4.id",
            "left join euser t5 on t1.confirmuserid = t5.id",
            "left join ehospital t3 on t1.hospitalid = t3.id",
            "left join etruck t2 on  t1.truckid = t2.id",
            "left join evehicle t6 on  t2.vehicleid = t6.id",
            "left join etransportcompany t7 on t2.transportcompanyid = t7.id",
            "left join etreatmentplant t8 on t7.treatmentplantid = t8.id",
            "where ${sql}"
    })
    Map<String, Object> treatmentplantTruckhospitalSum(@Param("sql") String sql);


    @Select({
            "select sum(IFNULL(plasticnumber,0)) plasticnumber, sum(IFNULL(plasticweight,0)) plasticweight,",
            "sum(IFNULL(glassnumber,0)) glassnumber, sum(IFNULL(glassweight,0)) glassweight",
            "from etruckhospital",
            "where status = '已审核'",
            " and truckid = #{truckid,jdbcType=INTEGER}"
    })
    Map<String, Object> sumByTruckid(Integer truckid);

}