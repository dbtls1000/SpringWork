package com.biz.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.biz.bbs.model.BbsVO;

public interface BbsDao {
	
	@Select(" SELECT * FROM tbl_bbs ")
	public List<BbsVO> selectAll();
	
	@InsertProvider(type=BbsSQL.class,method="Bbs_insert_sql")
	@SelectKey(keyProperty = "bbs_seq",
				statement=" SELECT SEQ_BBS.NEXTVAL FROM DUAL ",
				resultType = Long.class,
				before = true)
	public int insert(BbsVO bbsVO);
	
	@Select(" SELECT * FROM tbl_bbs WHERE bbs_seq = #{bbs_seq} ")
	public BbsVO findBySeq(long bbs_seq);
	
	
	
}
