package br.edu.infnet.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.dashboard.model.service.ApoliceService;

@Controller
public class ApoliceController {
	
	@Autowired
	private ApoliceService apoliceService;

	@GetMapping(value = "/apolices")
	public String obterLista(Model model) {
		
		model.addAttribute("listagem", apoliceService.obterLista());
		
		return "lista/apolices";
	}
}
