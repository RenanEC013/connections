package com.pingr.Connections.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingr.Connections.core.Account;

import java.util.Map;

@JsonSerialize
public class AccountRemovedEvent {

    @JsonProperty
    private String eventType;

    @JsonProperty
    private Long accountId;

    @JsonProperty
    private Map<String, Object> payload;

    public AccountRemovedEvent() {
    }

    public AccountRemovedEvent(String eventType, Long accountId, Map<String, Object> payload) {
        this.eventType = eventType;
        this.accountId = accountId;
        this.payload = payload;
    }

    // operação inversa do método estático AccountRemovedEvent.of(Account)
    public Account extract() {
        return new Account(this.accountId);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AccountRemovedEvent{" +
                "eventType='" + eventType + '\'' +
                ", accountId=" + accountId +
                ", payload=" + payload +
                '}';
    }
}
