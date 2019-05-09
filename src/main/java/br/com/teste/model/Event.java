package br.com.teste.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table
public class Event implements Serializable {

    @PrimaryKey
    private EventKey key;
    @CassandraType(type = DataType.Name.UUID)
    private UUID event_id;
    private String domain;

    public Event() {
    }

    public Event(EventKey key, UUID event_id, String domain) {
        this.key = key;
        this.event_id = event_id;
        this.domain = domain;
    }

    public UUID getEvent_id() {
        return event_id;
    }

    public void setEvent_id(UUID event_id) {
        this.event_id = event_id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public EventKey getKey() {
        return key;
    }

    public void setKey(EventKey key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key=" + key +
                ", event_id=" + event_id +
                ", domain='" + domain + '\'' +
                '}';
    }
}
