package com.edson.starwars.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edson.starwars.model.Planeta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwapiServiceIntegrationTest {
	 	 
	 @Autowired
	 private SwapiService swapiService;
	
	 @Test
	 public void deveBuscarQuantidadeDeAparicoesComSucesso() throws Exception {
		 Planeta genosis = new Planeta("Geonosis", "temperate, arid", "rock, desert, mountain, barren");
		 int quantidadeAparicoesEmFilmes = swapiService.buscarQuantidadeAparicoesEmFilmes(genosis);
		 assertEquals(1, quantidadeAparicoesEmFilmes);
	 }
	 
	 @Test
	 public void deveRetornarZeroParaPlanetaInexistente() throws Exception {
		 Planeta genosis = new Planeta("nome", "clima", "terreno");
		 int quantidadeAparicoesEmFilmes = swapiService.buscarQuantidadeAparicoesEmFilmes(genosis);
		 assertEquals(0, quantidadeAparicoesEmFilmes);
	 }
	
}
