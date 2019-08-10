package com.edson.starwars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edson.starwars.exception.SwapiException;
import com.edson.starwars.model.Planeta;
import com.edson.starwars.repository.PlanetaRepository;

@Service
public class PlanetasService {

	private PlanetaRepository planetaRepository;
	private SwapiService swapService;

	public PlanetasService(PlanetaRepository planetaRepository, SwapiService swapService) {
		this.planetaRepository = planetaRepository;
		this.swapService = swapService;
	}

	public Planeta adicionar(Planeta planeta) throws SwapiException {
		Integer aparicoesEmFilmes = swapService.buscarQuantidadeAparicoesEmFilmes(planeta);
    	planeta.setAparicoesEmFilmes(aparicoesEmFilmes);
    	return planetaRepository.save(planeta);
    }
	
    public List<Planeta> listar() {
        return planetaRepository.findAll();
    }
    
    public Optional<Planeta> buscarPorId(String id) {
        return planetaRepository.findById(id);
    }
    
    public List<Planeta> listarPorNome(String nome) {
    	return planetaRepository.findByNomeContaining(nome);
    }
    
	public void remover(String id) {
    	planetaRepository.deleteById(id);
    }
   	
}
