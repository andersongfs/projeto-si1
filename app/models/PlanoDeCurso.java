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
	private final int PRIMEIRO_PERIODO = 0;
	private int periodoAtual;
	
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
		periodoAtual = PRIMEIRO_PERIODO;
	//	popularDisciplinasNaoAlocadas();
	}

	/**
	 * Popula períodos vazios e adiciona disciplinas obrigatórias no primeiro
	 * período.
	 * @throws JaContemDisciplinaException 
	 * @throws LimitesExcedidosException 
	 */
	private void inicializarPeriodos(){
		for (int i = 0; i < NUMERO_PERIODOS; i++) {
			periodos.add(new Periodo());
		}
		try{
			for (Disciplina d : Disciplina.find.all()){
				int periodo = d.getPeriodo();
				periodos.get(periodo).addCadeira(d);
			}
		}catch (Exception e){
			
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

	public boolean temRequisitosDesalocados(Long idDisciplina) {
		Disciplina disciplina = Disciplina.find.byId(idDisciplina);
		for (int i = disciplina.getPeriodo(); i < periodos.size(); i++) {
			for (Disciplina disciplinaTemp : periodos.get(i).getDisciplinas()) {
				if (disciplina.ehPreRequisito(disciplinaTemp)) {
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

	public int getPeriodoAtual() {
		return periodoAtual;
	}

	public void setPeriodoAtual(int periodoAtual) {
		this.periodoAtual = periodoAtual;
	}
	
	public int calculaCreditosPagos(){
		int creditosPagos = 0;
		for(int i = 0; i < this.periodoAtual; i++){
			creditosPagos += this.getPeriodo(i).getTotalCreditos();
		}
		return creditosPagos;
	}
	
	public int calculaCreditosCursando(){
		return this.getPeriodo(periodoAtual).getTotalCreditos();
	}
	
	public int calculaCreditosPlanejados(){
		int creditosPlanejados = 0;
		for (Periodo p : periodos) {
			creditosPlanejados += p.getTotalCreditos();
			
		}
		return creditosPlanejados;
		
	}
	
}
