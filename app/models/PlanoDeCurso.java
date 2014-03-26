package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

import com.avaje.ebean.Ebean;

@Entity
public class PlanoDeCurso extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "plano_periodo")
	private List<Periodo> periodos;
	final int NUMERO_PERIODOS = 8;

	// CREATOR: PlanoDeCurso faz uso direto do CatalogoDeDisciplina
//	@ManyToOne
//	private CatalogoDisciplinas catalogoDeDisciplinas;
	
	/*
	 * INFORMATION EXPERT: PlanoDeCurso usa as disciplinas não alocadas para
	 * decidir quem pode ser alocada, então ela precisa conhecer as disciplinas.
	 */
	@ManyToMany
	@JoinTable(name = "disciplinas_disponiveis")
	private List<Disciplina> disciplinasNaoAlocadas;

	public PlanoDeCurso() {
		//catalogoDeDisciplinas = catalogo;
		periodos = new ArrayList<Periodo>();
		disciplinasNaoAlocadas = new ArrayList<Disciplina>();
		inicializarPeriodos();
	//	popularDisciplinasNaoAlocadas();
	}

	/**
	 * Popula períodos vazios e adiciona disciplinas obrigatórias no primeiro
	 * período.
	 */
	private void inicializarPeriodos() {
		for (int i = 0; i < NUMERO_PERIODOS; i++) {
			periodos.add(new Periodo());
		}
		try {
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
//			// adicionar requisitos
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

//			// 5 periodo
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

//			// 7 periodo
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

//			// 8 periodo
			getCadeira("Projeto II").addRequisitos(getCadeira("Projeto I"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna um Periodo
	 * 
	 * @param i
	 * @return
	 */
	public Periodo getPeriodo(int i) {
		return periodos.get(i);
	}

	/**
	 * Retorna a lista com todos os periodos pertencentes ao usuário.
	 * 
	 * @return
	 */
	public List<Periodo> getPeriodos() {
		return periodos;
	}
	
	
	private void addCadeira(String nome, int creditos, int dificuldade, int periodo) throws LimitesExcedidosException, JaContemDisciplinaException {
		Disciplina d = new Disciplina(nome,creditos,dificuldade,periodo); 
		addCadeiraNoPeriodo(periodo, d);
	}
	/**
	 * Adiciona uma cadeira num determinado periodo.
	 * 
	 * @param p
	 *            Periodo de 0...n-1
	 * @param d
	 *            Disciplina a ser adicionada
	 * @throws LimitesExcedidosException
	 * @throws JaContemDisciplinaException
	 */
	public void addCadeiraNoPeriodo(int p, Disciplina d)
			throws LimitesExcedidosException, JaContemDisciplinaException {
		getPeriodo(p).addCadeira(d);
		if (disciplinasNaoAlocadas.contains(d)) {
			disciplinasNaoAlocadas.remove(d);
		}
	}

	/**
	 * Boolean informando se uma dis
	 * 
	 * @param periodo
	 *            Periodo de 0...n-1
	 * @param disciplina
	 *            Disciplina a ser procurada
	 * @return Boolean confirmando se o periodo contém a disciplina
	 */
	public boolean contemDisciplina(int periodo, Disciplina disciplina) {
		return getPeriodo(periodo).contains(disciplina);
	}

	/**
	 * Retorna o número de Disciplinas alocadas para o periodo p.
	 * 
	 * @param p
	 *            Periodo a ser analisado
	 * @return Inteiro contendo o número de disciplinas alocadas
	 */
	public int getNumeroDeDisciplinas(int p) {
		return getPeriodo(p).qtdeCadeiras();
	}

	/**
	 * Retorna o total de créditos alocados para um periodo p
	 * 
	 * @param p
	 *            Periodo a ser analisado
	 * @return Inteiro contendo o número de créditos do periodo.
	 */
	public int getTotalDeCreditos(int p) {
		return getPeriodo(p).creditosTotal();
	}

	/**
	 * @return uma Lista com as Disciplinas disponíveis para o plano de curso.
	 */
	public List<Disciplina> getCadeirasDisponiveis() {
		return disciplinasNaoAlocadas;
	}
	/**
	 * Remove uma cadeira de um determinado periodo.
	 * 
	 * @param periodo
	 *            Inteiro indicando o periodo (de 0...n-1)
	 * @param disciplina
	 *            String contendo a identificação da Disciplina a ser removida
	 */
	public void removeCadeira(int periodo, Long idDisciplina) {
		Disciplina disciplina = Disciplina.find.byId(idDisciplina);
		if (periodo >= 0 && periodo<8) {
			getPeriodo(periodo).removeDisciplina(disciplina);
			disciplinasNaoAlocadas.add(disciplina);
		}

		for (int i = 0; i < NUMERO_PERIODOS; i++) {
			for (int j = 0; j < periodos.get(i).getDisciplinas().size(); j++) {
				Disciplina disciplinaAuxiliar = periodos.get(i).getDisciplinas().get(j);
				if (disciplinaAuxiliar.getRequisitos().contains(disciplina)) {
					periodos.get(i).getDisciplinas().remove(disciplinaAuxiliar);
					j--;
					removeCadeira(i, disciplinaAuxiliar.getId());
				}
			}
		}
	}

	/**
	 * Retorna um boolean mostrando se uma cadeira tem ou não dependentes.
	 * 
	 * @param periodo
	 *            Inteiro com o periodo da cadeira a ser analisada.
	 * @param disciplina
	 *            String com a identificação
	 * @return boolean informando se existe alguma cadeira no plano de curso que
	 *         dependa da cadeira passada como argumento
	 */
	public boolean temDependentes(int periodo, Long idDisciplina) {
		Disciplina disciplinaAnalisada = Disciplina.find.byId(idDisciplina);
		for (int indicePeriodo = periodo + 1; indicePeriodo < getPeriodos()
				.size(); indicePeriodo++) {
			for (int d = getNumeroDeDisciplinas(indicePeriodo) - 1; d >= 0; d--) {
				Disciplina cadTemp = getPeriodo(indicePeriodo).getDisciplinas()
						.get(d);
				if (cadTemp.ehPreRequisito(disciplinaAnalisada)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Realoca disciplinas para os períodos escolhidos
	 * 
	 * @param periodoAtual
	 *            Período que a cadeira está alocada
	 * @param novoPeriodo
	 *            Período para o qual a cadeira sera movida
	 * @param cadeira
	 *            Disciplina a ser realocada
	 * @throws JaContemDisciplinaException
	 * @throws LimitesExcedidosException
	 */
	public void realocaCadeiras(int periodoAtual, int novoPeriodo,
			Disciplina disciplina) throws LimitesExcedidosException,
			JaContemDisciplinaException {
		if( ( periodos.get(novoPeriodo).creditosTotal() + disciplina.getCreditos() ) <= periodos.get(novoPeriodo).LIMITE_CREDITOS){
			periodos.get(periodoAtual).removeDisciplina(disciplina);
			try{
				//disciplina.setPeriodo(Ebean.find(Periodo.class, novoPeriodo).getId().intValue());
				periodos.get(novoPeriodo).addCadeira(disciplina);
				}
			
			catch (LimitesExcedidosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (JaContemDisciplinaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean temRequisitosDesalocados(String nomeDisciplina) {
		Disciplina disciplina = getCadeira(nomeDisciplina);
		for (int i = 0; i < periodos.size(); i++) {
			for (Disciplina cadeiraTemp : periodos.get(i).getDisciplinas()) {
				if (disciplina.ehPreRequisito(cadeiraTemp)) {
					return true;
				}
			}
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public static Finder<Long, PlanoDeCurso> find = new Finder<Long, PlanoDeCurso>(
			Long.class, PlanoDeCurso.class);
	
	public Disciplina getCadeira(String nomeDisciplina){
		for (Periodo p : periodos) {
			for (Disciplina d : p.getDisciplinas()) {
				if (d.getNomeCadeira().equals(nomeDisciplina)) {
					return d;
				}
			}
		}
		return null;
	}
	
	public void addNasNaoAlocadas(Disciplina d){
		disciplinasNaoAlocadas.add(d);
	}
	
	
}
