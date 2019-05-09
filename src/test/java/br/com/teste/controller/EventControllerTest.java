package br.com.teste.controller;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import br.com.teste.service.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;


@RunWith(MockitoJUnitRunner.class)
@WebFluxTest
public class EventControllerTest {

    @InjectMocks
    private EventController eventController;
    @Mock
    private EventService eventService;


    @Test
    public void findAllEvents(){

        EventKey eventKey1 = new EventKey("Key1","1");
        EventKey eventKey2 = new EventKey("Key2","2");

        Event event1 = new Event(eventKey1,UUID.randomUUID(),"Domain 1");
        Event event2 = new Event(eventKey2,UUID.randomUUID(),"Domain 2");

        Flux<Event> fluxEvent = Flux.just(event1,event2);

        Mockito.when(this.eventService.findAll()).thenReturn(fluxEvent);

        Assert.assertEquals(eventController.findAllEvents(),fluxEvent);
    }

    @Test
    public void findById(){
        //todo erro, corrigir
        EventKey eventKey = new EventKey("Key1","1");
        Event event = new Event (eventKey,UUID.randomUUID(),"Domain");


        Mono<Event> monoEvent = Mono.just(event);
        Mockito.when(this.eventService.findById(eventKey)).thenReturn(Mono.just(event));

        Assert.assertEquals(eventController.findById(eventKey),monoEvent);
    }

    @Test public void deleteEvent(){
        EventKey eventKey = new EventKey("Key1","1");

        Mockito.when(this.eventController.deleteEvent(eventKey)).thenThrow(NullPointerException.class);

        Assert.assertEquals(eventController.deleteEvent(eventKey), new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));


    }




}
