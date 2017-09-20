/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.events;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.repository.EventRepository;
import com.mycompany.mavenproject1.services.common.GenericResponse;
import com.mycompany.mavenproject1.services.events.transform.EventsTransform;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author danie
 */
@Controller
@RequestMapping("/events")
public class EventsService {

    private final Logger log = Logger.getLogger(EventsService.class.getName());

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventsTransform transform;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> findall() {

        GenericResponse response = new GenericResponse();
        try {
            log.info("-[FIND-ALL]");
            List<Event> list = eventRepository.findAll();
            response.setContent(transform.listEntityToListBean(list));
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        log.info("-[SUCESS]-[HTTP STATUS][200]");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> create(@RequestParam(value = "nameEvent", required = true) String nameEvent,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "dateEvent", required = true) Date dateEvent,
            @RequestParam(value = "imgEvent", required = false) String imgEvent) {

        Event event = new Event();
        event.setNameEvent(nameEvent);
        event.setDescEvent(description);
        event.setDateEvent(dateEvent);
        if (imgEvent != null && !imgEvent.isEmpty()) {
            event.setImageEvent(imgEvent);
        }
        try {
            eventRepository.save(event);
        } catch (Exception ex) {
            System.out.println(ex);
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> update(
            @RequestParam(value = "idEvent", required = true) Integer idEvent,
            @RequestParam(value = "nameEvent", required = false) String nameEvent,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "dateEvent", required = false) Date dateEvent,
            @RequestParam(value = "imgEvent", required = false) String imgEvent) {

        Event event = eventRepository.findOne(idEvent);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (nameEvent != null && !nameEvent.isEmpty()) {
            event.setNameEvent(nameEvent);
        }
        if (description != null && !description.isEmpty()) {
            event.setDescEvent(description);
        }
        if (dateEvent != null) {
            event.setDateEvent(dateEvent);
        }

        if (imgEvent != null && !imgEvent.isEmpty()) {
            event.setImageEvent(imgEvent);
        }
        try {
            eventRepository.save(event);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @RequestParam(value = "idEvent", required = true) Integer idEvent
    ) {

        Event event = eventRepository.findOne(idEvent);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            eventRepository.delete(event);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
