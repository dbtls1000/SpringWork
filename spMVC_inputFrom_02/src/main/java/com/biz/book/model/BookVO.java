package com.biz.book.model;

public class BookVO {
	
	private String b_title;
	private String b_comp;
	private String b_author;
	private int b_price;
	
	
	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookVO(String b_title, String b_comp, String b_author, int b_price) {
		super();
		this.b_title = b_title;
		this.b_comp = b_comp;
		this.b_author = b_author;
		this.b_price = b_price;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_comp() {
		return b_comp;
	}
	public void setB_comp(String b_comp) {
		this.b_comp = b_comp;
	}
	public String getB_author() {
		return b_author;
	}
	public void setB_author(String b_author) {
		this.b_author = b_author;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	@Override
	public String toString() {
		return "BookVO [b_title=" + b_title + ", b_comp=" + b_comp + ", b_author=" + b_author + ", b_price=" + b_price
				+ "]";
	}
	
	
	
}
