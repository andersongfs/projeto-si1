import static org.junit.Assert.*;
import models.Disciplina;
import models.Global;
import models.JaContemDisciplinaException;
import models.LimitesExcedidosException;
import models.PlanoDeCurso;
import models.PrerequisitosInsuficientesException;
import models.Usuario;

import org.junit.*;

//Comentario Geral: Diante desse teste, não é nescessário fazer uma classe de teste apenas para o PLano de Curso
//tendo em vista que os outros metodos de plano de curso ja foram testados em TestaPeriodo.
public class BehaviourTest {

	private PlanoDeCurso controlador;
	private Usuario user;
	private PlanoDeCurso userPlan;
	
	@Before
	public void setUp(){
		controlador = new PlanoDeCurso();
		userPlan = new PlanoDeCurso();
		user = new Usuario("teste@teste", "teste", "123456", userPlan);

	}
	
	
	@Test
	public void adicionarCadeira() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		controlador.addCadeiraNoPeriodo(1, new Disciplina("CALC II"));
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
	}
	
	@Test
	public void testaAdicionarCadeiraQueTemPrerequisito(){
		
	}
	
	@Test 
	public void removerCadeiraSemDependentes() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		controlador.addCadeiraNoPeriodo(1, new Disciplina("CALC II"));
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
		controlador.removeCadeira(1, controlador.getCadeira("CALC II").getId());
		assertTrue(!controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
	}
	
	@Test
	public void removerCadeiraComDependentes() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		controlador.addCadeira(1, "CALC II");
		controlador.addCadeira(2, "PROBABILIDADE");
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
		assertTrue(controlador.getPeriodo(2).contains(new Disciplina("PROBABILIDADE", 4)));
		controlador.removeCadeira(1, "CALC II");
		assertTrue(!controlador.getPeriodo(2).contains(new Disciplina("PROBABILIDADE", 4)));
		assertTrue(!controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
			
	}
	
	@Test
	public void estourarCreditosDoPeriodo() throws JaContemDisciplinaException {
		try {
			controlador.addCadeira(1, "CALC II");
			controlador.addCadeira(1, "LPROG2");
			controlador.addCadeira(1, "PROG2");
			controlador.addCadeira(1, "DISCRETA");
			controlador.addCadeira(1, "FFC");
			controlador.addCadeira(1, "LINEAR");
			controlador.addCadeira(1, "OPTATIVA 1");
			assertEquals(controlador.getPeriodo(1).creditosTotal(), controlador.getPeriodo(1).LIMITE_CREDITOS);
			controlador.addCadeira(1, "OPTATIVA 2");
			fail();
		} catch (LimitesExcedidosException e) {
			assertTrue(e instanceof LimitesExcedidosException); 
			// dumb ain't it?
		} catch (PrerequisitosInsuficientesException pie){
			fail();
		}		
	}
	
	@Test
	public void realocarCadeira() throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException{
		controlador.addCadeira(1, "CALC II");
		controlador.addCadeira(2, "PROBABILIDADE");
		controlador.addCadeira(1, "DISCRETA");
		assertTrue(controlador.getPeriodo(1).contains(catalogo.getCadeira("DISCRETA")));
		
		controlador.realocaCadeiras(1, 2, "DISCRETA");
		assertFalse(controlador.getPeriodo(1).contains(catalogo.getCadeira("DISCRETA")));
		assertTrue(controlador.getPeriodo(2).contains(catalogo.getCadeira("DISCRETA")));
	}
	
	@Test
	public void testUser(){
		
		assertEquals("teste@teste", user.getEmail());
		assertEquals("teste", user.getNome());
		
		assertTrue(user.getPlano().equals(userPlan));
		
	}
	
}
