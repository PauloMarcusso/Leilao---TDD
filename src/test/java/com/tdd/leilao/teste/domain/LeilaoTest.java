package com.tdd.leilao.teste.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.tdd.leilao.domain.Lance;
import com.tdd.leilao.domain.Leilao;
import com.tdd.leilao.domain.Usuario;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new Leilao("Playstation 5");
		
		//sem nenhum lance
		assertEquals(0, leilao.getLances().size());
		
		//verificando a quantidade de lances e valor
		leilao.propoe(new Lance(new Usuario("paulo"), 2000.0));		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		
		Leilao leilao = new Leilao("Playstation 5");
		
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(3000.0, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(4000.0, leilao.getLances().get(1).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Leilao leilao = new Leilao("Playstation 5");
		
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("paulo"), 4000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(3000.0, leilao.getLances().get(0).getValor());
	}
	
	@Test
	public void naoDeveAceitarCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Playstation 5");
		
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		leilao.propoe(new Lance(new Usuario("pamela"), 4000.0));
		
		//deve ser ignorado
		leilao.propoe(new Lance(new Usuario("paulo"), 3000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(4000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.0001);
		
		
	}
}
