package com.iot.service;

import com.iot.bean.Etransportcompany;
import com.iot.bean.Etransportcompanyv;

import java.util.List;

/**
 * @Auther: Barry
 * @Date: 2019/7/16
 * @Description: 省service
 */
public interface EtransportcompanyService {
    public List<Etransportcompany> select(String sql);

    public List<Etransportcompany> selectAll();

    public List<Etransportcompany> selectBySql(String sql);

    public List<Etransportcompany> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public Etransportcompany selectByPrimaryKey(Integer id);

    public List<Etransportcompanyv> selectV(String sql);

    public List<Etransportcompanyv> selectVAll();

    public List<Etransportcompanyv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Etransportcompanyv> selectVBySql(String sql);

    public Etransportcompanyv selectVByPrimaryKey(Integer id);

    public int insert(Etransportcompany record);

    public int update(Etransportcompany record);

    public int delete(Integer id);

    public int deletes(String ids);

    public Integer countBySql(String sql);


}
