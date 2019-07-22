package com.biz.memo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.biz.memo.dao.MemoDao;
import com.biz.memo.model.MemoVO;

@Service
public class MemoService {
	private static final Logger logger = LoggerFactory.getLogger(MemoService.class);
	@Autowired
	SqlSession sqlSession;
	
	MemoDao mDao;
	@Autowired
	public void getMapper() {
		mDao = sqlSession.getMapper(MemoDao.class);
	}
	
	public int insert(MemoVO memoVO) {
		// TODO Auto-generated method stub
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		/* 입력폼에서 날짜와 시각을 직접 입력하지 않고
		 * Service에서 데이터를 입력하기 전에
		 * 시스템의 날짜와 시각을 만들어서 vo에 주입하고
		 * insert를 수행한다.
		 * 
		 */
		//1.8 이하버전에서 사용
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		logger.debug("CUR" + curDate + "/" + curTime);
		//1.8이상에서 사용
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();
		
		curDate = ld.toString();
		curTime = lt.toString();
		
		curTime = curTime.substring(0,8);
		
		logger.debug("LOCAL" + curDate + "/" + curTime);
		
		memoVO.setMo_date(curDate);
		memoVO.setMo_time(curTime);
		
		logger.debug(memoVO.toString());
		
		int ret = mDao.insert(memoVO);
		return ret;
	}

	public List<MemoVO> selectAll() {
		// TODO Auto-generated method stub
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		List<MemoVO> memoList = mDao.selectAll();
		
		return memoList;
	}

	public MemoVO findBySeq(long mo_seq) {
		// TODO Auto-generated method stub
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		
		MemoVO memoVO = mDao.findBySeq(mo_seq);
		
		return memoVO;
	}
	
	public int write(MemoVO memoVO) {
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		/*
		 * 만약 메모를 새로 작성하는 경우는 form에서 mo_seq에 default값으로 0을 세팅할 것이고
		 * 수정으로 작성하는 경우는 controller에서 보낸 memoVO의 mo_seq값이 세팅되어 있을 것이다
		 * 
		 * 즉 매개변수라 받은memoVO의 mo_seq값이 0보다 크면
		 * 기존 데이터를 수정하는 상태일 것이고
		 * 아니면 새로 작성한 상태일 것이므로
		 * 
		 * 이 값을 검사하여 update,insert를 수행
		 */
		long mo_seq = memoVO.getMo_seq();
		if(mo_seq >0) mDao.update(memoVO);
		else mDao.insert(memoVO);
		
		return 0;
	}

	public int delete(long mo_seq) {
		// TODO Auto-generated method stub
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		int ret = mDao.delete(mo_seq);
		return ret;
	}

	public int update(MemoVO memoVO) {
		// TODO Auto-generated method stub
		// MemoDao mDao = sqlSession.getMapper(MemoDao.class);
		int ret = mDao.update(memoVO);
		return ret;
	}

	
}
