package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.bbs.mapper.FileDao;
import com.biz.bbs.model.BbsReqDto;
import com.biz.bbs.model.FileVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AjaxFileService {
	
	@Autowired
	FileDao fileDao;
	/*
	 * 변수에 final이 붙으면
	 * 이 변수의 값을 변경할 수 없다.
	 */
	private final String upLoadFolder = "c:/bizwork/upload";
	
	public List<FileVO> upLoads(MultipartHttpServletRequest files) {
		List<MultipartFile> fileList = files.getFiles("files");
		log.debug("파일개수" + fileList.size());
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		for(MultipartFile file : fileList) {
			
			String saveName = this.upLoad(file);
			
			fileVOList.add(FileVO.builder()
			.file_origin_name(file.getOriginalFilename())
			.file_name(saveName).build());
		
		}
		return fileVOList;
	}
	
	public String upLoad(MultipartFile file) {
		
		// 업로드할 파일 정보가 없으면
		// 더이상 코드 진행x
		if(file.isEmpty() || file ==null) return null;
		
		String originName = file.getOriginalFilename();
		String uuString = UUID.randomUUID().toString();
		String saveName = uuString + "_" + originName;
		
		//파일을 업로드 하기전에 저장할 폴더가 없으면 새로운 디렉토리를 생성
		File saveDir = new File(upLoadFolder);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		File saveFile = new File(upLoadFolder,saveName);
		
		
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveName;
	}
	
	public boolean file_delete(long file_seq) {
		// TODO Auto-generated method stub
		// 1.파일정보 추출
		FileVO fileVO = fileDao.findBySeq(file_seq);
		// 2.파일의 물리적 경로 생성 
		File delFile = new File(upLoadFolder, fileVO.getFile_name());
		// 3. 파일이 있는지 확인한 후 
		if(delFile.exists()) {
			// 4.파일 삭제
			delFile.delete();
			// 5.DB정보 삭제
			fileDao.delete(file_seq);
			return true;
		}
		return false;
	}
	
	/*
	 * mybatis에게 transaction방식으로 다중 쿼리를 실행하라는 어노테이션
	 */
	@Transactional
	public int insert(BbsReqDto bbsReqDto) {
		// TODO Auto-generated method stub
		List<String> bbs_files = bbsReqDto.getBbs_files();
		
		if(bbs_files == null) return -1;
		
		for(String file_name : bbs_files) {
			// UUID를 제거하고 origin이름을 추출
			long bbs_seq = bbsReqDto.getBbs_seq();
			String file_origin_name = file_name.substring(37);
			FileVO fVO = FileVO.builder()
							.file_name(file_name)
							.file_origin_name(file_origin_name)
							.file_bbs_seq(bbs_seq).build();
			fileDao.insert(fVO);
			
//			fileDao.insert(FileVO.builder()
//							.file_name(file_name)
//							.file_origin_name(file_name.substring(37))
//							.file_bbs_seq(bbsReqDto.getBbs_seq()).build());
		}
		return 0;
	}

	/*
	 * 첨부파일을 삭제하는 절차
	 * 1. tbl_bbs_file에서 첨부파일 목록 추출
	 * 2. 해당하는 실제 파일을 삭제
	 * 3. table에 해당 정보 삭제
	 */
	public int files_delete(long bbs_seq) {
		// TODO Auto-generated method stub
		List<FileVO> fileList = fileDao.finByBbsSeq(bbs_seq);
		for(FileVO fileVO : fileList) {
			//1.File객체생성
			File delFile = new File(upLoadFolder, fileVO.getFile_name());
			//2.실제파일을 삭제
			if(delFile.exists()) delFile.delete();
		}
		int ret = fileDao.deletes(bbs_seq);
		return 0;
	}
}
