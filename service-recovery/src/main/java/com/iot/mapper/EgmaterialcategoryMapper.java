package com.iot.mapper;

import com.iot.bean.Egmaterialcategory;
import com.iot.bean.Egmaterialcategoryv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EgmaterialcategoryMapper {

    @Select({
            "${sql}"
    })
    List<Egmaterialcategory> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    List<Egmaterialcategory> selectAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    List<Egmaterialcategory> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="stock", property="stock", jdbcType=JdbcType.DOUBLE)
    })
    Egmaterialcategory selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Egmaterialcategoryv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory"
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
    List<Egmaterialcategoryv> selectVAll();

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory",
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
    List<Egmaterialcategoryv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, treatmentplantid, note, stock",
            "from egmaterialcategory",
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
    Egmaterialcategoryv selectVByPrimaryKey(Integer id);

    @Insert({
            "insert into egmaterialcategory (id, name, ",
            "treatmentplantid, stock, note)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{treatmentplantid,jdbcType=INTEGER}, #{stock,jdbcType=DOUBLE},  ",
            "#{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Egmaterialcategory record);

    @Update({
            "update egmaterialcategory",
            "set name = #{name,jdbcType=VARCHAR},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
            "stock = #{stock,jdbcType=DOUBLE},",
            "note = #{note,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Egmaterialcategory record);

    @Delete({
            "delete from egmaterialcategory",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from egmaterialcategory",
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
            "update egmaterialcategory",
            "set",
            "stock = stock + #{stock,jdbcType=DOUBLE}",
            "where id = #{id,jdbcType=INTEGER}",
            " and stock + #{stock,jdbcType=DOUBLE} >= 0"
    })
    int updateStock(Integer id, Double stock);
}