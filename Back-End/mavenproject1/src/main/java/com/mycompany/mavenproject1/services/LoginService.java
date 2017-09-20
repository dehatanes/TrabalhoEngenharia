/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services;

import com.mycompany.mavenproject1.integrations.model.User;
import com.mycompany.mavenproject1.integrations.repository.UserRepository;
import com.mycompany.mavenproject1.services.common.GenericResponse;
import com.mycompany.mavenproject1.services.user.transform.UserTransform;
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
@RequestMapping("/login")
public class LoginService {

    @Autowired
    UserTransform transform;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericResponse> login(@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password) {

        GenericResponse response = new GenericResponse();
        try {
            User user = userRepository.findByEmailUser(email);
            System.out.println(user);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if (!user.getUserPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            response.addContentItem(transform.entityToBean(user));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
//            log.error(logPrefix + "-[HTTP STATUS][" + HttpStatus.INTERNAL_SERVER_ERROR + "]-[ERROR][" + ex.getMessage() + "]", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
