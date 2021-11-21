package com.iot.service;
  
import com.iot.bean.EtreatmentplantOutBoundVo;
import com.iot.bean.Etrucktreatmentplantreceive;
import com.iot.bean.Etrucktreatmentplantreceivev;

import java.util.List;

public interface EtrucktreatmentplantreceiveService {
    public List<Etrucktreatmentplantreceive> select(String sql);

    public List<Etrucktreatmentplantreceive> selectAll();

    public List<Etrucktreatmentplantreceive> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktreatmentplantreceive> selectBySql(String sql);

    public Etrucktreatmentplantreceive selectByPrimaryKey(Integer id);

    public List<Etrucktreatmentplantreceivev> selectV(String sql);

    public List<Etrucktreatmentplantreceivev> selectVAll();

    public List<Etrucktreatmentplantreceivev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktreatmentplantreceivev> selectVBySql(String sql);

    public Etrucktreatmentplantreceivev selectVByPrimaryKey(Integer id);

    public int insert(Etrucktreatmentplantreceive record);

    public int update(Etrucktreatmentplantreceive record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<EtreatmentplantOutBoundVo> selectOutBoundVoByPage(Integer page, Integer rows, String sort, String order, String sql);
}