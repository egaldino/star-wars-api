package com.edson.starwars.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edson.starwars.model.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String>{

	public List<Planeta> findByNomeContaining(String nome);

}
