package br.com.teste.repository;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveCassandraRepository<Event, EventKey> {

    Flux<Event> findByKeyEventName(String eventName);

    Mono<Event> findByKeyEventNameAndKeyClientId(String eventName, String clientId);

}
