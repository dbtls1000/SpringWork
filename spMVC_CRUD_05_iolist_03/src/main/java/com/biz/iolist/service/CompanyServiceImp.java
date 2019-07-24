package com.biz.iolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.mapper.CompanyDao;
import com.biz.iolist.model.CompanyVO;
@Service
public class CompanyServiceImp implements CompanyService {
	@Autowired
	CompanyDao cDao;
	@Override
	public List<CompanyVO> selectAll() {
		// TODO Auto-generated method stub
		List<CompanyVO> cList = cDao.selectAll();
		return cList;
	}
	
	@Override
	public List<CompanyVO> findByCName(String c_name) {
		// TODO Auto-generated method stub
		List<CompanyVO> cList = cDao.findByCName(c_name);
		return cList;
	}

	@Override
	public CompanyVO findByCCode(String c_code) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<CompanyVO> findByCTel(String c_tel) {
		// TODO Auto-generated method stub
		return null;
	}

}
