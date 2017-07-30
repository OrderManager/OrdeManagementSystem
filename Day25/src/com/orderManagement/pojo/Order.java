package com.orderManagement.pojo;

public class Order {

	private int id;
	private int status;
	private String number;
	private int goodsid;
	private int ordercartid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getOrdercartid() {
		return ordercartid;
	}

	public void setOrdercartid(int ordercartid) {
		this.ordercartid = ordercartid;
	}

}
