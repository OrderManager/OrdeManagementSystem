package team.kirohuji.OrderManagerSystem.entity;

public class Shop {
    private Integer id;

    private Integer isOpen;

    private String name;

    private String content;

    public Integer getId() {
        return id;
    }

    @Override
	public String toString() {
		return "Shop [name=" + name + ", content=" + content + "]";
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
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
}