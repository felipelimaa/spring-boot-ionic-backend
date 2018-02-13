package br.edu.unirn.cursomc.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), //ROLE_ = exigencia do Spring Security
	CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) { // metodo que pode ser executado sem objeto instanciado
		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) { // todos valores possíveis do Perfil
			if (cod.equals(x.getCod())) { // verifica se o codigo informa está no enum e retorna
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido para Perfil de Usuário: " + cod);
	}

}
