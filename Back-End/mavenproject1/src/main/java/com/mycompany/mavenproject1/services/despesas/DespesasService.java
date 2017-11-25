/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.despesas;

import com.mycompany.mavenproject1.integrations.model.Despesas;
import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.repository.DespesasRepository;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.despesas.bean.DespesaBean;
import com.mycompany.mavenproject1.services.despesas.transform.DespesaTransform;
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
 * @author danie
 */
@Controller
@RequestMapping("/despesas")
public class DespesasService {

    private final Logger log = Logger.getLogger(DespesasService.class.getName());

    @Autowired
    DespesasRepository despesasRepository;

    @Autowired
    DespesaTransform transform;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findall() {

        try {
            log.info("-[FIND-ALL]");
            List<GenericServiceBean> bean = transform.listEntityToListBean(despesasRepository.findAll());

            log.info("-[SUCESS]-[HTTP STATUS][200]");
            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> create(@RequestParam(value = "nomeItem", required = true) String nomeItem,
            @RequestParam(value = "descItem", required = true) String descItem,
            @RequestParam(value = "qtd", required = true) Integer qtd,
            @RequestParam(value = "valorUnitario", required = true) Double valorUnitario,
            @RequestParam(value = "idEvent", required = true) Integer idEvent
    ) {
        Event event = new Event(idEvent);
        Despesas despesa = new Despesas();
        despesa.setNomeItem(nomeItem);
        despesa.setDescItem(descItem);
        despesa.setQtd(qtd);
        despesa.setStatus(0);
        despesa.setIdEvent(event);
        try {
            despesasRepository.save(despesa);
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
            @RequestParam(value = "idDespesas", required = true) Integer idDespesas,
            @RequestParam(value = "nomeItem", required = false) String nomeItem,
            @RequestParam(value = "descItem", required = false) String descItem,
            @RequestParam(value = "qtd", required = false) Integer qtd,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "valorUnitario", required = false) Double valorUnitario,
            @RequestParam(value = "idEvent", required = false) Integer idEvent) {

        Despesas despesa = despesasRepository.findOne(idDespesas);
        if (despesa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (nomeItem != null && !nomeItem.isEmpty()) {
            despesa.setNomeItem(nomeItem);
        }
        if (descItem != null && !descItem.isEmpty()) {
            despesa.setDescItem(descItem);
        }
        if (qtd != null) {
            despesa.setQtd(qtd);
        }
        if (status != null) {
            despesa.setStatus(status);
        }
        if (valorUnitario != null) {
            despesa.setValorUnitario(valorUnitario);
        }
        if (idEvent != null) {
            Event event = new Event(idEvent);
            despesa.setIdEvent(event);
        }

        try {
            despesasRepository.save(despesa);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> delete(
            @RequestParam(value = "idDespesas", required = true) Integer idDespesas
    ) {

        Despesas despesa = despesasRepository.findOne(idDespesas);
        if (despesa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            despesasRepository.delete(despesa);
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
            DespesaBean bean = (DespesaBean) transform.entityToBean(despesasRepository.findOne(id));

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
            List<Despesas> list = despesasRepository.findByIdEvent(event);
            List<GenericServiceBean> bean = transform.listEntityToListBean(list);

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
