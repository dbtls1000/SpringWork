package com.biz.bbs.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BbsReqDto {
	/*
	 * validation에서 사용할 수 있는 유효성 검사항목
	 * NotBlank : 빈칸이어서는 안된다.
	 * NotNull : null값이어서는 안된다.
	 * Null : 반드시 null값이어야 한다.
	 * Size(mix=x,max=y) : x개부터 y개 까지만 인정.
	 * Max(x) : 숫자가 x를 이상하면 안된다.
	 * Min(y) : 숫자가 y를 이하이면 안된다.
	 * DecimalMax(x) : 10진수 x이상이면 안된다.
	 * DecimalMin(y) : 10진수 y이하이면 안된다.
	 * Email : email형식으로 입력되어야 한다.
	 */
	private long bbs_seq;//	NUMBER	Primary Key,
	private long bbs_main_seq;//	NUMBER	,
	private int bbs_layer;//	NUMBER	,
	private String bbs_date;//	VARCHAR2(10)	,
	private String bbs_time;//	VARCHAR2(20)	,
	@NotBlank(message = "작성자 이름을 입력해야 합니다.")
	@Email(message = "작성자는 이메일 형식이어야 합니다.")
	private String bbs_auth;//	nVARCHAR2(50)	,
	private String bbs_title;//	nVARCHAR2(50)	,
	private String bbs_content;//	nVARCHAR2(50)
	
	private List<String> bbs_files;
	private List<String> bbs_origin_files;
	
}
