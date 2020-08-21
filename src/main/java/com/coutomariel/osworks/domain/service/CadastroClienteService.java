package com.coutomariel.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coutomariel.osworks.domain.exception.NegocioException;
import com.coutomariel.osworks.domain.model.Cliente;
import com.coutomariel.osworks.domain.repository.ClientesRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clientesRepository.findByEmail(cliente.getEmail());
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este e=mail");
		}
		
		return clientesRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clientesRepository.deleteById(clienteId);
	}
	
	
}
