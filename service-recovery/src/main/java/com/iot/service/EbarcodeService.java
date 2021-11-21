package com.iot.service;
  
import com.iot.bean.Ebarcode;
import com.iot.bean.Ebarcodev;

import java.util.List;

public interface EbarcodeService {
    public List<Ebarcode> select(String sql);

    public List<Ebarcode> selectAll();

    public List<Ebarcode> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ebarcode> selectBySql(String sql);

    public Ebarcode selectByPrimaryKey(Integer id);

    public List<Ebarcodev> selectV(String sql);

    public List<Ebarcodev> selectVAll();

    public List<Ebarcodev> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

    public List<Ebarcodev> selectVBySql(String sql);

    public Ebarcodev selectVByPrimaryKey(Integer id);

    public int insert(Ebarcode record);

    public int update(Ebarcode record);

    public int delete(Integer id);

    public int deletes(String ids);
}