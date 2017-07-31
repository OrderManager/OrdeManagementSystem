package team.kirohuji.OrderManagerSystem.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private Integer id;

	private String name;

	private String code;
	
	private String password;

	private Integer phone;

	private String address;

	private Double money;

	private Integer ruleId;
	
	public User() {
		super();
	}

	public User(Integer id, String name, String code, String password, Integer phone, String address, Double money,
			Integer ruleId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.money = money;
		this.ruleId = ruleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", account=" + code + ", phone=" + phone + ", money="
				+ money +",NowTime ="+new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date())+"]";
	}
	
}