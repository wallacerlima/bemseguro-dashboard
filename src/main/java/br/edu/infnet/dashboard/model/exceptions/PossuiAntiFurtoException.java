package br.edu.infnet.dashboard.model.exceptions;

public class PossuiAntiFurtoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public PossuiAntiFurtoException(String mensagem) {
		super(mensagem);
	}
}
