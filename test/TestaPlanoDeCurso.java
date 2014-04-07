import static org.junit.Assert.*;

import models.PlanoDeCurso;

import org.junit.Before;
import org.junit.Test;


public class TestaPlanoDeCurso {
	
	private static PlanoDeCurso plano1;
	private static PlanoDeCurso plano2;
	private static PlanoDeCurso plano3;
	private static PlanoDeCurso plano4;
	private static PlanoDeCurso plano5;
	private static final int NUM_PERIODOS = 8;

	@Before
	public void setUp() throws Exception {
		plano1 = new PlanoDeCurso();
		plano2 = new PlanoDeCurso();
		plano3 = new PlanoDeCurso();
		plano4 = new PlanoDeCurso();
		plano5 = new PlanoDeCurso();
	}

	@Test
	public void testGetPeriodo() {
		assertEquals(plano1.getPeriodo(0).getTotalCreditos(), 24);
	}

	@Test
	public void testGetPeriodos() {
		assertEquals(plano1.getPeriodos().size(), NUM_PERIODOS);
		assertEquals(plano2.getPeriodos().size(), NUM_PERIODOS);
		assertEquals(plano3.getPeriodos().size(), NUM_PERIODOS);
		assertEquals(plano4.getPeriodos().size(), NUM_PERIODOS);
		assertEquals(plano5.getPeriodos().size(), NUM_PERIODOS);
	}

	@Test
	public void testAddCadeira() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCadeiraNoPeriodo() {
		fail("Not yet implemented");
	}

	@Test
	public void testContemDisciplina() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumeroDeDisciplinas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalDeCreditos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCadeirasDisponiveis() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCadeira() {
		fail("Not yet implemented");
	}

	@Test
	public void testTemDependentes() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealocaCadeiras() {
		fail("Not yet implemented");
	}

	@Test
	public void testTemRequisitosDesalocados() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCadeira() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNasNaoAlocadas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPeriodoAtual() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPeriodoAtual() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculaCreditosPagos() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculaCreditosCursando() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculaCreditosPlanejados() {
		fail("Not yet implemented");
	}

}
