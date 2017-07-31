package team.kirohuji.OrderManagerSystem.entity;

public class Item {
	private Integer id;
	
	private String iId;
	
	private Integer status;

	private Integer number;

	private Integer goodsId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getiId() {
		return iId;
	}

	public void setiId(String iId) {
		this.iId = iId == null ? null : iId.trim();
	}
	@Override
	public String toString() {
		return "Item [iId=" + iId + ", status=" + status + ", number=" + number+",";
	}


}