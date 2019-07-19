package com.biz.book.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.book.dao.MemberDao;
import com.biz.book.model.MemberVO;
@Service
public class MemberService {
	@Autowired
	SqlSession sqlSession;
	
	/*
	 * 회원가입 정책
	 * 1.최초로 회원가입을 하면 그 회원은 ADMIN으로 설정
	 * 2. 그 후에 가입한 회원은 user로 설정
	 */
	public int insert(MemberVO memberVO) {
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		// 1. 회원테이블에 데이터가 있는지
		List<MemberVO> mList = mDao.selectAll();
		// 2. 없으면(==null) ADMIN 있으면(!-null) USER
		// 3. DB에 저장
		if(mList.size() > 0) memberVO.setM_role("USER");
		else memberVO.setM_role("ADMIN");
		int ret = mDao.insert(memberVO);
		
		return ret;
	}

	public boolean member_check(String m_userid, String m_password) {
		// TODO Auto-generated method stub
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		MemberVO memberVO = mDao.findByUserId(m_userid);
		// 회원 id와 비밀번호를 검사하여 일치하는지 체크
		if(memberVO.getM_userid().equalsIgnoreCase(m_userid) &&
				memberVO.getM_password().equals(m_password)) {
			return true;
		}
			
		return false;
	}

	public MemberVO findByUserId(String m_userid) {
		// TODO Auto-generated method stub
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		MemberVO memberVO = mDao.findByUserId(m_userid);
		return memberVO;
		
	}
}
