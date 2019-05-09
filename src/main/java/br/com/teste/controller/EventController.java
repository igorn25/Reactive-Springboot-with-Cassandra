package br.com.teste.controller;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import br.com.teste.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventServiceImpl eventServiceImpl;

    @GetMapping("/isAlive")
    public String isAlive(){
        return "alive";
    }

    @PostMapping
    public Mono<Event> createEvent(@RequestBody Event event){
       return eventServiceImpl.createEvent(event);
    }

    @GetMapping
    public Flux<Event> findAllEvents(){
        return eventServiceImpl.findAll();
    }

    @GetMapping("/findOne")
    public Mono<Event> findById(@RequestBody EventKey key){
        return eventServiceImpl.findById(key);
    }

    @GetMapping("/findOne/{key}")
    public Mono<Event> findById(@PathVariable String key){return eventServiceImpl.findById(key);}

    @PutMapping
    public void updateEvent(@RequestBody Event event){
        eventServiceImpl.updateEvent(event);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEvent(@RequestBody EventKey key){
            try{
                eventServiceImpl.deleteEvent(key);
            }catch (Exception e){
                return new ResponseEntity<>( HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Success during exclusion!",HttpStatus.OK);

    }

}
