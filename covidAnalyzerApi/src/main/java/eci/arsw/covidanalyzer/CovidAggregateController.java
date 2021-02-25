package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.persistence.CovidPersistenceException;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidAggregateController {
	
	@Autowired
    ICovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addTruePositiveResult(Result result) {
        try {
	        //registrar dato
        	covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible agregar resultadp.",HttpStatus.FORBIDDEN);            
	    }
    }
    
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addTrueNegativeResult(Result result) {
        //TODO
        try {
			covidAggregateService.aggregateResult(result, ResultType.TRUE_NEGATIVE);
			 return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible agregar resultadp.",HttpStatus.FORBIDDEN);            
	    }
    }
    
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addFalsePositiveResult(Result result) {
        //TODO
        try {
			covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE);
			 return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible agregar resultadp.",HttpStatus.FORBIDDEN);            
	    }
    }
    
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addFalseNegativeResult(Result result) {
        //TODO
        try {
			covidAggregateService.aggregateResult(result, ResultType.FALSE_NEGATIVE);
			 return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible agregar resultadp.",HttpStatus.FORBIDDEN);            
	    }
    }

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getTruePositiveResult() {
        try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_POSITIVE),HttpStatus.ACCEPTED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Resultados no encontrados.",HttpStatus.NOT_FOUND);
	    }
    }
    
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getTrueNegativeResult() {

        try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_NEGATIVE),HttpStatus.ACCEPTED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Resultados no encontrados.",HttpStatus.NOT_FOUND);
	    }
    }
    
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getFalsePositiveResult() {

        try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_POSITIVE),HttpStatus.ACCEPTED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Resultados no encontrados.",HttpStatus.NOT_FOUND);
	    }
    }
    
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getFalseNegativeResult() {
        try {
	        //obtener datos que se enviarán a través del API
	        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_NEGATIVE),HttpStatus.ACCEPTED);
	    } catch (CovidPersistenceException ex) {
	        Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Resultados no encontrados.",HttpStatus.NOT_FOUND);
	    }
    }

    //TODO: Implemente el método.

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> savePersonaWithMultipleTests() {
        //TODO
        try {
			covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
		} catch (CovidPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
}