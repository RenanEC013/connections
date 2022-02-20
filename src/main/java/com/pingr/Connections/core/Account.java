package com.pingr.Connections.core;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Account {
    @Id
    private Long id;

    private Set<Integer> friendships;

    public Account() {
    }

    public Account(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }

    public Set<Integer> getFriendships() {
        return friendships;
    }

    public void setFriendships(Set<Integer> friendships) {
        this.friendships = friendships;
    }
}
