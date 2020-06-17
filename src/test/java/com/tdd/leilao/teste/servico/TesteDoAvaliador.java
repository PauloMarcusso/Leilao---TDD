package com.tdd.leilao.teste.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");

		Leilao leilao = new Leilao("Playstation 5 Novo");

		leilao.propoe(new Lance(joao, 110.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(110.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(110.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveRetornarOsTresMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		Usuario rita = new Usuario("Rita");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 1000));
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(maria, 3000));
		leilao.propoe(new Lance(rita, 4000));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
	}

	@Test
	public void deveRetornarApenasTresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		Usuario rita = new Usuario("Rita");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 1000));
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(maria, 3000));
		leilao.propoe(new Lance(rita, 4000));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
	}
	
	@Test
	public void deveRetornarApenasDoisLances() {
		
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 1000));
		leilao.propoe(new Lance(jose, 2000));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
	}
	
	@Test
	public void naoDeveRetornarNenhumLance() {
		
		Leilao leilao = new Leilao("Playstation 5");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> lances = leiloeiro.getTresMaiores();
		
		assertEquals(0, lances.size());
	}

}
