/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.user;

import com.mycompany.mavenproject1.integrations.model.User;
import com.mycompany.mavenproject1.integrations.repository.UserRepository;
import com.mycompany.mavenproject1.services.common.GenericResponse;
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

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> findall() {

        GenericResponse response = new GenericResponse();
        try {
//            log.debug(logPrefix + "-[FIND-ALL]");
            List<User> list = userRepository.findAll();
            response.setContent(transform.listEntityToListBean(list));
        } catch (Exception ex) {
//            log.error(logPrefix + "-[HTTP STATUS][" + HttpStatus.INTERNAL_SERVER_ERROR + "]-[ERROR][" + ex.getMessage() + "]", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
//        log.debug(logPrefix + "-[SUCESS]-[HTTP STATUS][200]");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> create(@RequestParam(value = "name", required = true) String nameUser,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "imgUser", required = false) String imgUser,
            @RequestParam(value = "profile", required = true) String profile
    ) {

        User user = new User();
        user.setNameUser(nameUser);
        user.setEmailUser(email);
        user.setUserPassword(password);
        user.setUserProfile(profile);
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
    ResponseEntity<GenericResponse> update(
            @RequestParam(value = "idUser", required = true) Integer idUser,
            @RequestParam(value = "name", required = true) String nameUser,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "imgUser", required = false) String imgUser,
            @RequestParam(value = "profile", required = true) String profile
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
    ResponseEntity<GenericResponse> delete(
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

}
