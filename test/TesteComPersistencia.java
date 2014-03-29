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

	private PlanoDeCurso plano;
	private Usuario user;
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		plano = new PlanoDeCurso();
		plano.save();
		user = new Usuario("n@email.com", "nathan", "senha", plano);
		user.save();
	}

	
	@Test
    public void criaUsuarioEPLano() throws Exception {
            Assert.assertNotNull(Usuario.find.byId(user.getId()));
            Assert.assertEquals("nathan", Usuario.find.byId(user.getId()).getNome());
    }
	
	
	@Test
	public void deveAdicionarERemoverCadeiraEmPlanoUsuario() throws PrerequisitosInsuficientesException,
	LimitesExcedidosException, JaContemDisciplinaException{
		
		/*
		 * A aplicação ja começa povoando todos os periodos, entao
		 * para que o teste surta efeito, vamos remover e depois adicionar a mesma cadeira
		 */
		PlanoDeCurso p2 = new PlanoDeCurso();
		user.getPlano().removeCadeira(1, user.getPlano().getCadeira("Programacao II").getId());
		user.update();
		
		Assert.assertTrue(user.getPlano().getCadeirasDisponiveis().contains(p2.getCadeira("Programacao II")));
		
		user.getPlano().addCadeiraNoPeriodo(1, p2.getCadeira("Programacao II"));
		user.update();
		Assert.assertFalse(user.getPlano().getCadeirasDisponiveis().contains(plano.getCadeira("Programacao II")));
		Assert.assertNotNull(Usuario.find.byId(user.getId()).getPlano().getCadeira("Programacao II"));
		Assert.assertEquals("Programacao II", Usuario.find.byId(user.getId()).getPlano().getCadeira("Programacao II").getNomeCadeira());
		
		user.getPlano().addCadeiraNoPeriodo(2, p2.getCadeira("Estrutura de Dados"));
		user.update();
		Assert.assertFalse(user.getPlano().getCadeirasDisponiveis().contains(plano.getCadeira("Estrutura de Dados")));
		Assert.assertNotNull(Usuario.find.byId(user.getId()).getPlano().getCadeira("Estrutura de Dados"));
		Assert.assertEquals("Estrutura de Dados", Usuario.find.byId(user.getId()).getPlano().getCadeira("Estrutura de Dados").getNomeCadeira());
		
		
		
	}
	
     @Test
     public void testRealocarCadeira() throws LimitesExcedidosException,
		JaContemDisciplinaException{
    	 
    	 user.getPlano().realocaCadeiras(1, 4, plano.getCadeira("Programacao II"));
    	 user.update();
    	// Verifica se a disciplina esta contida no periodo que foi realocada
    	 Assert.assertTrue(user.getPlano().getPeriodo(4).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 Assert.assertTrue(Usuario.find.byId(user.getId()).getPlano().getPeriodo(4).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	// Verifica se a disciplina não foi duplica e realmente nao esta mais no
 		// antigo periodo
    	 Assert.assertFalse(user.getPlano().getPeriodo(1).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 Assert.assertFalse(Usuario.find.byId(user.getId()).getPlano().getPeriodo(1).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 
    	 //voltar cadeira para pediodo original

    	 user.getPlano().realocaCadeiras(4, 1, plano.getCadeira("Programacao II"));
    	 user.update();
    	 Assert.assertTrue(user.getPlano().getPeriodo(1).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 Assert.assertTrue(Usuario.find.byId(user.getId()).getPlano().getPeriodo(1).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	// Verifica se a disciplina não foi duplica e realmente nao esta mais no
 		// antigo periodo
    	 Assert.assertFalse(user.getPlano().getPeriodo(4).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 Assert.assertFalse(Usuario.find.byId(user.getId()).getPlano().getPeriodo(4).getDisciplinas().contains(plano.getCadeira("Programacao II")));
    	 
    	 
     }
	
	
	
	@Test 
	public void testaUsuario(){
		
		PlanoDeCurso plano3 = new PlanoDeCurso();
		Usuario usuario = new Usuario("tiago@gmail.com", "tiago", "tiago1991", plano3);
		usuario.save();
		Assert.assertTrue(usuario.getPlano() == plano3);
		Assert.assertNotNull(Usuario.find.byId(usuario.getId()));
		Assert.assertEquals("tiago", Usuario.find.byId(usuario.getId()).getNome());
		
	}
	
	

}
