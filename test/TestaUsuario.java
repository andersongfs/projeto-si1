import static org.junit.Assert.*;

import models.PlanoDeCurso;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;


public class TestaUsuario {
	
	private static PlanoDeCurso planoDeCurso1;
	private static PlanoDeCurso planoDeCurso2;
	private static PlanoDeCurso planoDeCurso3;
	private static PlanoDeCurso planoDeCurso4;
	private static PlanoDeCurso planoDeCurso5;
	
	private static Usuario usuario1;
	private static Usuario usuario2;
	private static Usuario usuario3;
	private static Usuario usuario4;
	private static Usuario usuario5;
	
	@Before
	public void setUp() {
		planoDeCurso1 = new PlanoDeCurso();
		planoDeCurso2 = new PlanoDeCurso();
		planoDeCurso3 = new PlanoDeCurso();
		planoDeCurso4 = new PlanoDeCurso();
		planoDeCurso5 = new PlanoDeCurso();
		inicializaUsuarios();
		inicializaPlanosDeCurso();
	}

	@Test
	public void testGetNome() {
		assertEquals(usuario1.getNome(), "Allyson Soares");
		assertEquals(usuario2.getNome(), "Anderson Gustafson");
		assertEquals(usuario3.getNome(), "Guilherme Pimentel");
		assertEquals(usuario4.getNome(), "Nathan Martins");
		assertEquals(usuario5.getNome(), "Tiago Gonçalves");
	}

	@Test
	public void testSetNome() {
		String novoNome1 = "José";
		usuario1.setNome(novoNome1);
		assertEquals(usuario1.getNome(), novoNome1);
		
		String novoNome2 = "Antônio";
		usuario2.setNome(novoNome2);
		assertEquals(usuario2.getNome(), novoNome2);
		
		String novoNome3 = "João";
		usuario3.setNome(novoNome3);
		assertEquals(usuario3.getNome(), novoNome3);
		
		String novoNome4 = "Biu";
		usuario4.setNome(novoNome4);
		assertEquals(usuario4.getNome(), novoNome4);
		
		String novoNome5 = novoNome1;
		usuario5.setNome(novoNome5);
		assertEquals(usuario5.getNome(), novoNome5);
		assertEquals(usuario5.getNome(), usuario1.getNome());
	}

	@Test
	public void testGetEmail() {
		assertEquals(usuario1.getEmail(), "allysonss@gmail.com");
		assertEquals(usuario2.getEmail(), "andersongfs@gmail.com");
		assertEquals(usuario3.getEmail(), "guilherme.pimentel@gmail.com");
		assertEquals(usuario4.getEmail(), "nathan.martins@gmail.com");
		assertEquals(usuario5.getEmail(), "tiago.goncalves@gmail.com");
	}

	@Test
	public void testSetEmail() {
		String novoEmail1 = "jose@gmail.com";
		usuario1.setEmail(novoEmail1);
		assertEquals(usuario1.getEmail(), novoEmail1);
		
		String novoEmail2 = "antonio@gmail.com";
		usuario2.setEmail(novoEmail2);
		assertEquals(usuario2.getEmail(), novoEmail2);
		
		String novoEmail3 = "joao@gmail.com";
		usuario3.setEmail(novoEmail3);
		assertEquals(usuario3.getEmail(), novoEmail3);
		
		String novoEmail4 = "biu@gmail.com";
		usuario4.setEmail(novoEmail4);
		assertEquals(usuario4.getEmail(), novoEmail4);
		
		String novoEmail5 = "severino@gmail.com";
		usuario5.setEmail(novoEmail5);
		assertEquals(usuario5.getEmail(), novoEmail5);
	}

	@Test
	public void testGetPlano() {
		assertEquals(usuario1.getPlano(), planoDeCurso1);
		assertEquals(usuario2.getPlano(), planoDeCurso2);
		assertEquals(usuario3.getPlano(), planoDeCurso3);
		assertEquals(usuario4.getPlano(), planoDeCurso4);
		assertEquals(usuario5.getPlano(), planoDeCurso5);
	}

	@Test
	public void testSetPlano() {
		PlanoDeCurso novoPlano1 = new PlanoDeCurso();
		usuario1.setPlano(novoPlano1);
		assertEquals(usuario1.getPlano(), novoPlano1);
		
		PlanoDeCurso novoPlano2 = new PlanoDeCurso();
		usuario2.setPlano(novoPlano2);
		assertEquals(usuario2.getPlano(), novoPlano2);
		
		PlanoDeCurso novoPlano3 = new PlanoDeCurso();
		usuario3.setPlano(novoPlano3);
		assertEquals(usuario3.getPlano(), novoPlano3);
		
		PlanoDeCurso novoPlano4 = new PlanoDeCurso();
		usuario4.setPlano(novoPlano4);
		assertEquals(usuario4.getPlano(), novoPlano4);
		
		PlanoDeCurso novoPlano5 = new PlanoDeCurso();
		usuario5.setPlano(novoPlano5);
		assertEquals(usuario5.getPlano(), novoPlano5);
	}

	private void inicializaUsuarios() {
		usuario1 = new Usuario("allysonss@gmail.com", "Allyson Soares", "123132", planoDeCurso1);
		usuario2 = new Usuario("andersongfs@gmail.com", "Anderson Gustafson", "123456", planoDeCurso2);
		usuario3 = new Usuario("guilherme.pimentel@gmail.com", "Guilherme Pimentel", "654321", planoDeCurso3);
		usuario4 = new Usuario("nathan.martins@gmail.com", "Nathan Martins", "123321", planoDeCurso4);
		usuario5 = new Usuario("tiago.goncalves@gmail.com", "Tiago Gonçalves", "321123", planoDeCurso5);
	}
	
	private void inicializaPlanosDeCurso() {
		
		// 3 Usuários com o plano e velho e 2 usuários com o plano novo.
		
		PlanoDeCurso plano1 = usuario1.getPlano();
		plano1.setPreenchedor(1);
		plano1.povoaPlano();
		
		PlanoDeCurso plano2 = usuario2.getPlano();
		plano2.setPreenchedor(1);
		plano2.povoaPlano();
		
		PlanoDeCurso plano3 = usuario3.getPlano();
		plano3.setPreenchedor(1);
		plano3.povoaPlano();
		
		PlanoDeCurso plano4 = usuario4.getPlano();
		plano4.setPreenchedor(2);
		plano4.povoaPlano();
		
		PlanoDeCurso plano5 = usuario5.getPlano();
		plano5.setPreenchedor(2);
		plano5.povoaPlano();
	}
	
}
