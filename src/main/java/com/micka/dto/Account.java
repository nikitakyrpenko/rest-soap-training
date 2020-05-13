package com.micka.dto;

public class Account {

    private Integer id;
    private final Integer balance;
    private final Double charge;
    private final Integer userId;

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
}
