package com.edson.starwars.dto;

import java.util.List;

public class ResultadoSwapiDTO {

	private Integer count;
	private List<PlanetaSwapiDTO> results;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<PlanetaSwapiDTO> getResults() {
		return results;
	}
	public void setResults(List<PlanetaSwapiDTO> results) {
		this.results = results;
	}
	
	
}
