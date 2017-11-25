/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.repository;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.model.Lote;
import com.mycompany.mavenproject1.integrations.model.SalesHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Daniel
 */
public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Integer> {

    public List<SalesHistory> findByIdEvent(Event event);

    public List<SalesHistory> findByIdLote(Lote lote);
}
