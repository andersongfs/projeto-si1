import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.List;

import models.CatalogoDisciplinas;
import models.PlanoDeCurso;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TesteComPersistencia {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		CatalogoDisciplinas catalogo = new CatalogoDisciplinas();
		catalogo.save();
	}
	
	@Test
	public void criaPlano() throws Exception {
		CatalogoDisciplinas c = CatalogoDisciplinas.find.findUnique();
		Assert.assertNotNull(c);
		
		PlanoDeCurso plano = new PlanoDeCurso(c);
		plano.save();
		Assert.assertNotNull(plano.getCatalogo());
		
		PlanoDeCurso p = PlanoDeCurso.find.findUnique();
		Assert.assertNotNull(p.getCatalogo());
		Assert.assertNotNull(p.getCadeira("Programacao II").getId());
		
		PlanoDeCurso p2 = PlanoDeCurso.find.findUnique();
		Assert.assertNotNull(p2.getCadeira("Programacao II").getId());
		//assertEquals(plano, Ebean.find(beanType))
	}
	

}
