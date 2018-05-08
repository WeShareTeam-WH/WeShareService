package com.ws.model;

public class User {

    private Integer id;
    private Integer openId;
    private String name;
    private String phone;
    private String vartar;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVartar() {
        return vartar;
    }

    public void setVartar(String vartar) {
        this.vartar = vartar;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", openId=" + openId + ", name=" + name + ", phone=" + phone + ", vartar=" + vartar
                + ", deleted=" + deleted + "]";
    }
}
