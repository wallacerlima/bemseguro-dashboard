package br.edu.infnet.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.dashboard.model.service.SeguradoService;

@Controller
public class SeguradoController {
	
	@Autowired
	private SeguradoService seguradoService;

	@GetMapping(value = "/segurados")
	public String obterLista(Model model) {
		
		model.addAttribute("listagem", seguradoService.obterLista());
		
		return "lista/segurados";
	}
}