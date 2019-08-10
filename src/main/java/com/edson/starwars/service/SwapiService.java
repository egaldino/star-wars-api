package com.edson.starwars.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edson.starwars.dto.PlanetaSwapiDTO;
import com.edson.starwars.dto.ResultadoSwapiDTO;
import com.edson.starwars.exception.SwapiException;
import com.edson.starwars.model.Planeta;

@Service
public class SwapiService {

	@Value("${swapi.uri}")
	private String swapiUri;
	
	@Value("${swapi.user.agent}")
	private String swapiUserAgent;

	
	public Integer buscarQuantidadeAparicoesEmFilmes(Planeta planeta) throws SwapiException {
		try {
			RestTemplate swapRestTemplate = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
			headers.add("user-agent", swapiUserAgent);
			HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);
	
			ResponseEntity<ResultadoSwapiDTO> response =  swapRestTemplate.exchange(swapiUri + "?search=" + planeta.getNome(), HttpMethod.GET, httpEntity, ResultadoSwapiDTO.class);
			ResultadoSwapiDTO resultadoSwapDTO = response.getBody();
			PlanetaSwapiDTO planetaSwapDTO  = resultadoSwapDTO != null && resultadoSwapDTO.getResults() != null &&  resultadoSwapDTO.getResults().size() > 0 ? 
											 resultadoSwapDTO.getResults().get(0) : null;
			return planetaSwapDTO != null && planetaSwapDTO.getFilms() != null ? planetaSwapDTO.getFilms().size() : 0;
		} catch (Exception e) {
			throw new SwapiException("Houve erro ao tentar acessar dados em SWAPI", e);
		}
	}

}
