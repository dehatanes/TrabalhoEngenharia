/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.despesas;

import com.mycompany.mavenproject1.integrations.model.Despesas;
import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.repository.DespesasRepository;
import com.mycompany.mavenproject1.integrations.repository.EventRepository;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.saleshistory.SalesHistoryService;
import java.math.BigDecimal;
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
@RequestMapping("/despesas")
public class CalculaFinancas {

    private final Logger log = Logger.getLogger(SalesHistoryService.class.getName());

    @Autowired
    DespesasRepository despesasRepository;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/calcula-financas", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Double> calcula(@RequestParam(value = "idEvent", required = true) Integer idEvent
    ) {
        Event event = eventRepository.findOne(idEvent);
        Double totalDespesas = calculaDespesas(event);
        Double total = event.getEventProfit() - totalDespesas;

        return ResponseEntity.status(HttpStatus.OK).body(total);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/calcula-despesas", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Double> calculaDespesa(@RequestParam(value = "idEvent", required = true) Integer idEvent
    ) {
        Event event = eventRepository.findOne(idEvent);
        Double totalDespesas = calculaDespesas(event);

        return ResponseEntity.status(HttpStatus.OK).body(totalDespesas);
    }

    public Double calculaDespesas(Event event) {

        List<Despesas> despesas = despesasRepository.findByIdEvent(event);

        Double totalDespesas = 0.0;
        for (Despesas despesa : despesas) {
            totalDespesas = totalDespesas + (despesa.getQtd() * despesa.getValorUnitario());
            System.out.println("totalDespesas: " + totalDespesas);
        }
        return totalDespesas;
    }
}
