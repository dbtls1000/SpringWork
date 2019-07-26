package com.biz.bbs.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class BbsDto {
	private long bbs_seq;//	NUMBER	Primary Key,
	private long bbs_main_seq;//	NUMBER	,
	private int bbs_layer;//	NUMBER	,
	private String bbs_date;//	VARCHAR2(10)	,
	private String bbs_time;//	VARCHAR2(20)	,
	private String bbs_auth;//	nVARCHAR2(50)	,
	private String bbs_title;//	nVARCHAR2(50)	,
	private String bbs_content;//	nVARCHAR2(50)
	
	private List<FileVO> bbs_files;
	
}
