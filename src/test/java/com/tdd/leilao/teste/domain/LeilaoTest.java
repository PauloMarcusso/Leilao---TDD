package com.tdd.leilao.teste.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.tdd.leilao.builder.CriadorDeLeilao;
import com.tdd.leilao.domain.Lance;
import com.tdd.leilao.domain.Leilao;
import com.tdd.leilao.domain.Usuario;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation5").constroi();

		// sem nenhum lance
		assertEquals(0, leilao.getLances().size());

		// verificando a quantidade de lances e valor
		leilao.propoe(new Lance(new Usuario("paulo"), 2000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void deveReceberVariosLances() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation5").lance(new Usuario("Paulo"), 3000)
				.lance(new Usuario("Pamela"), 4000).constroi();

		assertEquals(2, leilao.getLances().size());
		assertEquals(3000.0, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(4000.0, leilao.getLances().get(1).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

		Usuario paulo = new Usuario("Paulo");
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(paulo, 2000.0)
				.lance(paulo, 3000.0).constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor());
	}

	@Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000)
                .lance(billGates, 3000)
                .lance(steveJobs, 4000)
                .lance(billGates, 5000)
                .lance(steveJobs, 6000)
                .lance(billGates, 7000)
                .lance(steveJobs, 8000)
                .lance(billGates, 9000)
                .lance(steveJobs, 10000)
                .lance(billGates, 11000)
                .lance(steveJobs, 12000)
                .constroi();

        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size()-1;
        assertEquals(11000.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
    }

	@Test
	public void deveRetornarODobroDoUltimoLanceDado() {
		Usuario paulo = new Usuario("Paulo");
		Usuario pamela = new Usuario("Pamela");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(paulo, 2000.0));
		leilao.propoe(new Lance(pamela, 3000.0));
		leilao.dobraLance(paulo);

		assertEquals(4000.0, leilao.getLances().get(2).getValor(), 0.00001);

	}

	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Playstation 5");

		Usuario paulo = new Usuario("Paulo");
		Usuario pamela = new Usuario("Pamela");

		leilao.dobraLance(pamela);

		assertEquals(0, leilao.getLances().size());
	}

}
