package br.edu.infnet.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.dashboard.model.service.VeiculoService;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping(value = "/veiculos")
	public String obterLista(Model model) {
		
		model.addAttribute("listagem", veiculoService.obterLista(1));		

		return "lista/veiculos";
	}
}
