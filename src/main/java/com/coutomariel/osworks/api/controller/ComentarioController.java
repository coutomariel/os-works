package com.coutomariel.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coutomariel.osworks.api.model.Comentario;
import com.coutomariel.osworks.api.model.ComentarioInput;
import com.coutomariel.osworks.api.model.ComentarioModel;
import com.coutomariel.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.coutomariel.osworks.domain.model.OrdemServico;
import com.coutomariel.osworks.domain.repository.OrdemServicoRepository;
import com.coutomariel.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{osId}/comentarios")
public class ComentarioController {
	
	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long osId){
		OrdemServico os = ordemServicoRepository.findById(osId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("OS não encontrada"));
		
		return toCollectionModel(os.getComentarios());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long osId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServicoService
				.adicionarComentario(osId, comentarioInput.getDescricao());
		return toModel(comentario);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}

	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
}
