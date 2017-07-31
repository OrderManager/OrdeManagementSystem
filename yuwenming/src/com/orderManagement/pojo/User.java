package com.orderManagement.pojo;

public class User {

	private int id;
	private String name;
	private String password;
	private String phone;
	private String address;
	private double money;
	private int ruleid;

	public User(int id, String name, String password, String phone, String address, double money, int ruleid) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.money = money;
		this.ruleid = ruleid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRuleid() {
		return ruleid;
	}

	public void setRule_id(int ruleid) {
		this.ruleid = ruleid;
	}

}
