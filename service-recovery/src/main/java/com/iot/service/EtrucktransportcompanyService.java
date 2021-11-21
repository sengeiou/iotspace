package com.iot.service;
  
import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;

import java.util.List;

public interface EtrucktransportcompanyService {
    public List<Etrucktransportcompany> select(String sql);

    public List<Etrucktransportcompany> selectAll();

    public List<Etrucktransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktransportcompany> selectBySql(String sql);

    public Etrucktransportcompany selectByPrimaryKey(Integer id);

    public List<Etrucktransportcompanyv> selectV(String sql);

    public List<Etrucktransportcompanyv> selectVAll();

    public List<Etrucktransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etrucktransportcompanyv> selectVBySql(String sql);

    public Etrucktransportcompanyv selectVByPrimaryKey(Integer id);

    public int insert(Etrucktransportcompany record);

    public int update(Etrucktransportcompany record);

    public int delete(Integer id);

    public int deletes(String ids);

    public List<Etrucktransportcompanyv>selectVByStatusAndSqlPage(Integer page, Integer rows, String sort, String order, String sql);

}