package team.kirohuji.OrderManagerSystem.entity;

public class Goods {
    private Integer id;

    private Double price;

    private String name;

    private String content;

    private Integer isDelete;

    private Integer inventry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getInventry() {
        return inventry;
    }

    public void setInventry(Integer inventry) {
        this.inventry = inventry;
    }
}