/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.repository;

import com.mycompany.mavenproject1.integrations.model.Despesas;
import com.mycompany.mavenproject1.integrations.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Daniel
 */
public interface DespesasRepository extends JpaRepository<Despesas, Integer> {

    List<Despesas> findByIdEvent(Event event);

}
