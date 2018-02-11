package br.edu.unirn.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) { // metodo que pode ser executado sem objeto instanciado
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) { // todos valores possíveis do EstadoPagamento
			if (cod.equals(x.getCod())) { // verifica se o codigo informa está no enum e retorna
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido para Estado Pagamento: " + cod);
	}

}
