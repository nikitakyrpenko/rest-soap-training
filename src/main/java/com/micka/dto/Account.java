package com.micka.dto;


public class Account {

    private Integer id;
    private Integer balance;
    private Double charge;
    private Integer userId;

    public Account(Integer id,Integer balance, Double charge, Integer userId) {
        this.id = id;
        this.balance = balance;
        this.charge = charge;
        this.userId = userId;
    }

    public Account(Integer balance, Double charge, Integer userId) {
        this.balance = balance;
        this.charge = charge;
        this.userId = userId;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getBalance() {
        return balance;
    }

    public Double getCharge() {
        return charge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
