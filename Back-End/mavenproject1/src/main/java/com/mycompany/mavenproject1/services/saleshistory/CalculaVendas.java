/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.saleshistory;

import com.mycompany.mavenproject1.integrations.model.Despesas;
import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.model.Lote;
import com.mycompany.mavenproject1.integrations.model.SalesHistory;
import com.mycompany.mavenproject1.integrations.repository.EventRepository;
import com.mycompany.mavenproject1.integrations.repository.LoteRepository;
import com.mycompany.mavenproject1.integrations.repository.SalesHistoryRepository;
import java.util.List;
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
 * @author Daniel
 */
@Controller
@RequestMapping("/sales-history")
public class CalculaVendas {

    private final Logger log = Logger.getLogger(SalesHistoryService.class.getName());

    @Autowired
    SalesHistoryRepository salesRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LoteRepository loteRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/calcula-vendas", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Double> calculaDespesa(@RequestParam(value = "idEvent", required = true) Integer idEvent
    ) {
        Event event = eventRepository.findOne(idEvent);
        List<Lote> lotes = loteRepository.findByIdEvent(event);

        Double total = 0.0;
        for (Lote lote : lotes) {
            total = total + (lote.getQtdIngressosVendidos() * lote.getValorIngresso());
            System.out.println("total: " + total);
        }

        return ResponseEntity.status(HttpStatus.OK).body(total);
    }

}
