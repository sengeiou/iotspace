package com.iot.mapper;

import com.iot.bean.Ehospital;
import com.iot.bean.Ehospitalv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation = RedisCache.class)
public interface EhospitalMapper {
    @Select({
            "${sql}"
    })
    List<Ehospital> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR)

    })
    List<Ehospital> selectAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR)
    })
    List<Ehospital> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR)
    })
    Ehospital selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ehospitalv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))
    })
    List<Ehospitalv> selectVAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))

    })
    List<Ehospitalv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))
    })
    Ehospitalv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into ehospital (id, name, address, leadername, belonglevel, beds, transportcompanyid, province, city, plat, longitude, latitude, ",
        "leaderphone, level, type, supervisionunitid, code, img, time, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{leadername,jdbcType=VARCHAR}, #{belonglevel,jdbcType=VARCHAR}, #{beds,jdbcType=INTEGER}, #{transportcompanyid,jdbcType=INTEGER}, ",
         "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{plat,jdbcType=VARCHAR}, #{longitude,jdbcType=VARCHAR}, #{latitude,jdbcType=VARCHAR}, ",
        "#{leaderphone,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{supervisionunitid,jdbcType=INTEGER}, #{code,jdbcType=VARCHAR}, #{img,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ehospital record);

    @Update({
        "update ehospital",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "leadername = #{leadername,jdbcType=VARCHAR},",
          "leaderphone = #{leaderphone,jdbcType=VARCHAR},",
            "level = #{level,jdbcType=VARCHAR},",
            "belonglevel = #{belonglevel,jdbcType=VARCHAR},",
            "beds = #{beds,jdbcType=INTEGER},",
            "transportcompanyid = #{transportcompanyid,jdbcType=INTEGER},",
            "type = #{type,jdbcType=VARCHAR},",
            "supervisionunitid = #{supervisionunitid,jdbcType=INTEGER},",
            "code = #{code,jdbcType=VARCHAR},",
            "img = #{img,jdbcType=VARCHAR},",
            "province = #{province,jdbcType=VARCHAR},",
            "city = #{city,jdbcType=VARCHAR},",
            "plat = #{plat,jdbcType=VARCHAR},",
            "longitude = #{longitude,jdbcType=VARCHAR},",
            "latitude = #{latitude,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ehospital record);

    @Delete({
            "delete from ehospital",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ehospital",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /*全国医疗机构总量*/
    @Select({
            "select count(1) count from ehospital"
    })
    Integer countryHospitalNum();

    /*某个省医疗机构总量*/
    @Select({
            "select count(1) from ehospital",
            "where supervisionunitid in(",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))"
    })
    Integer provinceHospitalNum(String address);

    /*某个市医疗机构总量*/
    @Select({
            "select count(1) from ehospital",
            "where supervisionunitid in(",
            "select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))"
    })
    Integer cityHospitalNum(String address);

    /*某个区医疗机构总量*/
    @Select({
            "select count(1) from ehospital",
           "where supervisionunitid = (",
            "select t2.id id from esupervisionunit t1,esupervisionunit t2",
           " where t1.id = t2.pid",
            " and t1.address = #{paddress,jdbcType=VARCHAR}",
            " and t2.address = #{address,jdbcType=VARCHAR})"
    })
    Integer platHospitalNum(String address, String paddress);

    /*查询某个运输公司下的各类型医院数量*/
    @Select({
            "select count(1) count, type type from ehospital",
            "where transportcompanyid = #{transportcompanyid,jdbcType=INTEGER}",
            "group by type"
    })
    List<Map<String,Object>> countByTransportcompanyGroupByType(Integer transportcompanyid);

    @Select({
            "select count(1) count, level level from ehospital",
            "where transportcompanyid = #{transportcompanyid,jdbcType=INTEGER}",
            " and level = #{level,jdbcType=VARCHAR}"
    })
    Map<String,Object> countByTransportcompanyAndLevel(Integer transportcompanyid,String level);

    /*sql查询医院数量*/
    @Select({
            "select count(1) count from ehospital",
            "where ${sql}"

    })
    Integer hospitalNumBySql(@Param("sql") String sql);

    /*省监管单位查询医院*/
    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where supervisionunitid in(",
            "select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})",
            "union  select id from esupervisionunit where pid in (select id from esupervisionunit where pid = (select id from esupervisionunit where address =  #{address,jdbcType=VARCHAR})))",
            " and ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))
    })
    List<Ehospitalv> provinceHospital(@Param("address") String address,@Param("sql") String sql);

    /*某个市医疗机构*/
    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where supervisionunitid in(",
            "select id from esupervisionunit where address = #{address,jdbcType=VARCHAR}",
            "union  select id from esupervisionunit where pid = (select id from esupervisionunit where address =#{address,jdbcType=VARCHAR}))",
            " and ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))
    })
    List<Ehospitalv> cityHospital(@Param("address") String address,@Param("sql") String sql);

    /*某个区医疗机构总量*/
    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, level, type, supervisionunitid, code, img, belonglevel, beds, transportcompanyid, time, province, city, plat, longitude, latitude",
            "from ehospital",
            "where supervisionunitid = (",
            "select t2.id id from esupervisionunit t1,esupervisionunit t2",
            " where t1.id = t2.pid",
            " and t1.address = #{paddress,jdbcType=VARCHAR}",
            " and t2.address = #{address,jdbcType=VARCHAR})",
            " and ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="supervisionunitid", property="supervisionunitid", jdbcType=JdbcType.INTEGER),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="belonglevel", property="belonglevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="beds", property="beds", jdbcType=JdbcType.INTEGER),
            @Result(column="transportcompanyid", property="transportcompanyid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="plat", property="plat", jdbcType=JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
            @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
            @Result(column = "supervisionunitid", property = "supervisionunit",
                    one = @One(select = "com.iot.mapper.EsupervisionunitMapper.selectVByPrimaryKey")),
            @Result(column = "transportcompanyid", property = "transportcompany",
                    one = @One(select = "com.iot.mapper.EtransportcompanyMapper.selectVByPrimaryKey"))
    })
    List<Ehospitalv> platHospital(@Param("address") String address, @Param("paddress") String paddress,@Param("sql") String sql);


    @Update({
            "update ehospital",
            "set province = #{province,jdbcType=VARCHAR},",
            "city = #{city,jdbcType=VARCHAR},",
            "plat = #{plat,jdbcType=VARCHAR},",
            "longitude = #{longitude,jdbcType=VARCHAR},",
            "latitude = #{latitude,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateLongitudeLatitude(Ehospital record);

}