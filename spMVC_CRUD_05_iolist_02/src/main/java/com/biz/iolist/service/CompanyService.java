package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.iolist.model.CompanyVO;

public interface CompanyService {
	
	
	public List<CompanyVO> selectAll();
	
	public CompanyVO findByCCode(String c_code);
	
	public List<CompanyVO> findByCName(String c_name);
	
	public List<CompanyVO> findByCTel(String c_tel);
}
