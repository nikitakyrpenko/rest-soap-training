package com.micka.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "balance")
    @NotNull
    private Integer balance;

    @Column(name = "charge")
    @NotNull
    private Double charge;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;


    public AccountEntity() {
    }

    public AccountEntity(Integer id, @NotNull Integer balance, @NotNull Double charge, UserEntity holder) {
        this.id = id;
        this.balance = balance;
        this.charge = charge;
        this.user = holder;
    }

    public AccountEntity(@NotNull Integer balance, @NotNull Double charge, UserEntity holder) {
        this.balance = balance;
        this.charge = charge;
        this.user = holder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public Double getCharge() {
        return charge;
    }

    public UserEntity getHolder() {
        return user;
    }

}
