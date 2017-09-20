/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.repository;

import com.mycompany.mavenproject1.integrations.model.UserEvent;
import com.mycompany.mavenproject1.integrations.model.UserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danie
 */
public interface UserEventRepository extends JpaRepository<UserEvent, UserEventPK> {

}
