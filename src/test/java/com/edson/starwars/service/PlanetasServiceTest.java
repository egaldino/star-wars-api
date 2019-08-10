package com.edson.starwars.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edson.starwars.model.Planeta;
import com.edson.starwars.repository.PlanetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetasServiceTest {
	 	 
	 @InjectMocks
	 private PlanetasService planetasService;
	 
	 @Mock
	 private PlanetaRepository planetaRepository;
	 
	 @Mock
	 private SwapiService swapService;
	 
	 
	 private Planeta geonosis;
	 private Planeta kamino;
	 private Planeta coruscant;

	 @Before
	 public void setup() {
		 MockitoAnnotations.initMocks(this);
		 
		 geonosis = new Planeta("Geonosis", "temperate, arid", "rock, desert, mountain, barren", 1);
		 kamino = new Planeta("Kamino", "temperate", "ocean", 1);
		 coruscant = new Planeta("Coruscant", "temperate", "cityscape, mountains", 4);
	 }
	 
	 @Test
	 public void deveAdicionarPlanetaComSucesso() throws Exception {
		 Planeta geonosisSemQuantidadeAparicoesEId = new Planeta("Geonosis", "temperate, arid", "rock, desert, mountain, barren");
		 
		 Planeta geonosisRetorno = new Planeta("111", "Geonosis", "temperate, arid", "rock, desert, mountain, barren", 1);
		 
		 Mockito.when(swapService.buscarQuantidadeAparicoesEmFilmes(geonosis)).thenReturn(1);
		 Mockito.when(planetaRepository.save(geonosisSemQuantidadeAparicoesEId)).thenReturn(geonosisRetorno);

		 Planeta planetaAdicionado = planetasService.adicionar(geonosisSemQuantidadeAparicoesEId);
		 assertEquals(geonosisRetorno, planetaAdicionado);
	 }
	 
	 @Test
	 public void deveListarPlanetasComSucesso() throws Exception {
		Mockito.when(planetaRepository.findAll()).thenReturn(Arrays.asList(geonosis, kamino, coruscant));

		List<Planeta> listaPlanetas = planetasService.listar();
		assertEquals(geonosis, listaPlanetas.get(0));
		assertEquals(kamino, listaPlanetas.get(1));
		assertEquals(coruscant, listaPlanetas.get(2));
	 }
	 
	 @Test
	 public void deveBuscarPlanetaPorIdComSucesso() throws Exception {
		Mockito.when(planetaRepository.findById("1")).thenReturn(Optional.of(geonosis));
		
		Optional<Planeta> planetaRetornado = planetasService.buscarPorId("1");
		assertTrue(planetaRetornado.isPresent());
		assertEquals(geonosis, planetaRetornado.get());
	 }
	
	 @Test
	 public void deveRetornarNotFoundSePlanetaNaoExisteAoBuscarPorId() throws Exception {
		Mockito.when(planetaRepository.findById("1")).thenReturn(Optional.empty());
			
		Optional<Planeta> planetaRetornado = planetasService.buscarPorId("1");
		assertFalse(planetaRetornado.isPresent());
	 }
	 
	 @Test
	 public void deveBuscarPlanetasPorNomeComSucesso() throws Exception {
		 Mockito.when(planetaRepository.findByNomeContaining("Coruscant")).thenReturn(Arrays.asList(coruscant));

		 List<Planeta> listaPlanetas = planetasService.listarPorNome("Coruscant");
		 assertEquals(coruscant, listaPlanetas.get(0));
	 }
	 
	 @Test
	 public void deveRemoverPlanetaComSucesso() throws Exception {
		 Mockito.doNothing().when(planetaRepository).delete(Mockito.any());
		 try {
			 planetasService.remover("1");
		 }catch (Exception e) {
			fail(e.getMessage());
		}
	 }
}
