package br.com.teste.controller;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import br.com.teste.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/isAlive")
    public String isAlive(){
        return "alive";
    }

    @PostMapping
    public void createEvent(@RequestBody Event event){
        eventService.createEvent(event);
    }

    @GetMapping
    public Flux<Event> findAllEvents(){
        return eventService.findAll();
    }

    @GetMapping("/findOne")
    public Mono<Event> findById(@RequestBody EventKey key){
        return eventService.findById(key);
    }

    @PutMapping
    public void updateEvent(@RequestBody Event event){
        eventService.updateEvent(event);
    }

    @DeleteMapping
    public void deleteEvent(@RequestBody EventKey key){
            eventService.deleteEvent(key);
    }

}
