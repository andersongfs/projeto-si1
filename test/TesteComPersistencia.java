import java.util.List;

import models.PlanoDeCurso;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import static play.test.Helpers.*;


public class TesteComPersistencia {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void criaPlano() throws Exception {
		PlanoDeCurso plano = new PlanoDeCurso();
		plano.save();
		List<PlanoDeCurso> p = plano.find.all();
		p.get(0);
		System.out.println(p.get(0).getCadeira("Programacao II").getId());
		
		//assertEquals(plano, Ebean.find(beanType))
	}
	

}
