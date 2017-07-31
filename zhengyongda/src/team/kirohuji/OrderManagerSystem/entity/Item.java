package team.kirohuji.OrderManagerSystem.entity;

public class Item {
    private Integer id;

    private Integer status;

    private Integer number;

    private Integer goodsId;

    private String iId;

    private Integer itemCartId;

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

    public Integer getItemCartId() {
        return itemCartId;
    }

    public void setItemCartId(Integer itemCartId) {
        this.itemCartId = itemCartId;
    }
}