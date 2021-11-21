package com.iot.mapper;

import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;
import com.iot.util.RedisCache;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface EtransportcompanyMapper {
    @Select({
            "${sql}"
    })
    List<Etransportcompany> select(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etransportcompany> selectAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    List<Etransportcompany> selectBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER)
    })
    Etransportcompany selectByPrimaryKey(Integer id);

    @Select({
            "${sql}"
    })
    List<Etransportcompanyv> selectV(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Etransportcompanyv> selectVAll();

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany",
            "where ${sql}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    List<Etransportcompanyv> selectVBySql(@Param("sql") String sql);

    @Select({
            "select",
            "id, name, address, leadername, leaderphone, note, img, treatmentplantid",
            "from etransportcompany",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="leadername", property="leadername", jdbcType=JdbcType.VARCHAR),
            @Result(column="leaderphone", property="leaderphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
            @Result(column="img", property="img", jdbcType=JdbcType.VARCHAR),
            @Result(column="treatmentplantid", property="treatmentplantid", jdbcType=JdbcType.INTEGER),
            @Result(column = "treatmentplantid", property = "treatmentplant",
                    one = @One(select = "com.iot.mapper.EtreatmentplantMapper.selectByPrimaryKey"))
    })
    Etransportcompanyv selectVByPrimaryKey(Integer id);

    @Insert({
        "insert into etransportcompany (id, name, address, leadername, ",
        "leaderphone, img, treatmentplantid, note)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{leadername,jdbcType=VARCHAR}, ",
        " #{leaderphone,jdbcType=VARCHAR}, #{img,jdbcType=VARCHAR}, #{treatmentplantid,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Etransportcompany record);

    @Update({
        "update etransportcompany",
        "set name = #{name,jdbcType=VARCHAR},",
            "address = #{address,jdbcType=VARCHAR},",
            "leadername = #{leadername,jdbcType=VARCHAR},",
            "leaderphone = #{leaderphone,jdbcType=VARCHAR},",
            "img = #{img,jdbcType=VARCHAR},",
            "treatmentplantid = #{treatmentplantid,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int update(Etransportcompany record);

    @Delete({
            "delete from etransportcompany",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int delete(Integer id);

    @Delete({
            "delete from etransportcompany",
            "where id in ${ids}"
    })
    int deletes(@Param("ids") String ids);

    @Select({
            "select count(1) count from etransportcompany",
            "where ${sql}"
    })
    Integer countBySql(@Param("sql") String sql);
}