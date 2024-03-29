package com.biz.memo.model;

public class MemoVO {
	private long mo_seq;//	NUMBER	Primary Key	
	private String mo_date;//	VARCHAR2(10)		Not null
	private String mo_time;
	private String mo_author;//	nVARCHAR2(50)		
	private String mo_subject;//	nVARCHAR2(125)		
	private String mo_memo;//	nVARCHAR2(1000)	
	private String mo_file;
	
	public MemoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemoVO(long mo_seq, String mo_date, String mo_time, String mo_author, String mo_subject, String mo_memo,
			String mo_file) {
		super();
		this.mo_seq = mo_seq;
		this.mo_date = mo_date;
		this.mo_time = mo_time;
		this.mo_author = mo_author;
		this.mo_subject = mo_subject;
		this.mo_memo = mo_memo;
		this.mo_file = mo_file;
	}
	public long getMo_seq() {
		return mo_seq;
	}
	public void setMo_seq(long mo_seq) {
		this.mo_seq = mo_seq;
	}
	public String getMo_date() {
		return mo_date;
	}
	public void setMo_date(String mo_date) {
		this.mo_date = mo_date;
	}
	public String getMo_time() {
		return mo_time;
	}
	public void setMo_time(String mo_time) {
		this.mo_time = mo_time;
	}
	public String getMo_author() {
		return mo_author;
	}
	public void setMo_author(String mo_author) {
		this.mo_author = mo_author;
	}
	public String getMo_subject() {
		return mo_subject;
	}
	public void setMo_subject(String mo_subject) {
		this.mo_subject = mo_subject;
	}
	public String getMo_memo() {
		return mo_memo;
	}
	public void setMo_memo(String mo_memo) {
		this.mo_memo = mo_memo;
	}
	public String getMo_file() {
		return mo_file;
	}
	public void setMo_file(String mo_file) {
		this.mo_file = mo_file;
	}
	@Override
	public String toString() {
		return "MemoVO [mo_seq=" + mo_seq + ", mo_date=" + mo_date + ", mo_time=" + mo_time + ", mo_author=" + mo_author
				+ ", mo_subject=" + mo_subject + ", mo_memo=" + mo_memo + ", mo_file=" + mo_file + "]";
	}
	
	
	
	
}
