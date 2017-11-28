/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.saleshistory;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.model.Lote;
import com.mycompany.mavenproject1.integrations.model.SalesHistory;
import com.mycompany.mavenproject1.integrations.repository.LoteRepository;
import com.mycompany.mavenproject1.integrations.repository.SalesHistoryRepository;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.saleshistory.bean.SalesHistoryBean;
import com.mycompany.mavenproject1.services.saleshistory.transform.SalesHistoryTransform;
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
 * @author Daniel
 */
@Controller
@RequestMapping("/sales-history")
public class SalesHistoryService {

    private final Logger log = Logger.getLogger(SalesHistoryService.class.getName());

    @Autowired
    SalesHistoryRepository salesHistoryRepository;

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    SalesHistoryTransform transform;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findall() {

        try {
            log.info("-[FIND-ALL]");
            List<GenericServiceBean> bean = transform.listEntityToListBean(salesHistoryRepository.findAll());

            log.info("-[SUCESS]-[HTTP STATUS][200]");
            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> create(@RequestParam(value = "idEvent", required = true) Integer idEvent,
            @RequestParam(value = "idLote", required = true) Integer idLote,
            @RequestParam(value = "saleDate", required = true) Date saleDate,
            @RequestParam(value = "qtdSold", required = true) Integer qtdSold
    ) {
        Event event = new Event(idEvent);
        Lote lote = loteRepository.findOne(idLote);
        SalesHistory salesHistory = new SalesHistory();
        salesHistory.setQtdSold(qtdSold);
        salesHistory.setSaleDate(saleDate);
        salesHistory.setIdEvent(event);
        salesHistory.setIdLote(lote);

        try {
            salesHistoryRepository.save(salesHistory);

            lote.setQtdIngressosVendidos(lote.getQtdIngressosVendidos() + salesHistory.getQtdSold());
            loteRepository.save(lote);
        } catch (Exception ex) {
            System.out.println(ex);
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> update(
            @RequestParam(value = "idSalesHistory", required = true) Integer idSalesHistory,
            @RequestParam(value = "idEvent", required = false) Integer idEvent,
            @RequestParam(value = "idLote", required = false) Integer idLote,
            @RequestParam(value = "saleDate", required = false) Date saleDate,
            @RequestParam(value = "qtdSold", required = false) Integer qtdSold) {

        SalesHistory salesHistory = salesHistoryRepository.findOne(idSalesHistory);
        if (salesHistory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (idEvent != null) {
            Event event = new Event(idEvent);
            salesHistory.setIdEvent(event);
        }
        if (idLote != null) {
            Lote lote = new Lote(idLote);
            salesHistory.setIdLote(lote);
        }
        if (saleDate != null) {
            salesHistory.setSaleDate(saleDate);
        }
        if (qtdSold != null) {
            salesHistory.setQtdSold(qtdSold);
        }

        try {
            salesHistoryRepository.save(salesHistory);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> delete(
            @RequestParam(value = "idSalesHistory", required = true) Integer idSalesHistory
    ) {

        SalesHistory salesHistory = salesHistoryRepository.findOne(idSalesHistory);
        if (salesHistory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            salesHistoryRepository.delete(salesHistory);
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
            SalesHistoryBean bean = (SalesHistoryBean) transform.entityToBean(salesHistoryRepository.findOne(id));

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/find-by-event", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findByEvent(@RequestParam(value = "idEvent", required = true) Integer idEvent) {

        try {
            Event event = new Event(idEvent);
            List<SalesHistory> list = salesHistoryRepository.findByIdEvent(event);
            List<GenericServiceBean> bean = transform.listEntityToListBean(list);

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/find-by-lote", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findByLote(@RequestParam(value = "idLote", required = true) Integer idLote) {

        try {
            Lote lote = new Lote(idLote);
            List<SalesHistory> list = salesHistoryRepository.findByIdLote(lote);
            List<GenericServiceBean> bean = transform.listEntityToListBean(list);

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
