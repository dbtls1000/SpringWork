package com.biz.bbs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	private String m_userid;
	private String m_password;
	private String m_name;
	private String m_email;
	private String m_tel;
	private String m_role;
}
