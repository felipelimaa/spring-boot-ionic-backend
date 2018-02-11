package br.edu.unirn.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) { // metodo que pode ser executado sem objeto instanciado
		if (cod == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) { // todos valores possíveis do TipoCliente
			if (cod.equals(x.getCod())) { // verifica se o codigo informa está no enum e retorna
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido para Tipo Cliente: " + cod);
	}

}
