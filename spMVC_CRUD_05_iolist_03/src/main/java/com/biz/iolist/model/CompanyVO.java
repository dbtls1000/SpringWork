package com.biz.iolist.model;

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
public class CompanyVO {
	private String c_code;
	private String c_name;
	private String c_ceo;
	private String c_tel;
	private String c_addr;
}
