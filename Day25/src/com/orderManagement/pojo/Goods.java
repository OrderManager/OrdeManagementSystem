package com.orderManagement.pojo;

public class Goods {

	private int id;
	private double price;
	private String name;
	private String content;
	private int isdelete;
	private int iventry;

	public Goods(int id, double price, String name, String content, int isdelete, int iventry) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.content = content;
		this.isdelete = isdelete;
		this.iventry = iventry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public int getIventry() {
		return iventry;
	}

	public void setIventry(int iventry) {
		this.iventry = iventry;
	}

}
