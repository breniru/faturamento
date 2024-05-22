package br.com.projetofaturamento.Faturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofaturamento.Faturamento.repository.FaturamentoModeloRepository;

@RestController
@RequestMapping("/faturamentos")
public class FaturamentoController {

	@Autowired
	private FaturamentoModeloRepository faturamentoModeloRepository;
	
	@GetMapping
	@PostMapping
	@PutMapping
	@DeleteMapping
	
}
