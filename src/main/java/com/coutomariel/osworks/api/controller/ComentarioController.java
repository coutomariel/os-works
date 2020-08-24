package com.coutomariel.osworks.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coutomariel.osworks.api.model.Comentario;
import com.coutomariel.osworks.api.model.ComentarioInput;
import com.coutomariel.osworks.api.model.ComentarioModel;
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
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long osId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServicoService
				.adicionarComentario(osId, comentarioInput.getDescricao());
		return toModel(comentario);
	}

	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
}
