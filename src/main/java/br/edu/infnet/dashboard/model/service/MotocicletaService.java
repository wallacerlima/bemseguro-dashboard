package br.edu.infnet.dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.dashboard.clients.IMotocicletaClient;
import br.edu.infnet.dashboard.model.domain.Motocicleta;
import br.edu.infnet.dashboard.model.domain.Usuario;

@Service
public class MotocicletaService {

	@Autowired
	private IMotocicletaClient motocicletaClient;

	public List<Motocicleta> obterLista() {
		return motocicletaClient.obterLista();
	}

	public List<Motocicleta> obterLista(Usuario usuario) {
		 return  motocicletaClient.obterLista(usuario.getId());
	}

	public void incluir(Motocicleta motocicleta) {
		motocicletaClient.inserir(motocicleta);
	}

	public void excluir(Integer id) {
		motocicletaClient.excluir(id);
	}

	public Motocicleta obterPorId(Integer id) {
		
		return motocicletaClient.obterPorId(id);
	}

	public Integer obterQtd() {
		return motocicletaClient.obterLista().size();
	}
}