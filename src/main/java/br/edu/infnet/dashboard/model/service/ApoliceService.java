package br.edu.infnet.dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.dashboard.clients.IApoliceClient;
import br.edu.infnet.dashboard.model.domain.Apolice;
import br.edu.infnet.dashboard.model.domain.Usuario;


@Service
public class ApoliceService {

	@Autowired
	private IApoliceClient apoliceClient;

	public List<Apolice> obterLista() {
		return apoliceClient.obterLista();
	}

	public List<Apolice> obterLista(Usuario usuario) {
		 return apoliceClient.obterLista(usuario.getId());
	}

	public void incluir(Apolice Apolice) {
		apoliceClient.incluir(Apolice);

	}

	public void excluir(Integer id) {
		apoliceClient.excluir(id);
	}

	public Apolice obterPorId(Integer id) {
		return apoliceClient.obterPorId(id);
	}

	public Integer obterQtd() {
		return apoliceClient.obterLista().size();
	}
}
