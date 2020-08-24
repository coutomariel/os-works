package com.coutomariel.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coutomariel.osworks.api.model.Comentario;
import com.coutomariel.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.coutomariel.osworks.domain.exception.NegocioException;
import com.coutomariel.osworks.domain.model.Cliente;
import com.coutomariel.osworks.domain.model.OrdemServico;
import com.coutomariel.osworks.domain.model.StatusOrdemServico;
import com.coutomariel.osworks.domain.repository.ClientesRepository;
import com.coutomariel.osworks.domain.repository.ComentarioRepository;
import com.coutomariel.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClientesRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

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

	public void finalizar(Long ordemServicoId) {
		OrdemServico os = buscar(ordemServicoId);
		os.finalizar();
		ordemServicoRepository.save(os);
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico os = buscar(ordemServicoId);

		Comentario comentario = new Comentario();
		comentario.setOs(os);
		comentario.setDescricao(descricao);
		comentario.setDataEnvio(OffsetDateTime.now());

		return comentarioRepository.save(comentario);
	}

	private OrdemServico buscar(Long OrdemServicoId) {
		return ordemServicoRepository.findById(OrdemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Os não encontrada!"));
	}
}
