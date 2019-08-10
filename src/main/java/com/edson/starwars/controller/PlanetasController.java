package com.edson.starwars.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edson.starwars.model.Planeta;
import com.edson.starwars.service.PlanetasService;

@RestController
@RequestMapping("${api.uri}")
public class PlanetasController {

	Logger logger = LoggerFactory.getLogger(PlanetasController.class);
	
	@Autowired
	private PlanetasService planetasService;
	
	@PostMapping(path="/planeta")
    public ResponseEntity<Planeta> adicionar(@Valid @RequestBody Planeta planeta) {
    	try {
    		Planeta planetaAdicionado = planetasService.adicionar(planeta);
    		return new ResponseEntity<Planeta>(planetaAdicionado, HttpStatus.CREATED);
    	} catch (Exception e) {
    		logger.error("Exception ao adicionar planeta", e);
    		return new ResponseEntity<Planeta>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
    }
	
	@GetMapping("/planetas")
    public ResponseEntity<List<Planeta>> listar() {
        try {
    		List<Planeta> listaPlanetas = planetasService.listar();
    		return new ResponseEntity<List<Planeta>>(listaPlanetas, HttpStatus.OK);
    	} catch (Exception e) {
    		logger.error("Exception ao listar planetas", e);
    		return new ResponseEntity<List<Planeta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	@GetMapping("/planetas/{id}")
    public ResponseEntity<Planeta> buscarPorId(@PathVariable(value="id", required = true) String id) {
    	try {
    		Optional<Planeta> planeta = planetasService.buscarPorId(id);
    		if(planeta.isPresent()) {
    			return new ResponseEntity<Planeta>(planeta.get(), HttpStatus.OK);
    		} 
    		return new ResponseEntity<Planeta>(HttpStatus.NOT_FOUND);
     	} catch (Exception e) {
     		logger.error("Exception ao buscar planeta", e);
     		return new ResponseEntity<Planeta>(HttpStatus.INTERNAL_SERVER_ERROR);
 		}
    }
    
	@GetMapping("/planetasPorNome/{nome}")
    public ResponseEntity<List<Planeta>> listarPorNome(@PathVariable(value="nome", required = true) String nome) {
    	try {
     		List<Planeta> listaPorNome = planetasService.listarPorNome(nome);
     		return new ResponseEntity<List<Planeta>>(listaPorNome , HttpStatus.OK);
     	} catch (Exception e) {
     		logger.error("Exception ao listar planetas", e);
     		return new ResponseEntity<List<Planeta>>(HttpStatus.INTERNAL_SERVER_ERROR);
 		}
    }
    
    @DeleteMapping(path="planeta/{id}")
	public ResponseEntity<Void> remover(@PathVariable(name = "id", required = true) String id) {
    	try {
     		planetasService.remover(id);
     	} catch (Exception e) {
     		logger.error("Exception ao tentar remover planeta", e);
     		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
 		}
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
}
