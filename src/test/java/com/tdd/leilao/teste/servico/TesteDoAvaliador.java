package com.tdd.leilao.teste.servico;

import org.junit.Assert;
import org.junit.Test;

import com.tdd.leilao.domain.Lance;
import com.tdd.leilao.domain.Leilao;
import com.tdd.leilao.domain.Usuario;
import com.tdd.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveCalcularAMedia() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5 Novo");

		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		Assert.assertEquals(300, leiloeiro.getMedia(), 0.0001);
	}

	@Test
	public void testaMediaDeZeroLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");

		Leilao leilao = new Leilao("Playstation 5 Novo");

		leilao.propoe(new Lance(joao, 0.0));
		leilao.propoe(new Lance(jose, 0.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		Assert.assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 5 Novo");

		leilao.propoe(new Lance(joao, 110.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(110, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(110.0, leiloeiro.getMenorLance(), 0.0001);
	}

}
