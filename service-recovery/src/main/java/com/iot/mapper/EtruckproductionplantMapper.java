package com.iot.mapper;

import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtruckproductionplantMapper {
    @Select({
            "${sql}"
    })
    List<Etruckproductionplant> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruckproductionplant> selectAll();

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER)
    })
    List<Etruckproductionplant> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER)
    })
    Etruckproductionplant selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etruckproductionplantv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column = "productionplantid", property = "productionplant",
                    one = @One(select = "com.iot.mapper.EproductionplantMapper.selectByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey"))
    })
    List<Etruckproductionplantv> selectVAll();

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column = "productionplantid", property = "productionplant",
                    one = @One(select = "com.iot.mapper.EproductionplantMapper.selectByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey"))
    })
    List<Etruckproductionplantv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, productionplantid, submituserid, confirmuserid, plasticnumber, plasticweight, glassnumber, glassweight, time, note, status, truckid",
            "from etruckproductionplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column = "productionplantid", property = "productionplant",
                    one = @One(select = "com.iot.mapper.EproductionplantMapper.selectByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey"))
    })
    Etruckproductionplantv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into etruckproductionplant (id, ",
        "productionplantid, submituserid, confirmuserid, plasticnumber,plasticweight,glassnumber,glassweight, time, ",
        "note, truckid, status)",
        "values (#{id,jdbcType=INTEGER}, ",
        "#{productionplantid,jdbcType=INTEGER}, ",
        "#{submituserid,jdbcType=INTEGER}, ",
        "#{confirmuserid,jdbcType=INTEGER}, #{plasticnumber,jdbcType=INTEGER}, #{plasticweight,jdbcType=DOUBLE}, #{glassnumber,jdbcType=INTEGER}, #{glassweight,jdbcType=DOUBLE}, #{time,jdbcType=TIMESTAMP}, ",
        "#{note,jdbcType=VARCHAR}, #{truckid,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etruckproductionplant record);

    @Update({
        "update etruckproductionplant",
        "set productionplantid = #{productionplantid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
            "submituserid = #{submituserid,jdbcType=INTEGER},",
            "confirmuserid = #{confirmuserid,jdbcType=INTEGER},",
            "plasticnumber = #{plasticnumber,jdbcType=INTEGER},",
            "plasticweight = #{plasticweight,jdbcType=DOUBLE},",
            "glassnumber = #{glassnumber,jdbcType=INTEGER},",
            "glassweight = #{glassweight,jdbcType=DOUBLE},",
            "truckid = #{truckid,jdbcType=INTEGER},",
            "status = #{status,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etruckproductionplant record);

    @Delete({
            "delete from etruckproductionplant",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etruckproductionplant",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Select({
            "SELECT t2.id id, t2.productionplantid productionplantid, t2.submituserid submituserid, ",
            "t2.confirmuserid confirmuserid, t2.plasticnumber plasticnumber, t2.plasticweight plasticweight, t2.glassnumber glassnumber, t2.glassweight glassweight, ",
            "t2.time time, t2.note note, t2.status status, t2.truckid truckid",
            "from etruck t1, etruckproductionplant t2",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="productionplantid", property="productionplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="submituserid", property="submituserid", jdbcType=JdbcType.INTEGER),
            @Result(column="confirmuserid", property="confirmuserid", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticnumber", property="plasticnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="plasticweight", property="plasticweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="glassnumber", property="glassnumber", jdbcType=JdbcType.INTEGER),
            @Result(column="glassweight", property="glassweight", jdbcType=JdbcType.DOUBLE),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="truckid", property="truckid", jdbcType=JdbcType.INTEGER),
            @Result(column = "productionplantid", property = "productionplant",
                    one = @One(select = "com.iot.mapper.EproductionplantMapper.selectByPrimaryKey")),
            @Result(column = "submituserid", property = "submitUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "confirmuserid", property = "confirmUser",
                    one = @One(select = "com.iot.mapper.EuserMapper.selectByPrimaryKey")),
            @Result(column = "truckid", property = "truck",
                    one = @One(select = "com.iot.mapper.EtruckMapper.selectVByPrimaryKey"))
    })
    List<Etruckproductionplantv> selectVByStatusAndSql(@Param("sql") String sql);

}