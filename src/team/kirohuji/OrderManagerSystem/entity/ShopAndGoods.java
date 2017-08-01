package team.kirohuji.OrderManagerSystem.entity;

public class ShopAndGoods {
	private String shopName;
	private String goodsName;
	private double price;
	private int idDelete;

	private int inventry;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventry() {
		return inventry;
	}

	public void setInventry(int inventry) {
		this.inventry = inventry;
	}

	public int getIdDelete() {
		return idDelete;
	}

	public void setIdDelete(int idDelete) {
		this.idDelete = idDelete;
	}

	@Override
	public String toString() {
		return "ShopAndGoods [shopName=" + shopName + ", goodsName=" + goodsName + ", price=" + price + ", inventry=" + inventry + "]";
	}



}
