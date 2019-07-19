package com.biz.memo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpService {
	/*
	 * 업로드한 파일을 외부에서 보기위해 폴더를 개방해야 하는데 서버가 위치한 폴더의
	 * resources폴더 아래에 upload폴더를 만들고 그 폴더만 외부에 공개를 한다.
	 * 
	 * 실제 서버가 가지고 있는 resources 폴더의 위치를
	 * 알아야 한다.
	 * 
	 * 실제 서버의 폴더위치를 추출하기 위해서
	 * ServletContext를 이용한다.
	 */
	@Autowired
	ServletContext context;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUpService.class);
	
	
	public String fileup(MultipartFile upFile) {
		// TODO 파일 업로드 서비스
		String serverPath = context.getRealPath("/resources/upload/");
		logger.debug("SERVER PATH:" + serverPath);
		try {
			// 1. up파일에 내용이 없으면 더이상 진행 금지
			if(upFile.isEmpty()) return null;
			// 2.web에서 보낸 파일의 실제 이름추출
			String originFileName = upFile.getOriginalFilename();
			// 3.파일을 byte배열로 변환
			byte[] fileByte = upFile.getBytes();
			// 4.파일의 저장위치를 설정
			// String fileDir = "C:/bizwork/upload";
			File dir = new File(serverPath);
			// 파일을 저장하기 위해 사용할 폴더(디렉토리)가 없는지 검사
			if(!dir.exists()) {
				dir.mkdirs(); //없으면 만들어라
			}
			/*
			 * originalFile이름을 그대로 DB에 저장을 하면
			 * 악의적인 사용자가 기존 업로드된 파일 이름과 같은 이름의 다른 파일을 업로드 하면
			 * 이미 업로드된 파일이 변경된다
			 * 
			 * 서버에 저장할 파일 이름을 변환 시켜야 한다.
			 */
			/*
			 * UUID : 우주적 유일한 키값
			 * 파일이름 앞에 UUID 문자열을 추가하여
			 * 중복된 이름의 파일이 업로드 되는것을 막는다.
			 */
			String uuString = UUID.randomUUID().toString();
			originFileName = uuString + "_" + originFileName;
			// java 1.8이하에서 사용하는 보편적인 코드
			// 5. 서버에 저장할 폴더와 파일이름을 결합한 객체 생성
			File serverFile = new File(serverPath, originFileName);
			
			// 6. 파일 저장 객체 생성
			FileOutputStream fileOut = 
					new FileOutputStream(serverFile);
			// 7. 중간에 버퍼를 생성
			BufferedOutputStream os = 
					new BufferedOutputStream(fileOut);
			// 8. 파일내용을 기록하고 닫기.
			os.write(fileByte);
			os.close();
			
			// commons-io,commons-fileupload를 사용하는 방법
			// 3,6~9코드를 한줄로 요약
			upFile.transferTo(serverFile);
			
			// 9. orginal파일이름을 controller로 리턴
			return originFileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
