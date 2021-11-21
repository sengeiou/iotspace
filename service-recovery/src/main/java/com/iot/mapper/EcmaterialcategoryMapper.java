package com.iot.mapper;

import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EcmaterialcategoryMapper {

    @Select({
            "${sql}"
    })
    List<Ecmaterialcategory> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    List<Ecmaterialcategory> selectAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    List<Ecmaterialcategory> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    Ecmaterialcategory selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Ecmaterialcategoryv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Ecmaterialcategoryv> selectVAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Ecmaterialcategoryv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from ecmaterialcategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE),
            @Result(column = "treatmentplantid",property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    Ecmaterialcategoryv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into ecmaterialcategory (id, name, ",
            "treatmentplantid, stock, note)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{treatmentplantid,jdbcType=INTEGER}, #{stock,jdbcType=DOUBLE},  ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Ecmaterialcategory record);

    @Update({
            "update ecmaterialcategory",
            "set name = #{name,jdbcType=VARCHAR},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "stock = #{stock,jdbcType=DOUBLE},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Ecmaterialcategory record);

    @Delete({
            "delete from ecmaterialcategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from ecmaterialcategory",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    /**
     * 更新库存 添加或减少
     * @param id
     * @param stock
     * @return
     */
    @Update({
            "update ecmaterialcategory",
            "set",
            "stock = stock + #{stock,jdbcType=DOUBLE}",
            "where id = #{id,jdbcType=INTEGER}",
            " and stock + #{stock,jdbcType=DOUBLE} >= 0"
    })
    int updateStock(Integer id,Double stock);
}