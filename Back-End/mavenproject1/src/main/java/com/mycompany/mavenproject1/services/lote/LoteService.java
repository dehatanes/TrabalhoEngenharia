/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.lote;

import com.mycompany.mavenproject1.integrations.model.Lote;
import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.integrations.repository.LoteRepository;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.lote.bean.LoteBean;
import com.mycompany.mavenproject1.services.lote.transform.LoteTransform;
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
@RequestMapping("/lote")
public class LoteService {

    private final Logger log = Logger.getLogger(LoteService.class.getName());

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    LoteTransform transform;

    @RequestMapping(method = RequestMethod.GET, path = "/find-all", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<GenericServiceBean>> findall() {

        try {
            log.info("-[FIND-ALL]");
            List<GenericServiceBean> bean = transform.listEntityToListBean(loteRepository.findAll());

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
            @RequestParam(value = "nomeLote", required = true) String nomeLote,
            @RequestParam(value = "qtdMaxIngressos", required = true) Integer qtdMaxIngressos,
            @RequestParam(value = "valorIngresso", required = true) Integer valorIngresso
    ) {
        Event event = new Event(idEvent);
        Lote lote = new Lote();
        lote.setNomeLote(nomeLote);
        lote.setQtdMaxIngressos(qtdMaxIngressos);
        lote.setQtdIngressosVendidos(0);
        lote.setValorIngresso(valorIngresso);
        lote.setIdEvent(event);
        try {
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
            @RequestParam(value = "idLote", required = true) Integer idLote,
            @RequestParam(value = "idEvent", required = false) Integer idEvent,
            @RequestParam(value = "nomeLote", required = false) String nomeLote,
            @RequestParam(value = "qtdMaxIngressos", required = false) Integer qtdMaxIngressos,
            @RequestParam(value = "qtdIngressosVendidos", required = false) Integer qtdIngressosVendidos,
            @RequestParam(value = "valorIngresso", required = false) Integer valorIngresso) {

        Lote lote = loteRepository.findOne(idLote);
        if (lote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (idEvent != null) {
            Event event = new Event(idEvent);
            lote.setIdEvent(event);
        }
        if (nomeLote != null && !nomeLote.isEmpty()) {
            lote.setNomeLote(nomeLote);
        }
        if (qtdMaxIngressos != null) {
            lote.setQtdMaxIngressos(qtdMaxIngressos);
        }
        if (qtdIngressosVendidos != null) {
            lote.setQtdIngressosVendidos(qtdIngressosVendidos);
        }
        if (valorIngresso != null) {
            lote.setValorIngresso(valorIngresso);
        }

        try {
            loteRepository.save(lote);
        } catch (Exception ex) {
            log.log(Level.FINEST, "-[HTTP STATUS][{0}]-[ERROR][{1}]", new Object[]{HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()});
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GenericServiceBean> delete(
            @RequestParam(value = "idLote", required = true) Integer idLote
    ) {

        Lote lote = loteRepository.findOne(idLote);
        if (lote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            loteRepository.delete(lote);
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
            LoteBean bean = (LoteBean) transform.entityToBean(loteRepository.findOne(id));

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
            List<Lote> list = loteRepository.findByIdEvent(event);
            List<GenericServiceBean> bean = transform.listEntityToListBean(list);

            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
