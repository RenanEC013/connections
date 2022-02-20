package com.pingr.Connections.core;

import javax.persistence.*;

@Entity
@Table
public class Friendship {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acc_1_id")
    Account acc1;
    @ManyToOne
    @JoinColumn(name = "acc_2_id")
    Account acc2;

    public Account getAcc2() {
        return acc2;
    }

    public Account getAcc1() {
        return acc1;
    }

    public Friendship() {
    }

    public Friendship(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAcc1(Account acc1) {
        this.acc1 = acc1;
    }

    public void setAcc2(Account acc2) {
        this.acc2 = acc2;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}
