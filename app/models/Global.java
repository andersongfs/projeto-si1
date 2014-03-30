package models;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings{
	
	private int NUMERO_PERIODOS = 8;
	private final int NUMERO_DE_USUARIOS_PADRAO = 30;

	public Logger log = new Logger();
	@Override
	public void onStart(Application app){
		povoaBd();
		log.info("Disciplinas Criadas - BD Povoado");
		if(Usuario.find.findRowCount() == 0){
			Usuario admin = new Usuario("admin@admin", "admin", "admin", new PlanoDeCurso());
			admin.save();
			criaTrintaUsuarios();
		}
	}
	
	public void povoaBd(){
			try {
				criaDisciplina("Calculo I", 4, 5, 0);
				criaDisciplina("Vetorial", 4, 4, 0);
				criaDisciplina("LPT", 4, 2, 0);
				criaDisciplina("Programacao I", 4, 3, 0);
				criaDisciplina("Lab Programacao I", 4, 3, 0);
				criaDisciplina("Introducao a Computacao", 4, 3, 0);

				// cadeiras 2 periodo
				criaDisciplina("Programacao II", 4, 4, 1);
				criaDisciplina("Lab Programacao II", 4, 4, 1);
				criaDisciplina("Teoria dos Grafos", 2, 3, 1);
				criaDisciplina("Calculo II", 4, 5, 1);
				criaDisciplina("Matematica Discreta", 4, 4, 1);
				criaDisciplina("Metodologia Cientifica", 4, 3, 1);
				criaDisciplina("Fund Fisica Classica", 4, 4, 1);

				// cadeiras 3 periodo
				criaDisciplina("Estrutura de Dados", 4, 5, 2);
				criaDisciplina("Lab Estrutura de Dados", 4, 5, 2);
				criaDisciplina("Algebra Linear", 4, 5, 2);
				criaDisciplina("Probabilidade e Estat.", 4, 5, 2);
				criaDisciplina("Teoria da Computacao", 4, 3, 2);
				criaDisciplina("Fund Fisica Moderna", 4, 5, 2);
				criaDisciplina("Gerencia da Informacao", 4, 4, 2);

				// cadeiras 4 periodo
				criaDisciplina("Org Arquitetura de Computadores", 4, 4, 3);
				criaDisciplina("Lab Org Arquitetura de Computadores", 4, 4, 3);
				criaDisciplina("Paradigmas de Ling de Prog", 2, 4, 3);
				criaDisciplina("Metodos Estatisticos", 4, 5, 3);
				criaDisciplina("Logica Matematica", 4, 5, 3);
				criaDisciplina("Sistemas da Informacao I", 4, 4, 3);
				criaDisciplina("Eng de Software I", 4, 4, 3);

				// cadeiras 5 periodo
				criaDisciplina("Analise Tec de Algoritmos", 4, 4, 4);
				criaDisciplina("Redes de Computadores", 4, 4, 4);
				criaDisciplina("Compiladores", 4, 5, 4);
				criaDisciplina("Banco de Dados I", 4, 4, 4);
				criaDisciplina("Sistemas da Informacao II", 4, 4, 4);
				criaDisciplina("Lab Eng de Software", 2, 4, 4);
				criaDisciplina("Informatica e Sociedade", 2, 2, 4);

				// cadeiras 6 periodo
				criaDisciplina("Sistemas Operacionais", 4, 4, 5);
				criaDisciplina("Interconexao de Redes de Comp.", 2, 4, 5);
				criaDisciplina("Lab Interconexao de Redes de Comp.", 2, 4, 5);
				criaDisciplina("Inteligencia Artificial", 4, 4, 5);
				criaDisciplina("Banco de Dados II", 4, 4, 5);
				criaDisciplina("Optativa 1", 4, 3, 5);
				criaDisciplina("Optatiiva 2", 4, 3, 5);
				criaDisciplina("Direito e Cidadania", 4, 2, 5);

				// cadeiras 7 periodo
				criaDisciplina("Metodos e Software Num", 4, 4, 6);
				criaDisciplina("Av. de Desempenho de Sist. Discretos", 4, 5, 6);
				criaDisciplina("Optativa 3", 4, 3, 6);
				criaDisciplina("Optativa 4", 4, 3, 6);
				criaDisciplina("Optativa 5", 4, 3, 6);
				criaDisciplina("Projeto I", 4, 5, 6);
				criaDisciplina("Optativa 6", 4, 3, 6);

				// cadeiras 8 periodo
				criaDisciplina("Projeto II", 6, 5, 7);
				criaDisciplina("Optativa 7", 4, 3, 7);
				criaDisciplina("Optativa 8", 4, 3, 7);
				criaDisciplina("Optativa 9", 4, 3, 7);
				criaDisciplina("Optativa 10", 4, 3, 7);
				criaDisciplina("Optativa 11", 4, 3, 7);
		//
//				// adicionar requisitos
		//
				// 2 periodo
				addRequisitos("Calculo II","Calculo I");
				addRequisitos("Programacao II","Programacao I");
				addRequisitos("Programacao II","Lab Programacao I");
				addRequisitos("Programacao II", "Introducao a Computacao");
				addListaRequisitos("Lab Programacao II", getDisciplina("Programacao II").getRequisitos());
				addRequisitos("Teoria dos Grafos", "Programacao I");
				addRequisitos("Teoria dos Grafos", "Lab Programacao I");
				addRequisitos("Fund Fisica Classica", "Calculo I");
				addRequisitos("Fund Fisica Classica", "Vetorial");
				// 3 periodo
				addRequisitos("Estrutura de Dados", "Programacao II");
				addRequisitos("Estrutura de Dados", "Lab Programacao II");
				addRequisitos("Estrutura de Dados", "Teoria dos Grafos");
				addListaRequisitos("Lab Estrutura de Dados", getDisciplina("Estrutura de Dados").getRequisitos());
				addRequisitos("Algebra Linear", "Vetorial");
				addRequisitos("Probabilidade e Estat.", "Calculo II");
				addRequisitos("Teoria da Computacao", "Teoria dos Grafos");
				addRequisitos("Teoria da Computacao", "Matematica Discreta");
				addRequisitos("Teoria da Computacao", "Introducao a Computacao");
				addRequisitos("Fund Fisica Moderna", "Fund Fisica Classica");
				addRequisitos("Fund Fisica Moderna", "Calculo II");
				// 4 periodo
				addRequisitos("Org Arquitetura de Computadores", "Estrutura de Dados");
				addRequisitos("Org Arquitetura de Computadores", "Lab Estrutura de Dados");
				addRequisitos("Org Arquitetura de Computadores", "Fund Fisica Moderna");
				addListaRequisitos("Lab Org Arquitetura de Computadores", getDisciplina("Org Arquitetura de Computadores").getRequisitos());
				addRequisitos("Paradigmas de Ling de Prog", "Estrutura de Dados");
				addRequisitos("Paradigmas de Ling de Prog", "Lab Estrutura de Dados");
				addRequisitos("Paradigmas de Ling de Prog", "Teoria da Computacao");
				addRequisitos("Metodos Estatisticos", "Probabilidade e Estat.");
				addRequisitos("Metodos Estatisticos", "Algebra Linear");
				addRequisitos("Logica Matematica", "Teoria da Computacao");
				addRequisitos("Sistemas da Informacao I", "Gerencia da Informacao");
				addRequisitos("Eng de Software I", "Probabilidade e Estat.");
				addRequisitos("Eng de Software I", "Programacao II");
				addRequisitos("Eng de Software I", "Lab Programacao II");

//				// 5 periodo
				addRequisitos("Analise Tec de Algoritmos", "Estrutura de Dados");
				addRequisitos("Analise Tec de Algoritmos", "Lab Estrutura de Dados");
				addRequisitos("Analise Tec de Algoritmos", "Calculo II");
				addRequisitos("Analise Tec de Algoritmos", "Logica Matematica");
				addRequisitos("Redes de Computadores", "Org Arquitetura de Computadores");
				addRequisitos("Redes de Computadores", "Lab Org Arquitetura de Computadores");
				addListaRequisitos("Compiladores", getDisciplina("Redes de Computadores").getRequisitos());
				addRequisitos("Compiladores", "Paradigmas de Ling de Prog");
				addRequisitos("Banco de Dados I", "Sistemas da Informacao I");
				addRequisitos("Sistemas da Informacao II", "Sistemas da Informacao I");
				addRequisitos("Lab Eng de Software", "Eng de Software I");

				// 6 periodo
				addListaRequisitos("Sistemas Operacionais", getDisciplina("Redes de Computadores").getRequisitos());
				addRequisitos("Interconexao de Redes de Comp.", "Redes de Computadores");
				addRequisitos("Lab Interconexao de Redes de Comp.", "Redes de Computadores");
				addRequisitos("Inteligencia Artificial", "Metodos Estatisticos");
				addRequisitos("Inteligencia Artificial", "Paradigmas de Ling de Prog");
				addRequisitos("Inteligencia Artificial", "Analise Tec de Algoritmos");
				addRequisitos("Banco de Dados II", "Banco de Dados I");
				addRequisitos("Banco de Dados II", "Sistemas da Informacao II");

//				// 7 periodo
				addRequisitos("Metodos e Software Num", "Analise Tec de Algoritmos");
				addRequisitos("Metodos e Software Num", "Algebra Linear");
				addRequisitos("Av. de Desempenho de Sist. Discretos", "Probabilidade e Estat.");
				addRequisitos("Projeto I", "Lab Eng de Software");
				addRequisitos("Projeto I", "Metodologia Cientifica");

//				// 8 periodo
				addRequisitos("Projeto II", "Projeto I");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	private Disciplina getDisciplina(String nome) {
		return Disciplina.find.where().eq("nomeCadeira", nome).findUnique();
	}
	
	private void addRequisitos(String disciplina, String requisito){
		Disciplina d = getDisciplina(disciplina);
		d.addRequisitos(getDisciplina(requisito));
		d.update();
	}
	
	private void addListaRequisitos(String disciplina, List<Disciplina> requisitos){
		Disciplina d = getDisciplina(disciplina);
		for (Disciplina req : requisitos) {
			d.addRequisitos(req);
		}
		d.update();
	}

	
	private void criaDisciplina(String nome, int creditos, int dificuldade, int periodoPadrao) {
		Disciplina d = new Disciplina(nome,creditos,dificuldade,periodoPadrao);
		d.save();
	}
	
	private void criaTrintaUsuarios(){
		log.info("Usuarios criados");
		for (int i = 0; i < NUMERO_DE_USUARIOS_PADRAO; i++ ){
			Usuario user = new Usuario("user"+i+"@email.com" , "user"+i, "user"+i, new PlanoDeCurso());
			user.save();
		}
	}
}
