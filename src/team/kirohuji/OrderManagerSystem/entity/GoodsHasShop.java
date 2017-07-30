package team.kirohuji.OrderManagerSystem.entity;

public class GoodsHasShop {
    private Integer goodsId;

    private Integer shopId;
    
    public GoodsHasShop(Integer goodsId, Integer shopId) {
		super();
		this.goodsId = goodsId;
		this.shopId = shopId;
	}

	public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}