package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model.Finder;

@Entity
public class CatalogoDisciplinas {

	// CREATOR: Nessa classe são criadas as disciplinas, pois
	// CatalogoDeDisciplinas contém todas as disciplinas do sistema.
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Disciplina> disciplinas;

	public CatalogoDisciplinas() {
		disciplinas = new ArrayList<Disciplina>();
		preencherCadeiras();
	}

	/**
	 * Preenche as disciplinas ofertadas
	 */
	public void preencherCadeiras() {
		// adicionar cadeiras...
		// String cadeira, int creditos, int dificuldade, int periodoDefault
		// cadeiras 1 periodo
		addCadeira("Calculo I", 4, 5, 0);
		addCadeira("Vetorial", 4, 4, 0);
		addCadeira("LPT", 4, 2, 0);
		addCadeira("Programacao I", 4, 3, 0);
		addCadeira("Lab Programacao I", 4, 3, 0);
		addCadeira("Introducao a Computacao", 4, 3, 0);

		// cadeiras 2 periodo
		addCadeira("Programacao II", 4, 4, 1);
		addCadeira("Lab Programacao II", 4, 4, 1);
		addCadeira("Teoria dos Grafos", 2, 3, 1);
		addCadeira("Calculo II", 4, 5, 1);
		addCadeira("Matematica Discreta", 4, 4, 1);
		addCadeira("Metodologia Cientifica", 4, 3, 1);
		addCadeira("Fund Fisica Classica", 4, 4, 1);

		// cadeiras 3 periodo
		addCadeira("Estrutura de Dados", 4, 5, 2);
		addCadeira("Lab Estrutura de Dados", 4, 5, 2);
		addCadeira("Algebra Linear", 4, 5, 2);
		addCadeira("Probabilidade e Estat.", 4, 5, 2);
		addCadeira("Teoria da Computacao", 4, 3, 2);
		addCadeira("Fund Fisica Moderna", 4, 5, 2);
		addCadeira("Gerencia da Informacao", 4, 4, 2);

		// cadeiras 4 periodo
		addCadeira("Org Arquitetura de Computadores", 4, 4, 3);
		addCadeira("Lab Org Arquitetura de Computadores", 4, 4, 3);
		addCadeira("Paradigmas de Ling de Prog", 2, 4, 3);
		addCadeira("Metodos Estatisticos", 4, 5, 3);
		addCadeira("Logica Matematica", 4, 5, 3);
		addCadeira("Sistemas da Informacao I", 4, 4, 3);
		addCadeira("Eng de Software I", 4, 4, 3);

		// cadeiras 5 periodo
		addCadeira("Analise Tec de Algoritmos", 4, 4, 4);
		addCadeira("Redes de Computadores", 4, 4, 4);
		addCadeira("Compiladores", 4, 5, 4);
		addCadeira("Banco de Dados I", 4, 4, 4);
		addCadeira("Sistemas da Informacao II", 4, 4, 4);
		addCadeira("Lab Eng de Software", 2, 4, 4);
		addCadeira("Informatica e Sociedade", 2, 2, 4);

		// cadeiras 6 periodo
		addCadeira("Sistemas Operacionais", 4, 4, 5);
		addCadeira("Interconexao de Redes de Comp.", 2, 4, 5);
		addCadeira("Lab Interconexao de Redes de Comp.", 2, 4, 5);
		addCadeira("Inteligencia Artificial", 4, 4, 5);
		addCadeira("Banco de Dados II", 4, 4, 5);
		addCadeira("Optativa 1", 4, 3, 5);
		addCadeira("Optatiiva 2", 4, 3, 5);
		addCadeira("Direito e Cidadania", 4, 2, 5);

		// cadeiras 7 periodo
		addCadeira("Metodos e Software Num", 4, 4, 6);
		addCadeira("Av. de Desempenho de Sist. Discretos", 4, 5, 6);
		addCadeira("Optativa 3", 4, 3, 6);
		addCadeira("Optativa 4", 4, 3, 6);
		addCadeira("Optativa 5", 4, 3, 6);
		addCadeira("Projeto I", 4, 5, 6);
		addCadeira("Optativa 6", 4, 3, 6);

		// cadeiras 8 periodo
		addCadeira("Projeto II", 6, 5, 7);
		addCadeira("Optativa 7", 4, 3, 7);
		addCadeira("Optativa 8", 4, 3, 7);
		addCadeira("Optativa 9", 4, 3, 7);
		addCadeira("Optativa 10", 4, 3, 7);
		addCadeira("Optativa 11", 4, 3, 7);
//
//		// adicionar requisitos
//
		// 2 periodo
		getCadeira("Calculo II").addRequisitos(getCadeira("Calculo I"));
		getCadeira("Programacao II").addRequisitos(getCadeira("Programacao I"));
		getCadeira("Programacao II").addRequisitos(
				getCadeira("Lab Programacao I"));
		getCadeira("Programacao II").addRequisitos(
				getCadeira("Introducao a Computacao"));
		getCadeira("Lab Programacao II").addListaRequisitos(
				getCadeira("Programacao II").getRequisitos());
		getCadeira("Teoria dos Grafos").addRequisitos(
				getCadeira("Programacao I"));
		getCadeira("Teoria dos Grafos").addRequisitos(
				getCadeira("Lab Programacao I"));
		getCadeira("Fund Fisica Moderna")
				.addRequisitos(getCadeira("Calculo I"));
		getCadeira("Fund Fisica Moderna").addRequisitos(getCadeira("Vetorial"));

		// 3 periodo
		getCadeira("Estrutura de Dados").addRequisitos(
				getCadeira("Programacao II"));
		getCadeira("Estrutura de Dados").addRequisitos(
				getCadeira("Lab Programacao II"));
		getCadeira("Estrutura de Dados").addRequisitos(
				getCadeira("Teoria dos Grafos"));
		getCadeira("Lab Estrutura de Dados").addListaRequisitos(
				getCadeira("Estrutura de Dados").getRequisitos());
		getCadeira("Algebra Linear").addRequisitos(getCadeira("Vetorial"));
		getCadeira("Probabilidade e Estat.").addRequisitos(
				getCadeira("Calculo II"));
		getCadeira("Teoria da Computacao").addRequisitos(
				getCadeira("Teoria dos Grafos"));
		getCadeira("Teoria da Computacao").addRequisitos(
				getCadeira("Matematica Discreta"));
		getCadeira("Teoria da Computacao").addRequisitos(
				getCadeira("Introducao a Computacao"));
		getCadeira("Fund Fisica Moderna").addRequisitos(
				getCadeira("Fund Fisica Classica"));
		getCadeira("Fund Fisica Moderna").addRequisitos(
				getCadeira("Calculo II"));

		// 4 periodo
		getCadeira("Org Arquitetura de Computadores").addRequisitos(
				getCadeira("Estrutura de Dados"));
		getCadeira("Org Arquitetura de Computadores").addRequisitos(
				getCadeira("Lab Estrutura de Dados"));
		getCadeira("Org Arquitetura de Computadores").addRequisitos(
				getCadeira("Fund Fisica Moderna"));
		getCadeira("Lab Org Arquitetura de Computadores").addListaRequisitos(
				getCadeira("Org Arquitetura de Computadores").getRequisitos());
		getCadeira("Paradigmas de Ling de Prog").addRequisitos(
				getCadeira("Estrutura de Dados"));
		getCadeira("Paradigmas de Ling de Prog").addRequisitos(
				getCadeira("Lab Estrutura de Dados"));
		getCadeira("Paradigmas de Ling de Prog").addRequisitos(
				getCadeira("Teoria da Computacao"));
		getCadeira("Metodos Estatisticos").addRequisitos(
				getCadeira("Probabilidade e Estat."));
		getCadeira("Metodos Estatisticos").addRequisitos(
				getCadeira("Algebra Linear"));
		getCadeira("Logica Matematica").addRequisitos(
				getCadeira("Teoria da Computacao"));
		getCadeira("Sistemas da Informacao I").addRequisitos(getCadeira("Gerencia da Informacao"));
		getCadeira("Eng de Software I").addRequisitos(
				getCadeira("Probabilidade e Estat."));
		getCadeira("Eng de Software I").addRequisitos(
				getCadeira("Programacao II"));
		getCadeira("Eng de Software I").addRequisitos(
				getCadeira("Lab Programacao II"));

//		// 5 periodo
		getCadeira("Analise Tec de Algoritmos").addRequisitos(
				getCadeira("Estrutura de Dados"));
		getCadeira("Analise Tec de Algoritmos").addRequisitos(
				getCadeira("Lab Estrutura de Dados"));
		getCadeira("Analise Tec de Algoritmos").addRequisitos(
				getCadeira("Calculo II"));
		getCadeira("Analise Tec de Algoritmos").addRequisitos(
				getCadeira("Logica Matematica"));
		getCadeira("Redes de Computadores").addRequisitos(
				getCadeira("Org Arquitetura de Computadores"));
		getCadeira("Redes de Computadores").addRequisitos(
				getCadeira("Lab Org Arquitetura de Computadores"));
		getCadeira("Compiladores").addListaRequisitos(
				getCadeira("Redes de Computadores").getRequisitos());
		getCadeira("Compiladores").addRequisitos(
				getCadeira("Paradigmas de Ling de Prog"));
		getCadeira("Banco de Dados I").addRequisitos(
				getCadeira("Sistemas da Informacao I"));
		getCadeira("Sistemas da Informacao II").addRequisitos(
				getCadeira("Sistemas da Informacao I"));
		getCadeira("Lab Eng de Software").addRequisitos(
				getCadeira("Eng de Software I"));

		// 6 periodo
		getCadeira("Sistemas Operacionais").addListaRequisitos(
				getCadeira("Redes de Computadores").getRequisitos());
		getCadeira("Interconexao de Redes de Comp.").addRequisitos(
				getCadeira("Redes de Computadores"));
		getCadeira("Lab Interconexao de Redes de Comp.").addRequisitos(
				getCadeira("Redes de Computadores"));
		getCadeira("Inteligencia Artificial").addRequisitos(
				getCadeira("Metodos Estatisticos"));
		getCadeira("Inteligencia Artificial").addRequisitos(
				getCadeira("Paradigmas de Ling de Prog"));
		getCadeira("Inteligencia Artificial").addRequisitos(
				getCadeira("Analise Tec de Algoritmos"));
		getCadeira("Banco de Dados II").addRequisitos(
				getCadeira("Banco de Dados I"));
		getCadeira("Banco de Dados II").addRequisitos(
				getCadeira("Sistemas da Informacao II"));

//		// 7 periodo
		getCadeira("Metodos e Software Num").addRequisitos(
				getCadeira("Analise Tec de Algoritmos"));
		getCadeira("Metodos e Software Num").addRequisitos(
				getCadeira("Algebra Linear"));
		getCadeira("Av. de Desempenho de Sist. Discretos").addRequisitos(
				getCadeira("Probabilidade e Estat."));
		getCadeira("Projeto I")
				.addRequisitos(getCadeira("Lab Eng de Software"));
		getCadeira("Projeto I").addRequisitos(
				getCadeira("Metodologia Cientifica"));

//		// 8 periodo
		getCadeira("Projeto II").addRequisitos(getCadeira("Projeto I"));
	}

	/**
	 * Retorna uma Disciplina, achando ela pelo nome. Null caso a disciplina não
	 * seja encontrada.
	 * 
	 * @param String
	 *            contendo o nome da cadeira a ser pesquisada
	 * @return Objeto do tipo Disciplina. Null caso nada seja encontrado.
	 */
	public Disciplina getCadeira(String string) {
		for (Disciplina d : disciplinas) {
			if (d.getNomeCadeira().trim().toLowerCase()
					.equals(string.trim().toLowerCase())) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Adiciona uma nova cadeira aos requisitos
	 * 
	 * @param nome
	 * @param creditos
	 * @param requisitos
	 */
	public void addCadeira(String nome, int creditos) {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Disciplina novaCadeira = new Disciplina(nome, creditos);
		if (!disciplinas.contains(novaCadeira)) {
			disciplinas.add(novaCadeira);
			for (Disciplina d : requisitos) {
				novaCadeira.addRequisitos(d);
			}
		}
	}

	public void addCadeira(String nome, int creditos, int dificuldade,
			int periodo) {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Disciplina novaCadeira = new Disciplina(nome, creditos, dificuldade,
				periodo);
		if (!disciplinas.contains(novaCadeira)) {
			disciplinas.add(novaCadeira);
			for (Disciplina d : requisitos) {
				novaCadeira.addRequisitos(d);
			}
		}
	}

	/**
	 * Retorna o array com todas as disciplinas
	 * 
	 * @return
	 */
	public List<Disciplina> getCadeiras() {
		return disciplinas;
	}
	
	public Long getId(){
		return id;
	}
	
	public static Finder<Long,CatalogoDisciplinas> find = new Finder<Long,CatalogoDisciplinas>(
		    Long.class, CatalogoDisciplinas.class
	); 

}
