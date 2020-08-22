package com.coutomariel.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private OrdemServico salvar(@Valid @RequestBody OrdemServico os) {
		return ordemServicoService.criar(os);
	}
	
	@GetMapping
	private List<OrdemServico> listar(){
		return osRepository.findAll();
	}
	
	@GetMapping("/{ClienteId}")
	private ResponseEntity<OrdemServico> buscar(@PathVariable Long ClienteId) {
		Optional<OrdemServico> os = osRepository.findById(ClienteId);
		if(os.isPresent()) {
			return ResponseEntity.ok(os.get());
		}
		return ResponseEntity.notFound().build();
	}
}
