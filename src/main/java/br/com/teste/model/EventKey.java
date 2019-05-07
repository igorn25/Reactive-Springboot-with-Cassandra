package br.com.teste.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;


@PrimaryKeyClass
public class EventKey  implements Serializable {

    @PrimaryKeyColumn(name = "event_name",ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String eventName;
    @PrimaryKeyColumn(name = "client_id", ordinal = 1)
    private String clientId;

    public EventKey() {
    }

    public EventKey(String eventName, String clientId) {
        this.eventName = eventName;
        this.clientId = clientId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "EventKey{" +
                "eventName='" + eventName + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey eventKey = (EventKey) o;
        return Objects.equals(eventName, eventKey.eventName) &&
                Objects.equals(clientId, eventKey.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, clientId);
    }
}
