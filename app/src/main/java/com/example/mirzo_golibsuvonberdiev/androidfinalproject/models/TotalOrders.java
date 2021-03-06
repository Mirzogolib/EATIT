package com.example.mirzo_golibsuvonberdiev.androidfinalproject.models;

import java.util.List;

/**
 * Created by mirzo-golibsuvonberdiev on 12/20/18.
 */

public class TotalOrders {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<Order> orderList;
    private String status;

    public TotalOrders() {
    }

    public TotalOrders(String phone, String name, String address, String total, List<Order> orderList, String status) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.orderList = orderList;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
