package cn.itcast.bos.domain;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 7047110655689713356L;
    private String id;
    private String name;
    private String telephone;
    private String address;

    // 定区编号 （用来表示是否关联定区）
    private String decidedZoneId;

    public String getId() {
        return id;


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDecidedZoneId() {
        return decidedZoneId;
    }

    public void setDecidedZoneId(String decidedZoneId) {
        this.decidedZoneId = decidedZoneId;
    }

}
