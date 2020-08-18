package com.coutomariel.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coutomariel.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("João das Neves");
		cliente1.setEmail("jneves@teste.com");
		cliente1.setTelefone("33221418");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Brandon Stark");
		cliente2.setEmail("brandon@teste.com");
		cliente2.setTelefone("33181732");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
