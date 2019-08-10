package com.edson.starwars.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.edson.starwars.helper.TestHelper;
import com.edson.starwars.model.Planeta;
import com.edson.starwars.service.PlanetasService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetasControllerTest {

	 private MockMvc mockMvc;
	 	 
	 @InjectMocks
	 private PlanetasController planetasController;
	 
	 @Mock
	 private PlanetasService planetasService;
	 
	 @Value("${api.uri}")
	 private String apiUrl;
	 
	 private Planeta geonosis;
	 private Planeta kamino;
	 private Planeta coruscant;

	 @Before
	 public void setup() {
		 MockitoAnnotations.initMocks(this);
		 mockMvc = MockMvcBuilders.standaloneSetup(planetasController).addPlaceholderValue("api.uri", apiUrl).build();
		 
		 geonosis = new Planeta("Geonosis", "temperate, arid", "rock, desert, mountain, barren", 1);
		 kamino = new Planeta("Kamino", "temperate", "ocean", 1);
		 coruscant = new Planeta("Coruscant", "temperate", "cityscape, mountains", 4);
	 }
	 
	 @Test
	 public void deveAdicionarPlanetaComSucesso() throws Exception {
		 Mockito.when(planetasService.adicionar(geonosis)).thenReturn(geonosis);

		 MvcResult result = this.mockMvc.perform(post(apiUrl + "/planeta")
				 	.content(TestHelper.mapToJson(geonosis))
				 	.contentType(MediaType.APPLICATION_JSON_UTF8)
				 	.accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		Planeta planetaAdicionado = TestHelper.mapFromJson(response.getContentAsString(), Planeta.class);
		assertEquals(geonosis, planetaAdicionado);
	 }
	 
	 @Test
	 public void naoDeveAdicionarPlanetaSemNome() throws Exception {
		 Planeta planetaSemNome = new Planeta(null, "clima", "terreno");
		 Mockito.when(planetasService.adicionar(planetaSemNome)).thenReturn(planetaSemNome);
		 
		 MvcResult result = this.mockMvc.perform(post(apiUrl + "/planeta")
				 	.content(TestHelper.mapToJson(planetaSemNome))
				 	.contentType(MediaType.APPLICATION_JSON_UTF8)
				 	.accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test
	 public void naoDeveAdicionarPlanetaSemClima() throws Exception {
		 Planeta planetaSemNome = new Planeta("nome", null, "terreno");
		 Mockito.when(planetasService.adicionar(planetaSemNome)).thenReturn(planetaSemNome);
		 
		 MvcResult result = this.mockMvc.perform(post(apiUrl + "/planeta")
				 	.content(TestHelper.mapToJson(planetaSemNome))
				 	.contentType(MediaType.APPLICATION_JSON_UTF8)
				 	.accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test
	 public void naoDeveAdicionarPlanetaSemTerreno() throws Exception {
		 Planeta planetaSemNome = new Planeta("nome", "clima", null);
		 Mockito.when(planetasService.adicionar(planetaSemNome)).thenReturn(planetaSemNome);
		 
		 MvcResult result = this.mockMvc.perform(post(apiUrl + "/planeta")
				 	.content(TestHelper.mapToJson(planetaSemNome))
				 	.contentType(MediaType.APPLICATION_JSON_UTF8)
				 	.accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	 }
	 
	 @Test
	 public void deveListarPlanetasComSucesso() throws Exception {
		 Mockito.when(planetasService.listar()).thenReturn(Arrays.asList(geonosis, kamino, coruscant)); 

		 MvcResult result = this.mockMvc.perform(get(apiUrl + "/planetas").accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		Planeta[] listaPlanetas = TestHelper.mapFromJson(response.getContentAsString(), Planeta[].class);
		assertEquals(geonosis, listaPlanetas[0]);
		assertEquals(kamino, listaPlanetas[1]);
		assertEquals(coruscant, listaPlanetas[2]);
	 }
	 
	 @Test
	 public void deveBuscarPlanetaPorIdComSucesso() throws Exception {
		 Mockito.when(planetasService.buscarPorId("1")).thenReturn(Optional.of(geonosis));
		 
		 MvcResult result = this.mockMvc.perform(get(apiUrl + "/planetas/1").accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		Planeta planetaRetornado = TestHelper.mapFromJson(response.getContentAsString(), Planeta.class);
		assertEquals(geonosis, planetaRetornado);
	 }
	 
	 @Test
	 public void deveRetornarNotFoundSePlanetaNaoExisteAoBuscarPorId() throws Exception {
		 this.mockMvc.perform(get(apiUrl + "/planetas/54321")).andDo(print()).andExpect(status().isNotFound());
	 }
	 
	 @Test
	 public void deveBuscarPlanetasPorNomeComSucesso() throws Exception {
		 Mockito.when(planetasService.listarPorNome("Coruscant")).thenReturn(Arrays.asList(coruscant));

		 MvcResult result = this.mockMvc.perform(get(apiUrl + "/planetasPorNome/Coruscant").accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		Planeta[] listaPlanetas = TestHelper.mapFromJson(response.getContentAsString(), Planeta[].class);
		assertEquals(coruscant, listaPlanetas[0]);
	 }
	 
	 @Test
	 public void deveRemoverPlanetaComSucesso() throws Exception {
		 Mockito.doNothing().when(planetasService).remover(Mockito.anyString());

		 MvcResult result = this.mockMvc.perform(delete(apiUrl + "/planeta/54321")
				 	.accept(MediaType.APPLICATION_JSON_UTF8))
		 			.andDo(print())
		 			.andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	
}
