package br.edu.infnet.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.dashboard.model.domain.Usuario;
import br.edu.infnet.dashboard.model.service.CarroService;
import br.edu.infnet.dashboard.model.service.CaminhaoService;
import br.edu.infnet.dashboard.model.service.LogService;
import br.edu.infnet.dashboard.model.service.ApoliceService;
import br.edu.infnet.dashboard.model.service.MotocicletaService;
import br.edu.infnet.dashboard.model.service.UsuarioService;

@Controller
public class DashboardController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApoliceService apoliceService;
	
	@Autowired
	private CarroService carroService;

	@Autowired
	private CaminhaoService caminhaoService;

	@Autowired
	private MotocicletaService motocicletaService;

	@Autowired
	private LogService logService;

	@GetMapping(value = "/")
	public String index(Model model) {

		//produtos e pedidos por usuário
		List<Usuario> usuarios = usuarioService.obterLista();		
		model.addAttribute("apolicesPorUsuario", usuarios);		
		model.addAttribute("veiculosPorUsuario", usuarios);
		
		//quantidade de pedidos
		model.addAttribute("qtdeApolices", apoliceService.obterLista().size());
		
		//valor de vendos dos produtos
		model.addAttribute("qtdCarros", carroService.obterLista().size());
		model.addAttribute("qtdCaminhao", caminhaoService.obterLista().size());
		model.addAttribute("qtdMotocicleta", motocicletaService.obterLista().size());

		//recuperar o log
		model.addAttribute("listaLog", logService.obterLista());
		model.addAttribute("qtdeLog", logService.obterQuantidade());
		
		return "index";
	}
}