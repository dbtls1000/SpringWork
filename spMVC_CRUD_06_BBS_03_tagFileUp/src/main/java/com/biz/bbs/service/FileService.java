package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.biz.bbs.mapper.FileDao;
import com.biz.bbs.model.BbsVO;
import com.biz.bbs.model.FileVO;

@Service
public class FileService {
	
	@Autowired
	FileDao fileDao;
	private String upLoadFolder = "c:/bizwork/upload";
	/*
	 * 파일들의 이름을 UUID 이름으로 변경하고
	 * 변경된 파일들의 이름을 FileVO에 담아서 리턴하는 메서드 
	 */
//	public List<FileVO> getFileList(List<MultipartFile> files){
//		List<FileVO> fileList = new ArrayList<FileVO>();
//		for(MultipartFile file : files) {
//			String originName = file.getOriginalFilename();
//			String uuString = UUID.randomUUID().toString();
//			String saveName = uuString+ "_" + originName;
//			
//			fileList.add(FileVO.builder().file_origin_name(originName)
//			.file_name(saveName).build());
//		}
//		return fileList;
//	}
	
	public void uploadFileList(BbsVO bbsVO){
		
		String upLoadFolder = "c:/bizwork/upload";

		// 1.vo에서 파일 리스트를 추출
		List<MultipartFile> files = bbsVO.getBbs_files();
		// 2.vo에서 seq 값 추출
		long bbs_seq = bbsVO.getBbs_seq();
		// 3.업로드된 파일의 개수만큼 반복문 수행
		for(MultipartFile file : files) {
			// 4.파일정보에서 원래 파일이름 추출
			String originName = file.getOriginalFilename();
			// 5.파일이름에 UUID를 추가하여
			String uuString = UUID.randomUUID().toString();
			// 6.UUID와 원래 파일이름을 연결해서
			// 7.저장하는 파일 이름으로 생성
			String saveName = uuString + "_" + originName;
			
			// 9.파일 업로드
			File uploadFile = new File(upLoadFolder,saveName);
			try {
				//서버의 폴더에 저장하기
				file.transferTo(uploadFile);
				//한개의 파일정보를 insert
				fileDao.insert(FileVO.builder()
						.file_bbs_seq(bbs_seq)//tbl_bbs와 tbl_bbs_file을 연결하는 key
						.file_name(saveName)//view에서 확인할 파일명
						.file_origin_name(originName).build());//원래 원본 파일명
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void fileUpLoad(FileVO fileVO) {
		String upLoadFolder = "c:/bizwork/upload/";
		if(fileVO == null) return;
		String saveName = fileVO.getFile_name();
		
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
}
