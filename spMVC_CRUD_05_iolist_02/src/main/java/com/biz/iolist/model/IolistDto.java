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

public class IolistDto {
	private long io_seq;
	private String io_date;
	private String io_inout;
	private String io_ccode;
	private String io_cname;
	private String io_pcode;
	private String io_pname;
	private int io_amt;
	private int io_price;
	private int io_itotal;
	private int io_ototal;
}
