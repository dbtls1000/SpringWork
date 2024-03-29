package com.biz.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import com.biz.bbs.model.BbsDto;
import com.biz.bbs.model.BbsVO;
import com.biz.bbs.model.FileVO;

public interface BbsDao {
	
	@Select(" SELECT * FROM tbl_bbs WHERE bbs_main_seq = 0 "
			+ " ORDER BY bbs_date DESC, bbs_time DESC ")
	@Results(value= {@Result(property = "bbs_seq",column = "bbs_seq"),
					@Result(property = "bbs_rep_list",
					column = "bbs_seq",
					javaType=List.class,
					many=@Many(select="getRepList"))
			
	})
	public List<BbsDto> selectAll();
	
	/* 
	 * @SelectKey를 사용하여 insert 이전에 미리 bbs_seq값을 생성해 두면
	 * Service나 Controller에서 bbsVO로 부터 bbs_seq를 추출할 수 있다.
	 */
	@InsertProvider(type=BbsSQL.class,method="Bbs_insert_sql")
	@SelectKey(keyProperty = "bbs_seq",
				statement=" SELECT SEQ_BBS.NEXTVAL FROM DUAL ",
				resultType = Long.class,
				before = true)
	public int insert(BbsVO bbsVO);
	
	
	//내용만확인을위한SELECT
	@Select(" SELECT * FROM tbl_bbs WHERE bbs_seq = #{bbs_seq} ")
	public BbsVO findBySeq(long bbs_seq);
	
	
	//fileList까지 묶어서 확인을 위한 select
	@Select(" SELECT * FROM tbl_bbs WHERE bbs_seq = #{bbs_seq} ")
	@Results(value= {@Result(property = "bbs_seq",column = "bbs_seq"),
					@Result(property = "bbs_files",column = "bbs_seq",javaType=List.class,
					many=@Many(select="getFileList"))
			})
	public BbsDto findBySeqForFile(long bbs_seq);
	
	
	@Select(" SELECT * FROM tbl_bbs_file "
			+ " WHERE file_bbs_seq = #{bbs_seq}")
	public List<FileVO> getFileList(long bbs_seq);
	
	@Select(" SELECT * FROM tbl_bbs WHERE bbs_main_seq > 0 AND "
			+ " bbs_main_seq = #{bbs_main_seq} ")
	public List<BbsVO> getRepList(long bbs_main_seq);

	@Select(" SELECT * FROM tbl_bbs ORDER BY bbs_date DESC, bbs_time DESC ")
	@Results(value= {@Result(property = "bbs_seq",column = "bbs_seq"),
			@Result(property = "bbs_files",column = "bbs_seq",javaType=List.class,
			many=@Many(select="getFileList"))
			})
	public List<BbsDto> selectAllForFile();
	
	@Delete(" DELETE FROM tbl_bbs WHERE bbs_seq = #{bbs_seq} ")
	public int delete(long bbs_seq);

	@UpdateProvider(value=BbsSQL.class,method="Bbs_update_sql")
	public int update(BbsVO bbsVO);
	
	
	
}
