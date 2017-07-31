package team.kirohuji.OrderManagerSystem.entity;

public class Bill {
    private Integer id;

    private String uId;

    private String totalNumber;

    private String ordercol;

    private Integer userId;

    private Integer itemId;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber == null ? null : totalNumber.trim();
    }

    public String getOrdercol() {
        return ordercol;
    }

    public void setOrdercol(String ordercol) {
        this.ordercol = ordercol == null ? null : ordercol.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}