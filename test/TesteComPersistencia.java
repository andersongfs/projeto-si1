import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.List;

import javax.validation.constraints.AssertTrue;

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
		
		
		//Removendo cadeira
		p2.removeCadeira(1, p2.getCadeira("Programacao II").getNomeCadeira());
		//Cadeira contida na lista de disciplinas disponiveis
		Assert.assertTrue(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Programacao II")));
		//Tamanho da lista contendo programacao II + dependencias.
		Assert.assertTrue(p2.getCadeirasDisponiveis().size() == 18); // programacao II e as cadeiras que ela é pré requisito.
		//Cadeiras que são dependências
		Assert.assertTrue(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Estrutura de Dados")));
		Assert.assertTrue(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Lab Estrutura de Dados")));
		
		//Assert.assertEquals(false, p2.getCadeira("Programacao II").isAlocada()); Precisamos resolver esse problema!
		
		
		//Adicionando Cadeira
		p2.adicionaCadeira(1,p2.getCadeira("Programacao II").getNomeCadeira());
		
		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Programacao II")));
		
		//Adicionando nos periodos posteriores
		p2.adicionaCadeira(2,p2.getCadeira("Estrutura de Dados").getNomeCadeira());
		p2.adicionaCadeira(2,p2.getCadeira("Lab Estrutura de Dados").getNomeCadeira());
		
		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Estrutura de Dados")));
		Assert.assertFalse(p2.getCadeirasDisponiveis().contains(p2.getCadeira("Lab Estrutura de Dados")));
		
		
		
		//Realocar
		PlanoDeCurso p3 = PlanoDeCurso.find.findUnique();
		p3.realocaCadeiras(1,4, p3.getCadeira("Programacao II").getNomeCadeira());
		// Verifica se a disciplina esta contida no periodo que foi realocada
		Assert.assertTrue(p3.getPeriodo(4).getDisciplinas().contains(p3.getCadeira("Programacao II")));
		//Verifica se a disciplina não foi duplica e realmente nao esta mais no antigo periodo
		Assert.assertFalse(p3.getPeriodo(1).getDisciplinas().contains(p3.getCadeira("Programacao II")));
		//Voltar a cadeira pra o perido original
		p3.realocaCadeiras(4,1, p3.getCadeira("Programacao II").getNomeCadeira());
		Assert.assertTrue(p3.getPeriodo(1).getDisciplinas().contains(p3.getCadeira("Programacao II")));
		Assert.assertFalse(p3.getPeriodo(4).getDisciplinas().contains(p3.getCadeira("Programacao II")));
		
		//Realocar para um perido com o total de creditos lotados
		p3.realocaCadeiras(1,2, p3.getCadeira("Programacao II").getNomeCadeira());
		//Ela deve fica no mesmo periodo
		Assert.assertTrue(p3.getPeriodo(1).getDisciplinas().contains(p3.getCadeira("Programacao II")));
		
	}
	

}
