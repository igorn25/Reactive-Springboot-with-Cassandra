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
public class EventServiceImpl implements EventService{
    
    @Autowired
    EventRepository eventRepository;

    @Override
    public Mono<Event> createEvent(Event newEvent) {
        try {
            UUID uuid = UUID.randomUUID();
            newEvent.setEvent_id(uuid);
            return eventRepository.save(newEvent);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Flux<Event> findAll() {
        return eventRepository.findAll();
    }
    @Override
    public Mono<Event>findById(EventKey key) {

        eventRepository.findByKeyEventNameAndKeyClientId(key.getEventName(),key.getClientId());

        return eventRepository.findByKeyEventNameAndKeyClientId(key.getEventName(),key.getClientId());
    }
    @Override
    public void updateEvent(Event updatedEvent) {
//        Mono<Event> monoEvent = eventRepository.findById(newEvent.getKey());
//        if(monoEvent.blockOptional().isPresent()){
//            Event event = monoEvent.blockOptional().get();
//            event.setDomain(newEvent.getDomain());
//            eventRepository.save(event).subscribe();
//        }
        eventRepository.save(updatedEvent).subscribe();

    }
    @Override
    public Mono<Event> findById(String key) {
        return findById(getPK(key));
    }
    @Override
    public void deleteEvent(EventKey key) {
        eventRepository.deleteById(key).subscribe();
    }

    private EventKey getPK(String pkString){
        String[] idPk = pkString.split("-");
        return new EventKey(idPk[0],idPk[1]);

    }
}
