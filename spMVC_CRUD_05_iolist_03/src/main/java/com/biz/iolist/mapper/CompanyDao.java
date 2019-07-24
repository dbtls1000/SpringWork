package com.biz.iolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.iolist.model.CompanyVO;

public interface CompanyDao {
	@Select(" SELECT * FROM tbl_comp ")
	public List<CompanyVO> selectAll();
	@Select(" SELECT * FROM tbl_comp WHERE c_code = #{c_code} ")
	public CompanyVO findByCCode(String c_code);
	@Select(" SELECT * FROM tbl_comp "
			+ " WHERE c_name LIKE '%' || #{c_name} || '%' ")
	public List<CompanyVO> findByCName(String c_name);
	@Select(" SELECT * FROM tbl_comp "
			+ " WHERE c_tel LIKE '%' || #{c_tel} || '%' ")
	public List<CompanyVO> findByCTel(String c_tel);
}
