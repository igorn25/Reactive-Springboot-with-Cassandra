package br.com.teste.controller;

import br.com.teste.model.Event;
import br.com.teste.model.EventKey;
import br.com.teste.service.EventServiceImpl;
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

import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
@WebFluxTest
public class EventControllerTest {

    @InjectMocks
    private EventController eventController;
    @Mock
    private EventServiceImpl eventService;

    private static final EventKey eventKey1 = new EventKey("Key1","1");
    private static final EventKey eventKey2 = new EventKey("Key2","2");

    private static final Event event1 = new Event(eventKey1,UUID.randomUUID(),"Domain 1");
    private static final Event event2 = new Event(eventKey2,UUID.randomUUID(),"Domain 2");



    @Test
    public void findAllEvents(){

        Flux<Event> fluxEvent = Flux.just(event1,event2);

        Mockito.when(this.eventService.findAll()).thenReturn(fluxEvent);

        Assert.assertEquals(eventController.findAllEvents(),fluxEvent);
    }

    @Test
    public void findById(){
        Mono<Event> monoEvent = Mono.just(event1);
        Mockito.when(this.eventService.findById(eventKey1)).thenReturn(monoEvent);

        Assert.assertEquals(eventController.findById(eventKey1),monoEvent);
    }

    @Test public void deleteEventException(){

        Mockito.when(this.eventController.deleteEvent(eventKey1)).thenThrow(NullPointerException.class);

        Assert.assertEquals(eventController.deleteEvent(eventKey1), new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
    }

    @Test public void deleteEvent(){
        eventService.deleteEvent(eventKey1);
        Mockito.verify(eventService, times(1)).deleteEvent(eventKey1);


    }
    @Test public void createEvent(){
        Mono<Event> monoEvent = Mono.just(event1);

        Mockito.when(this.eventService.createEvent(event1).thenReturn(monoEvent));

        Assert.assertEquals(eventController.createEvent(event1),monoEvent);

    }



}
