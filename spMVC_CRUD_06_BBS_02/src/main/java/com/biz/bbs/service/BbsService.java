package com.biz.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.bbs.mapper.BbsDao;
import com.biz.bbs.model.BbsDto;
import com.biz.bbs.model.BbsVO;
import com.biz.bbs.model.FileVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BbsService {
	@Autowired
	BbsDao bDao;
	@Autowired
	FileService fileService;
	public List<BbsVO> bbsList(){
		List<BbsVO> bbsList = bDao.selectAll();
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
	public int insert(BbsVO bbsVO) {
		// TODO Auto-generated method stub
		// 1. 게시글을 저장
		//	저장후에는 bbsVO.bbs_seq에 새로 생성된 pk값이 담겨있다.
		int ret = bDao.insert(bbsVO);
		//파일의 개수가 0이상이고
		// 만약그렇다라도 0번파일의 이름이 있을 경우에만 실행
		if(bbsVO.getBbs_files().size() >0 &&
				!bbsVO.getBbs_files().get(0)
				.getOriginalFilename().isEmpty()) {
			// 2. 게시글의 bbs_seq가 추가되고 UUID 파일이름이 추가된 fileList를 생성하고
			List<FileVO> fileList = fileService.getFileList(bbsVO);
			
			// 3. 그 리스트를 모두 insert수행
			fileService.fileListInsert(fileList);
//			long bbs_seq = bbsVO.getBbs_seq();
//			log.debug("게시판 SEQ :" +bbsVO.getBbs_seq());
			
		}
	
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
	public BbsVO findBySeq(long bbs_seq) {
		// TODO Auto-generated method stub
		BbsVO bbsVO = bDao.findBySeq(bbs_seq);
		return bbsVO;
	}
	public int update(BbsVO bbsVO) {
		// TODO Auto-generated method stub
		
		int ret = bDao.update(bbsVO);
		
		return 0;
	}
}
