package com.iot.mapper;

import com.iot.bean.Eorder;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EorderMapper {
    @Select({
            "${sql}"
    })
    List<Eorder> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, type, number, unitprice, time, address, contract, no, categoryid, weight, totalprice, car, phone, invoice, note, treatmentplantid, username ",
            "from eorder"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="unitprice", property="unitprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="contract", property="contract", jdbcType=JdbcType.VARCHAR),
            @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="totalprice", property="totalprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="car", property="car", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="invoice", property="invoice", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR)
    })
    List<Eorder> selectAll();

    @Select({
            "select",
            "id, name, type, number, unitprice, time, address, contract, no, categoryid, weight, totalprice, car, phone, invoice, note, treatmentplantid, username",
            "from eorder",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="unitprice", property="unitprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="contract", property="contract", jdbcType=JdbcType.VARCHAR),
            @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="totalprice", property="totalprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="car", property="car", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="invoice", property="invoice", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR)
    })
    List<Eorder> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, type, number, unitprice, time, address, contract, no, categoryid, weight, totalprice, car, phone, invoice, note, treatmentplantid, username",
            "from eorder",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
            @Result(column="unitprice", property="unitprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="contract", property="contract", jdbcType=JdbcType.VARCHAR),
            @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR),
            @Result(column="categoryid", property="categoryid", jdbcType=JdbcType.INTEGER),
            @Result(column="weight", property="weight", jdbcType=JdbcType.DOUBLE),
            @Result(column="totalprice", property="totalprice", jdbcType=JdbcType.DOUBLE),
            @Result(column="car", property="car", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="invoice", property="invoice", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR)
    })
    Eorder selectByPrimaryKey(Integer id);

    @Insert({
            "insert into eorder (id, name, type,number, unitprice, time, address, ",
            "contract, no, categoryid, weight, totalprice, car,  ",
            "phone, invoice, treatmentplantid, username, note)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{number,jdbcType=INTEGER}, #{unitprice,jdbcType=DOUBLE}, ",
            "#{time,jdbcType=TIMESTAMP}, #{address,jdbcType=VARCHAR}, #{contract,jdbcType=VARCHAR}, #{no,jdbcType=VARCHAR}, #{categoryid,jdbcType=INTEGER}, ",
            "#{weight,jdbcType=DOUBLE}, #{totalprice,jdbcType=DOUBLE}, #{car,jdbcType=VARCHAR},",
            "#{phone,jdbcType=VARCHAR}, #{invoice,jdbcType=VARCHAR}, #{treatmentplantid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR},  #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Eorder record);

    @Update({
            "update eorder",
            "set name = #{name,jdbcType=VARCHAR},",
            "type = #{type,jdbcType=VARCHAR},",
            "number = #{number,jdbcType=INTEGER},",
            "unitprice = #{unitprice,jdbcType=DOUBLE},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "address = #{address,jdbcType=VARCHAR},",
            "contract = #{contract,jdbcType=VARCHAR},",
            "no = #{no,jdbcType=VARCHAR},",
            "categoryid = #{categoryid,jdbcType=INTEGER},",
            "weight = #{weight,jdbcType=DOUBLE},",
            "totalprice = #{totalprice,jdbcType=DOUBLE},",
            "car = #{car,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "invoice = #{invoice,jdbcType=VARCHAR},",
            "username = #{username,jdbcType=VARCHAR},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Eorder record);

    @Delete({
            "delete from eorder",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from eorder",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);
}