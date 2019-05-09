package br.com.teste.service;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {
    Mono<Event> createEvent(Event newEvent);
    Flux<Event> findAll();
    Mono<Event> findById(EventKey key);
    void updateEvent(Event updatedEvent);
    void deleteEvent(EventKey key);
    Mono<Event> findById(String key);
}
