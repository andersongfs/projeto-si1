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
	
	
	@Before
	public void setUp(){
		controlador = new PlanoDeCurso();
		user = new Usuario("teste@teste", "teste", "123456", controlador);

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
		Disciplina calcII = Disciplina.find.byId(Long.parseLong("10"));
		user.getPlano().getPeriodo(1).addCadeira(calcII);
		assertTrue(user.getPlano().getPeriodo(1).contains(calcII));
		//controlador.removeCadeira(1, );
		//assertTrue(!controlador.getPeriodo(1).contains(calcII));
	}
	
	@Test
	public void removerCadeiraComDependentes() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		 Disciplina calcII = Disciplina.find.byId(Long.parseLong("10"));
	     Disciplina prob = Disciplina.find.byId(Long.parseLong("17"));
		 prob.addRequisitos(calcII);
		
		controlador.addCadeiraNoPeriodo(1, calcII);
		controlador.addCadeiraNoPeriodo(2, prob);
		assertTrue(controlador.getPeriodo(1).contains(calcII));
		assertTrue(controlador.getPeriodo(2).contains(prob));
		controlador.removeCadeira(1, calcII.getId());
		assertTrue(!controlador.getPeriodo(2).contains(prob));
		assertTrue(!controlador.getPeriodo(1).contains(calcII));
			
	}
	
	@Test
	public void estourarCreditosDoPeriodo() throws JaContemDisciplinaException, PrerequisitosInsuficientesException {
		try {
			controlador.addCadeiraNoPeriodo(1, new Disciplina("CALC II",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("LPROG2",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("PROG2",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("DISCRETA",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("FFC",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("LINEAR",4));
			controlador.addCadeiraNoPeriodo(1, new Disciplina("OPTATIVA 1",4));
			
			assertEquals(controlador.getPeriodo(1).creditosTotal(), controlador.getPeriodo(1).LIMITE_CREDITOS);
			controlador.addCadeiraNoPeriodo(1, new Disciplina("OPTATIVA 2",4));
			fail();
		} catch (LimitesExcedidosException e) {
			assertTrue(e instanceof LimitesExcedidosException); 
			// dumb ain't it?
		}		
	}
	
	@Test
	public void realocarCadeira() throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException{
		Disciplina discreta = new Disciplina("DISCRETA");
		Disciplina  prob = new Disciplina("PROBABILIDADE");
		Disciplina calcII = new Disciplina("CALC II");
		
		
		controlador.addCadeiraNoPeriodo(1, calcII);
		controlador.addCadeiraNoPeriodo(2, prob);
		controlador.addCadeiraNoPeriodo(1,discreta);
		assertTrue(controlador.getPeriodo(1).getDisciplinas().contains(controlador.getCadeira("DISCRETA")));
		
		controlador.realocaCadeiras(1, 2, discreta);
		assertFalse(controlador.getPeriodo(1).contains(controlador.getCadeira("DISCRETA")));
		assertTrue(controlador.getPeriodo(2).contains(controlador.getCadeira("DISCRETA")));
	}
	
	@Test
	public void testUser(){
		
		assertEquals("teste@teste", user.getEmail());
		assertEquals("teste", user.getNome());
		
		assertTrue(user.getPlano().equals(controlador));
		
	}
	
}
