import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import models.JaContemDisciplinaException;
import models.LimitesExcedidosException;
import models.Periodo;
import models.PlanoDeCurso;
import models.PrerequisitosInsuficientesException;
import models.Usuario;

import org.apache.bcel.verifier.exc.VerificationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

public class TesteComPersistencia {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void criaPlano() throws Exception {
		PlanoDeCurso plano = new PlanoDeCurso();
		plano.save();
		Usuario user = new Usuario("n@email.com", "nathan", "senha", plano);
		PlanoDeCurso p = PlanoDeCurso.find.findUnique();
		Assert.assertNotNull(p.getCadeira("Programacao II").getId());
		PlanoDeCurso plano1 = PlanoDeCurso.find.findUnique();
		Assert.assertNotNull(plano1.getCadeira("Programacao II").getId());
		// assertEquals(plano, Ebean.find(beanType))

	}

	@Test
	public void testRemove() {
		CatalogoDisciplinas c = CatalogoDisciplinas.find.findUnique();
		PlanoDeCurso p3 = new PlanoDeCurso(c);
		p3.save();
		// Removendo cadeira
		p3.removeCadeira(1, p3.getCadeira("Programacao II").getNomeCadeira());
		// Cadeira contida na lista de disciplinas disponiveis
		p3.update();
		Assert.assertTrue(p3.getCadeirasDisponiveis().contains(
				p3.getCadeira("Programacao II")));
		// Tamanho da lista contendo programacao II + dependencias.
		Assert.assertTrue(p3.getCadeirasDisponiveis().size() == 18); 
		// Cadeiras que são dependências
		Assert.assertTrue(p3.getCadeirasDisponiveis().contains(
				p3.getCadeira("Estrutura de Dados")));
		Assert.assertTrue(p3.getCadeirasDisponiveis().contains(
				p3.getCadeira("Lab Estrutura de Dados")));

		//Assert.assertEquals(false,p3.getCadeira("Programacao II").isAlocada());
	}

	@Test
	public void testeadiciona() throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {

		CatalogoDisciplinas c = CatalogoDisciplinas.find.findUnique();
		PlanoDeCurso p2 = new PlanoDeCurso(c);
		p2.removeCadeira(1, p2.getCadeira("Programacao II").getNomeCadeira());

		// Adicionando Cadeira
		p2.adicionaCadeira(1, p2.getCadeira("Programacao II").getNomeCadeira());

		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(
				p2.getCadeira("Programacao II")));

		// Adicionando nos periodos posteriores
		p2.adicionaCadeira(2, p2.getCadeira("Estrutura de Dados")
				.getNomeCadeira());
		p2.adicionaCadeira(2, p2.getCadeira("Lab Estrutura de Dados")
				.getNomeCadeira());

		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(
				p2.getCadeira("Estrutura de Dados")));
		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(
				p2.getCadeira("Lab Estrutura de Dados")));
	}

	@Test
	public void testeRealocar() throws LimitesExcedidosException,
			JaContemDisciplinaException {

		// Realocar
		CatalogoDisciplinas d = CatalogoDisciplinas.find.findUnique();
		PlanoDeCurso plano3 = new PlanoDeCurso(d);
		plano3.save();
		Assert.assertNotNull(Ebean.find(Periodo.class, 4).getId().intValue());
		plano3.realocaCadeiras(1, 4, plano3.getCadeira("Programacao II")
				.getNomeCadeira());
		// Verifica se a disciplina esta contida no periodo que foi realocada
		Assert.assertTrue(plano3.getPeriodo(4).getDisciplinas()
				.contains(plano3.getCadeira("Programacao II")));
		// Verifica se a disciplina não foi duplica e realmente nao esta mais no
		// antigo periodo
		Assert.assertFalse(plano3.getPeriodo(1).getDisciplinas()
				.contains(plano3.getCadeira("Programacao II")));
		// Voltar a cadeira pra o perido original
		plano3.realocaCadeiras(4, 1, plano3.getCadeira("Programacao II")
				.getNomeCadeira());
		Assert.assertTrue(plano3.getPeriodo(1).getDisciplinas()
				.contains(plano3.getCadeira("Programacao II")));
		Assert.assertFalse(plano3.getPeriodo(4).getDisciplinas()
				.contains(plano3.getCadeira("Programacao II")));

		// Realocar para um perido com o total de creditos lotados
		plano3.realocaCadeiras(1, 2, plano3.getCadeira("Programacao II")
				.getNomeCadeira());
		// Ela deve fica no mesmo periodo
		Assert.assertTrue(plano3.getPeriodo(1).getDisciplinas()
				.contains(plano3.getCadeira("Programacao II")));
		
		
		
	
	}
	
	@Test 
	public void testaUsuario(){
		CatalogoDisciplinas d = CatalogoDisciplinas.find.findUnique();
		PlanoDeCurso plano3 = new PlanoDeCurso(d);
		plano3.save();
		Usuario usuario = new Usuario("tiago@gmail.com", "tiago", "tiago1991", plano3);
		
		Assert.assertTrue(usuario.getPlano() == plano3);
		
		
	}
	
	

}
