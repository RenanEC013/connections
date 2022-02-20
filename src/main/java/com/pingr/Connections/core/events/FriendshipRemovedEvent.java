package com.pingr.Connections.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingr.Connections.core.Account;
import com.pingr.Connections.core.Friendship;

import java.util.Map;

@JsonSerialize
public class FriendshipRemovedEvent {

    @JsonProperty
    private String eventType;

    @JsonProperty
    private Long friendshipId;

    @JsonProperty
    private Map<String, Object> payload;

    public FriendshipRemovedEvent() {
    }

    public FriendshipRemovedEvent(String eventType, Long friendshipId, Map<String, Object> payload) {
        this.eventType = eventType;
        this.friendshipId = friendshipId;
        this.payload = payload;
    }

    // operação inversa do método estático FriendshipRemovedEvent.of(Friendship)
    public Friendship extract() {
        return new Friendship(this.friendshipId);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getFriendshipId() {
        return friendshipId;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "FriendshipRemovedEvent{" +
                "eventType='" + eventType + '\'' +
                ", friendshipId=" + friendshipId +
                ", payload=" + payload +
                '}';
    }
}