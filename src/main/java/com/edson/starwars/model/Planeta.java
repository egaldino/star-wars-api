package com.edson.starwars.model;

import javax.validation.constraints.NotEmpty;

public class Planeta {

	private String id;
	
	@NotEmpty(message="Preencha o nome do planeta")
	private String nome;
	
	@NotEmpty(message="Preencha o clima do planeta")
	private String clima;
	
	@NotEmpty(message="Preencha o terreno do planeta")
	private String terreno;
	
	private Integer aparicoesEmFilmes;
	
	public Planeta() {}
	
	public Planeta(String nome, String clima, String terreno, Integer aparicoesEmFilmes) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}
	
	public Planeta(String nome, String clima, String terreno) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}

	public Planeta(String id, String nome, String clima, String terreno, Integer aparicoesEmFilmes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Integer getAparicoesEmFilmes() {
		return aparicoesEmFilmes;
	}
	public void setAparicoesEmFilmes(Integer aparicoesEmFilmes) {
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}
	
	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", aparicoesEmFilmes=" + aparicoesEmFilmes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aparicoesEmFilmes == null) ? 0 : aparicoesEmFilmes.hashCode());
		result = prime * result + ((clima == null) ? 0 : clima.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((terreno == null) ? 0 : terreno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (aparicoesEmFilmes == null) {
			if (other.aparicoesEmFilmes != null)
				return false;
		} else if (!aparicoesEmFilmes.equals(other.aparicoesEmFilmes))
			return false;
		if (clima == null) {
			if (other.clima != null)
				return false;
		} else if (!clima.equals(other.clima))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (terreno == null) {
			if (other.terreno != null)
				return false;
		} else if (!terreno.equals(other.terreno))
			return false;
		return true;
	}
	
	
}
