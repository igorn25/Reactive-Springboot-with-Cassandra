package br.com.teste.service;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import br.com.teste.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class EventService {
    
    @Autowired
    EventRepository eventRepository;


    public void createEvent(Event newEvent) {
        try {
            UUID uuid = UUID.randomUUID();
            newEvent.setEvent_id(uuid);
            eventRepository.insert(newEvent).subscribe();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Flux<Event> findAll() {
        return eventRepository.findAll();
    }

    public Mono<Event>findById(EventKey key) {
        return eventRepository.findByKeyEventNameAndKeyClientId(key.getEventName(),key.getClientId());
    }

    public void updateEvent(Event updatedEvent) {
//        Mono<Event> monoEvent = eventRepository.findById(newEvent.getKey());
//        if(monoEvent.blockOptional().isPresent()){
//            Event event = monoEvent.blockOptional().get();
//            event.setDomain(newEvent.getDomain());
//            eventRepository.save(event).subscribe();
//        }

        eventRepository.save(updatedEvent).subscribe();

    }

    public void deleteEvent(EventKey key) {
        eventRepository.deleteById(key).subscribe();

    }

    public EventKey getPK(String pkString){
        String[] idPk = pkString.split(",");
        return new EventKey(idPk[0],idPk[1]);

    }
}
