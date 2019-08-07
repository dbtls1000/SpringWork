package com.biz.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.bbs.mapper.BbsDao;
import com.biz.bbs.model.BbsDto;
import com.biz.bbs.model.BbsReqDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BbsService {
	
	@Autowired
	BbsDao bDao;
	@Autowired
	AjaxFileService afService;
	
	public List<BbsDto> bbsList(){
		List<BbsDto> bbsList = bDao.selectAll();
		return bbsList;
	}
	public List<BbsDto> bbsListForFile(){
		List<BbsDto> bbsList = bDao.selectAllForFile();
		return bbsList;
	}
	
	/*
	 * 글쓰기를 수행할때 파일을 같이 업로드하면 해당하는 글의 bbs_seq를 조회하여
	 * file table에 저장할때 같이 저장을 수행 해줘야 한다.
	 * 1.게시글을 저장 2.게시글의 bbs_seq를 조회 
	 * 3.파일정보를 insert할때 해당하는 bbs_seq를 같이 저장해주어야 한다.
	 * 4.게시글을 조회할때 해당하는 파일들을 같이 조회 
	 */
	public int insert(BbsReqDto bbsReqDto) {
		// TODO Auto-generated method stub
		// 게시판 내용 저장
		int ret = bDao.insert(bbsReqDto);
		// 업로드파일 정보 저장
		int file_ret = afService.insert(bbsReqDto);
		return ret;
	}
	
	
	public BbsDto getContent(long bbs_seq) {
		// TODO Auto-generated method stub
		BbsDto bbsDto = bDao.findBySeqForFile(bbs_seq);
		return bbsDto;
	}
	
	
	public int delete(long bbs_seq) {
		// TODO Auto-generated method stub
		int ret = bDao.delete(bbs_seq);
		return ret;
	}
	public BbsReqDto findBySeq(long bbs_seq) {
		// TODO Auto-generated method stub
		BbsReqDto bbsReqDto = bDao.findBySeq(bbs_seq);
		return bbsReqDto;
	}
	public int update(BbsReqDto bbsReqDto) {
		// TODO Auto-generated method stub
		int ret = bDao.update(bbsReqDto);
		
		return ret;
	}
}
