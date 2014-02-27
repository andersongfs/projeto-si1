import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

public class TestaDisciplina {
	
	private Disciplina disciplina1;
	private Disciplina disciplina2;
	private Disciplina disciplina3;
	private Disciplina disciplina4;
	
	@Before
	public void criaDisciplinas(){
		disciplina1 = new Disciplina("SI", 4);
		disciplina2 = new Disciplina("LP1", 4);
		disciplina3 = new Disciplina("GRAFOS", 2);
		disciplina4 = new Disciplina("CALC", 4);
	}

	@Test
	public void testaGetsESetsNomeCadeira(){
		assertEquals(disciplina1.getNomeCadeira(), "SI");
		assertEquals(disciplina2.getNomeCadeira(), "LP1");
		assertEquals(disciplina3.getNomeCadeira(), "GRAFOS");
		disciplina1.setNomeCadeira("Sistemas I");
		disciplina2.setNomeCadeira("Lab Prog I");
		disciplina3.setNomeCadeira("Grafos");
		assertEquals(disciplina1.getNomeCadeira(), "Sistemas I");
		assertEquals(disciplina2.getNomeCadeira(), "Lab Prog I");
		assertEquals(disciplina3.getNomeCadeira(), "Grafos");
		
	}
	
	@Test
	public void testaGetAndSetCreditos(){
		assertEquals(disciplina1.getCreditos(), 4);
		assertEquals(disciplina2.getCreditos(), 4);
		assertEquals(disciplina3.getCreditos(), 2);
		disciplina1.setCreditos(2);
		disciplina2.setCreditos(4);
		disciplina3.setCreditos(4);
		assertEquals(disciplina1.getCreditos(), 2);
		assertEquals(disciplina2.getCreditos(), 4);
		assertEquals(disciplina3.getCreditos(), 4);
		
	}
	
	@Test
	public void testaAddRequisitos(){
		disciplina1.addRequisitos(disciplina2);
		List<Disciplina> lista = new ArrayList<Disciplina>();
		lista.add(disciplina3);
		lista.add(disciplina4);
		disciplina1.addListaRequisitos(lista);
		assertEquals(disciplina1.ehPreRequisito(disciplina2), true);
		assertEquals(disciplina1.ehPreRequisito(disciplina3), true);
		assertEquals(disciplina1.ehPreRequisito(disciplina4), true);
	}
	
	@Test
	public void testaRemoveRequisitos(){
		disciplina1.addRequisitos(disciplina2);
		List<Disciplina> lista = new ArrayList<Disciplina>();
		lista.add(disciplina3);
		lista.add(disciplina4);
		disciplina1.addListaRequisitos(lista);
		assertEquals(disciplina1.ehPreRequisito(disciplina2), true);
		assertEquals(disciplina1.ehPreRequisito(disciplina3), true);
		assertEquals(disciplina1.ehPreRequisito(disciplina4), true);
		disciplina1.removeRequisito(disciplina3);
		disciplina1.removeRequisito(disciplina2);
		assertEquals(disciplina1.ehPreRequisito(disciplina2), false);
		assertEquals(disciplina1.ehPreRequisito(disciplina3), false);
		}
	
	@Test
	public void testaGetESetDificuldade(){
		disciplina1.setDificuldade(5);
		// nao tem como testar a dificuldade randomica.
		assertEquals(disciplina1.getDificuldade(), 5);
	}
	
	@Test
	public void testaEquals(){
		assertEquals(disciplina1.equals(disciplina2), false);
		Disciplina tempDisciplina = new Disciplina("SI", 4);
		assertEquals(disciplina1.equals(tempDisciplina), true);
	}

}
