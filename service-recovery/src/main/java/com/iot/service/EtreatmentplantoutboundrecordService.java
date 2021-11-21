package com.iot.service;

import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;

import java.util.List;

public interface EtreatmentplantoutboundrecordService {
	public List<Etreatmentplantoutboundrecord> select(String sql);

	public List<Etreatmentplantoutboundrecord> selectAll();

	public List<Etreatmentplantoutboundrecord> selectByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Etreatmentplantoutboundrecord> selectBySql(String sql);

	public Etreatmentplantoutboundrecord selectByPrimaryKey(Integer id);

	public List<Etreatmentplantoutboundrecordv> selectV(String sql);

	public List<Etreatmentplantoutboundrecordv> selectVAll();

	public List<Etreatmentplantoutboundrecordv> selectVByPage(Integer page, Integer rows, String sort, String order, String sql);

	public List<Etreatmentplantoutboundrecordv> selectVBySql(String sql);

	public Etreatmentplantoutboundrecordv selectVByPrimaryKey(Integer id);

	public int insert(Etreatmentplantoutboundrecord record);

	public int update(Etreatmentplantoutboundrecord record);

	public int delete(Integer id);

	public int deletes(String ids);
}