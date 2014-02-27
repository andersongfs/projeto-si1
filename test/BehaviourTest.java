import static org.junit.Assert.*;

import models.Disciplina;
import models.JaContemDisciplinaException;
import models.LimitesExcedidosException;
import models.PlanoDeCurso;
import models.PrerequisitosInsuficientesException;
import org.junit.*;

//Comentario Geral: Diante desse teste, não é nescessário fazer uma classe de teste apenas para o PLano de Curso
//tendo em vista que os outros metodos de plano de curso ja foram testados em TestaPeriodo.
public class BehaviourTest {

	private PlanoDeCurso controlador;
	@Before
	public void setUp(){
		controlador = new PlanoDeCurso();
	}
	
	
	@Test
	public void adicionarCadeira() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		controlador.adicionaCadeira(1, "CALC II");
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
	}
	
	@Test
	public void testaAdicionarCadeiraQueTemPrerequisito(){
		try { 
			controlador.adicionaCadeira(1, "FFM");
			fail();
		} catch (Exception e) { 
			assertTrue(e instanceof PrerequisitosInsuficientesException );
		}
	}
	
	@Test 
	public void removerCadeiraSemDependentes() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		controlador.adicionaCadeira(1, "CALC II");
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
		controlador.removeCadeira(1, "CALC II");
		assertTrue(!controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
	}
	
	@Test
	public void removerCadeiraComDependentes() throws LimitesExcedidosException, PrerequisitosInsuficientesException, JaContemDisciplinaException{
		// Perceba que o teste é quase redundante ao lado do teste acima. 
		// Se remover uma cadeira com dependente, também removerá uma sem. 
		// Então a redundância é "para aumentar a qualidade do teste."
		controlador.adicionaCadeira(1, "CALC II");
		controlador.adicionaCadeira(2, "PROBABILIDADE");
		assertTrue(controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
		assertTrue(controlador.getPeriodo(2).contains(new Disciplina("PROBABILIDADE", 4)));
		controlador.removeCadeira(1, "CALC II");
		assertTrue(!controlador.getPeriodo(2).contains(new Disciplina("PROBABILIDADE", 4)));
		assertTrue(!controlador.getPeriodo(1).contains(new Disciplina("CALC II", 4)));
			
	}
	
	@Test
	public void estourarCreditosDoPeriodo() throws JaContemDisciplinaException {
		try {
			controlador.adicionaCadeira(1, "CALC II");
			controlador.adicionaCadeira(1, "LPROG2");
			controlador.adicionaCadeira(1, "PROG2");
			controlador.adicionaCadeira(1, "DISCRETA");
			controlador.adicionaCadeira(1, "FFC");
			controlador.adicionaCadeira(1, "LINEAR");
			controlador.adicionaCadeira(1, "OPTATIVA 1");
			assertEquals(controlador.getPeriodo(1).creditosTotal(), controlador.getPeriodo(1).LIMITE_CREDITOS);
			controlador.adicionaCadeira(1, "OPTATIVA 2");
			fail();
		} catch (LimitesExcedidosException e) {
			assertTrue(e instanceof LimitesExcedidosException); 
			// dumb ain't it?
		} catch (PrerequisitosInsuficientesException pie){
			fail();
		}		
	}
}
