import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.JaContemDisciplinaException;
import models.LimitesExcedidosException;
import models.Periodo;

import org.junit.Before;
import org.junit.Test;

public class TestaPeriodo {
	
	private Periodo periodo;
	private Disciplina disciplina1;
	private Disciplina disciplina2;
	private Disciplina disciplina3;
	private Disciplina disciplina4;
	
	@Before
	public void before(){
		periodo = new Periodo();
		disciplina1 = new Disciplina("SI", 4);
		disciplina2 = new Disciplina("LP1", 4);
		disciplina3 = new Disciplina("GRAFOS", 2);
		disciplina4 = new Disciplina("CALC", 4);
		
	}
	
	@Test
	public void testaAddDisciplinas() throws LimitesExcedidosException, JaContemDisciplinaException{
		periodo.addCadeira(disciplina1);
		periodo.addCadeira(disciplina2);
		List<Disciplina> lista = new ArrayList<Disciplina>();
		lista.add(disciplina1);
		lista.add(disciplina2);
		assertEquals(periodo.contains(disciplina1),true);
		assertEquals(periodo.contains(disciplina2),true);
		assertEquals(periodo.getDisciplinas(), lista);
	}
	
	@Test
	public void testaRemoveCadeira() throws LimitesExcedidosException, JaContemDisciplinaException{
		periodo.addCadeira(disciplina1);
		periodo.addCadeira(disciplina2);
		assertEquals(periodo.contains(disciplina1),true);
		assertEquals(periodo.contains(disciplina2),true);
		periodo.removeDisciplina(disciplina1);
		assertEquals(periodo.contains(disciplina1), false);
	}
	@Test
	public void testaQtdCadeiras() throws LimitesExcedidosException, JaContemDisciplinaException{
		periodo.addCadeira(disciplina1);
		assertEquals(periodo.qtdeCadeiras(), 1);
		periodo.addCadeira(disciplina2);
		assertEquals(periodo.qtdeCadeiras(), 2);
		periodo.addCadeira(disciplina3);
		assertEquals(periodo.qtdeCadeiras(), 3);
		periodo.addCadeira(disciplina4);
		assertEquals(periodo.qtdeCadeiras(), 4);
		periodo.removeDisciplina(disciplina4);
		assertEquals(periodo.qtdeCadeiras(), 3);
	}
	
	@Test
	public void testaCreditosTotal() throws LimitesExcedidosException, JaContemDisciplinaException{
		periodo.addCadeira(disciplina1);
		assertEquals(periodo.creditosTotal(), 4);
		periodo.addCadeira(disciplina2);
		assertEquals(periodo.creditosTotal(), 8);
		periodo.addCadeira(disciplina3);
		assertEquals(periodo.creditosTotal(), 10);
		periodo.addCadeira(disciplina4);
		assertEquals(periodo.creditosTotal(), 14);
		periodo.removeDisciplina(disciplina4);
		assertEquals(periodo.creditosTotal(), 10);
	}
	
	//Não da pra testar a dificuldade, pois a dificuldade é randomica.
	
}
