package it.uniroma3.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.spring.model.Admin;
import it.uniroma3.spring.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo,Long>{

	List<Ruolo> findByUsername(String username );
	List<Ruolo> findByRuolo(String ruolo);
    
    void delete(Long id);
}
