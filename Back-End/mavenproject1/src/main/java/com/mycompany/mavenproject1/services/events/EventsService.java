/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.events;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.model.User;
import com.mycompany.mavenproject1.integrations.model.UserEvent;
import com.mycompany.mavenproject1.integrations.model.UserEventPK;
import com.mycompany.mavenproject1.integrations.repository.EventRepository;
import com.mycompany.mavenproject1.integrations.repository.UserEventRepository;
import com.mycompany.mavenproject1.integrations.repository.UserRepository;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.events.beans.EventsBean;
import com.mycompany.mavenproject1.services.events.transform.EventsTransform;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Autowired
    UserRepository repository;

    @Autowired
    UserEventRepository userEventRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")

    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findall() {

//        GenericResponse response = new GenericResponse();
        try {
            log.info("-[FIND-ALL]");
            List<GenericServiceBean> bean = transform.listEntityToListBean(eventRepository.findAll());

            log.info("-[SUCESS]-[HTTP STATUS][200]");
            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> create(@RequestParam(value = "nameEvent", required = true) String nameEvent,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "dateEvent", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEvent,
            @RequestParam(value = "imgEvent", required = false) String imgEvent,
            @RequestParam(value = "capacity", required = false) String capacity,
            @RequestParam(value = "profit", required = false) Integer profit
    ) {

        Event event = new Event();
        event.setNameEvent(nameEvent);
        event.setDescEvent(description);
        event.setDateEvent(dateEvent);
        if (imgEvent != null && !imgEvent.isEmpty()) {
            event.setImageEvent(imgEvent);
        }
        event.setCapacityEvent(capacity);
        event.setEventProfit(profit);
        try {
            eventRepository.save(event);
            EventsBean bean = (EventsBean) transform.entityToBean(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(bean);
        } catch (Exception ex) {
            System.out.println(ex);
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/update", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> update(
            @RequestParam(value = "idEvent", required = true) Integer idEvent,
            @RequestParam(value = "nameEvent", required = false) String nameEvent,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "dateEvent", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEvent,
            @RequestParam(value = "imgEvent", required = false) String imgEvent,
            @RequestParam(value = "capacity", required = false) String capacity,
            @RequestParam(value = "profit", required = false) Integer profit) {

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
        if (capacity != null) {
            event.setCapacityEvent(capacity);
        }
        if (profit != null) {
            event.setEventProfit(profit);
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
    ResponseEntity<GenericServiceBean> delete(
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

    @RequestMapping(method = RequestMethod.GET, path = "/find-by-id", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> findOne(@RequestParam(value = "id", required = true) Integer id) {

        try {
            //            log.debug(logPrefix + "-[FIND-ALL]");

            EventsBean bean = (EventsBean) transform.entityToBeanWithUsers(eventRepository.findOne(id));

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {
            //            log.error(logPrefix + "-[HTTP STATUS][" + HttpStatus.INTERNAL_SERVER_ERROR + "]-[ERROR][" + ex.getMessage() + "]", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //        log.debug(logPrefix + "-[SUCESS]-[HTTP STATUS][200]");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/set-user-event", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> setUserEvent(@RequestParam(value = "idUser", required = true) Integer idUser,
            @RequestParam(value = "idEvent", required = true) Integer idEvent,
            @RequestParam(value = "permission", required = true) String userPermission
    ) {

        try {
            //            log.debug(logPrefix + "-[FIND-ALL]");

//            EventsBean bean = (EventsBean) transform.entityToBeanWithUsers(eventRepository.findOne(id));
            Event event = eventRepository.findOne(idEvent);
            User user = repository.findOne(idUser);

            UserEvent userEvent = new UserEvent();
            UserEventPK pk = new UserEventPK(idEvent, idUser);
            userEvent.setEvent(event);
            userEvent.setUser(user);
            userEvent.setUserEventPK(pk);
            userEvent.setUserPermission(userPermission);

            userEventRepository.save(userEvent);

            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception ex) {
            //            log.error(logPrefix + "-[HTTP STATUS][" + HttpStatus.INTERNAL_SERVER_ERROR + "]-[ERROR][" + ex.getMessage() + "]", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //        log.debug(logPrefix + "-[SUCESS]-[HTTP STATUS][200]");
    }
}
