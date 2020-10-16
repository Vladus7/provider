package com.vlad.model.dao.entity;

import java.sql.Date;

public class Order {
    private int id;
    private int user_id;
    private int tariff_id;
    private Date start_support;
    private Date end_support;

    public Order(int id, int user_id, int tariff, Date start_support, Date end_support) {
        this.id = id;
        this.user_id = user_id;
        this.tariff_id = tariff;
        this.start_support = start_support;
        this.end_support = end_support;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTariff_id() {
        return tariff_id;
    }

    public void setTariff_id(int tariff_id) {
        this.tariff_id = tariff_id;
    }

    public Date getStart_support() {
        return start_support;
    }

    public void setStart_support(Date start_support) {
        this.start_support = start_support;
    }

    public Date getEnd_support() {
        return end_support;
    }

    public void setEnd_support(Date end_support) {
        this.end_support = end_support;
    }
}
