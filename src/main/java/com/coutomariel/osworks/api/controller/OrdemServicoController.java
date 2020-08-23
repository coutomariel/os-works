package com.coutomariel.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coutomariel.osworks.api.model.OrdemServicoInput;
import com.coutomariel.osworks.api.model.OrdemServicoModel;
import com.coutomariel.osworks.domain.model.OrdemServico;
import com.coutomariel.osworks.domain.repository.OrdemServicoRepository;
import com.coutomariel.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService ordemServicoService;

	@Autowired
	private OrdemServicoRepository osRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdemServicoModel salvar(@Valid @RequestBody OrdemServicoInput os) {
		return toModel(ordemServicoService.criar(toEntity(os)));
	}

	@GetMapping
	public List<OrdemServicoModel> listar() {
		return toCollectionDto(osRepository.findAll());
	}

	@GetMapping("/{ClienteId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ClienteId) {
		Optional<OrdemServico> os = osRepository.findById(ClienteId);
		if (os.isPresent()) {
			return ResponseEntity.ok(toModel(os.get()));
		}
		return ResponseEntity.notFound().build();
	}

//	Métodos de para conversão de dtos
	private OrdemServicoModel toModel(OrdemServico os) {
		return modelMapper.map(os, OrdemServicoModel.class);
	}

	private List<OrdemServicoModel> toCollectionDto(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(os -> toModel(os)).collect(Collectors.toList());
	}

	private OrdemServico toEntity(OrdemServicoInput osImput) {
		return modelMapper.map(osImput, OrdemServico.class);
	}
}
