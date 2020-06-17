package com.tdd.leilao.builder;

import com.tdd.leilao.domain.Lance;
import com.tdd.leilao.domain.Leilao;
import com.tdd.leilao.domain.Usuario;

public class CriadorDeLeilao {

	private Leilao leilao;

	public CriadorDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}
	
	public CriadorDeLeilao lance(Usuario usuario, double valor) {
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}
	
	public Leilao constroi() {
		return leilao;
	}
}
