/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.user;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.model.User;
import com.mycompany.mavenproject1.integrations.repository.UserRepository;
import com.mycompany.mavenproject1.services.common.GenericResponse;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.events.beans.EventsBean;
import com.mycompany.mavenproject1.services.events.transform.EventsTransform;
import com.mycompany.mavenproject1.services.user.beans.UserBean;
import com.mycompany.mavenproject1.services.user.transform.UserTransform;
import java.util.List;
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
@RequestMapping("/users")
public class UsersService {

    @Autowired
    UserTransform transform;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventsTransform eventsTransform;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findall() {

        GenericResponse response = new GenericResponse();
        try {
//            log.debug(logPrefix + "-[FIND-ALL]");

            List<GenericServiceBean> bean = transform.listEntityToListBean(userRepository.findAll());

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {
//            log.error(logPrefix + "-[HTTP STATUS][" + HttpStatus.INTERNAL_SERVER_ERROR + "]-[ERROR][" + ex.getMessage() + "]", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
//        log.debug(logPrefix + "-[SUCESS]-[HTTP STATUS][200]");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> create(@RequestParam(value = "name", required = true) String nameUser,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "imgUser", required = false) String imgUser,
            @RequestParam(value = "profile", required = true) String profile
    ) {

        User user = new User();
        user.setNameUser(nameUser);
        user.setEmailUser(email);
        user.setUserPassword(password);
        if (imgUser != null && !imgUser.isEmpty()) {
            user.setImageUser(imgUser);
        }
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> update(
            @RequestParam(value = "idUser", required = true) Integer idUser,
            @RequestParam(value = "name", required = true) String nameUser,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "imgUser", required = false) String imgUser
    ) {
        User user = userRepository.findOne(idUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (nameUser
                != null && !nameUser.isEmpty()) {
            user.setNameUser(nameUser);
        }

        if (imgUser
                != null && !imgUser.isEmpty()) {
            user.setImageUser(imgUser);
        }

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> delete(
            @RequestParam(value = "idUser", required = true) Integer idUser
    ) {

        User user = userRepository.findOne(idUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            userRepository.delete(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/find-events-by-user", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findOne(@RequestParam(value = "idUser", required = true) Integer idUser) {

        GenericResponse response = new GenericResponse();
        try {
            User user = userRepository.findOne(idUser);
            List<Event> list = user.getEventList();
            
            List<GenericServiceBean> listBean = eventsTransform.listEntityToListBean(list);

            return ResponseEntity.status(HttpStatus.OK).body(listBean);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
