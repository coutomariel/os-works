package com.coutomariel.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coutomariel.osworks.domain.exception.NegocioException;
import com.coutomariel.osworks.domain.model.Cliente;
import com.coutomariel.osworks.domain.model.OrdemServico;
import com.coutomariel.osworks.domain.model.StatusOrdemServico;
import com.coutomariel.osworks.domain.repository.ClientesRepository;
import com.coutomariel.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClientesRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico os) {
		Cliente cliente = clienteRepository.findById(os.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
		os.setCliente(cliente);
		os.setDataAbertura(OffsetDateTime.now());
		os.setStatus(StatusOrdemServico.ABERTA);

		return ordemServicoRepository.save(os);
	}
	
	public void excluir(Long osId) {
		ordemServicoRepository.deleteById(osId);
	}
}
