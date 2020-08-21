package com.coutomariel.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coutomariel.osworks.domain.model.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {

	Cliente findByEmail(String email);
	
}
