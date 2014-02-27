package models;

import java.util.ArrayList;
import java.util.List;


public class PlanoDeCurso {

	private List<Periodo> periodos;
	final int NUMERO_PERIODOS = 10;
	
	//CREATOR: PlanoDeCurso faz uso direto do CatalogoDeDisciplina
	private CatalogoDisciplinas catalogoDeDisciplinas;
	/*INFORMATION EXPERT: PlanoDeCurso usa as disciplinas não alocadas para 
	decidir quem pode ser alocada, então ela precisa conhecer as disciplinas.*/
	
	private List<Disciplina> disciplinasNaoAlocadas; 
	
	
	public PlanoDeCurso() {
		catalogoDeDisciplinas = new CatalogoDisciplinas();
		periodos = new ArrayList<Periodo>();
		disciplinasNaoAlocadas = new ArrayList<Disciplina>();
		inicializarPeriodos();
		popularDisciplinasNaoAlocadas();
	}
	
	/**
	 * Popula períodos vazios e adiciona disciplinas obrigatórias no primeiro
	 * período.
	 */
	private void inicializarPeriodos() {
		for (int i = 0; i < NUMERO_PERIODOS; i++ ){
			periodos.add(new Periodo());
		}
		try {
			// Preenche o primeiro periodo com as cadeiras obrigatórias
			adicionaCadeira(0, "CALC I");
			adicionaCadeira(0, "PROG1");
			adicionaCadeira(0, "LPROG1");
			adicionaCadeira(0, "IC");
			adicionaCadeira(0, "LPT");
			adicionaCadeira(0, "VETORIAL");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adiciona as disciplinas para a lista de não alocadas
	 */
	private void popularDisciplinasNaoAlocadas(){
		for(Disciplina d : catalogoDeDisciplinas.getCadeiras()){
			disciplinasNaoAlocadas.add(d);
		}
	}

	/**
	 * Retorna um Periodo
	 * @param i
	 * @return
	 */
	public Periodo getPeriodo(int i) {
		return periodos.get(i);
	}

	/**
	 * Retorna a lista com todos os periodos pertencentes ao usuário.
	 * @return
	 */
	public List<Periodo> getPeriodos() {
		return periodos;
	}
	
	/**
	 * Adiciona uma cadeira num determinado periodo.
	 * @param p Periodo de 0...n-1
	 * @param d Disciplina a ser adicionada
	 * @throws LimitesExcedidosException
	 * @throws JaContemDisciplinaException 
	 */
	private void addCadeiraNoPeriodo(int p, Disciplina d) throws LimitesExcedidosException, JaContemDisciplinaException{
		getPeriodo(p).addCadeira(d);
	}
	
	/**
	 * Boolean informando se uma dis
	 * @param periodo Periodo de 0...n-1
	 * @param disciplina Disciplina a ser procurada
	 * @return Boolean confirmando se o periodo contém a disciplina
	 */
	public boolean contemDisciplina(int periodo, Disciplina disciplina ){
		return getPeriodo(periodo).contains(disciplina);
	}
	
	/**
	 * Retorna o número de Disciplinas alocadas para o periodo p.
	 * @param p Periodo a ser analisado
	 * @return Inteiro contendo o número de disciplinas alocadas
	 */
	public int getNumeroDeDisciplinas(int p){
		return getPeriodo(p).qtdeCadeiras();
	}
	
	/**
	 * Retorna o total de créditos alocados para um periodo p
	 * @param p Periodo a ser analisado
	 * @return Inteiro contendo o número de créditos do periodo.
	 */
	public int getTotalDeCreditos(int p){
		return getPeriodo(p).creditosTotal();
	}
	
	
	/**
	 * @return uma Lista com as Disciplinas disponíveis para o plano de curso.
	 */
	public List<Disciplina> getCadeirasDisponiveis(){
		for (Periodo p: getPeriodos()){
			for (Disciplina d: p.getDisciplinas()){
				disciplinasNaoAlocadas.remove(d);
			}
		}
		return disciplinasNaoAlocadas;
	}

	/**
	 * Adiciona uma cadeira a um determinado periodo.
	 * @param periodo Inteiro indicando a qual periodo a cadeira será adicionada
	 * @param cadeira String contendo a identificação única da cadeira;
	 * @throws PrerequisitosInsuficientesException Caso os prerequisitos da cadeira não estejam alocados no plano de cuso
	 * @throws LimitesExcedidosException Caso o periodo esteja completamente cheio e não possa 
	 * @throws JaContemDisciplinaException 
	 */
	public void adicionaCadeira(int periodo, String cadeira) throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException {
		Disciplina c = catalogoDeDisciplinas.getCadeira(cadeira);
		
		for (Disciplina req: c.getRequisitos()){
			// varre os periodos anteriores vendo se os pre-requisitos estão adicionados.
			boolean pagavel = false;
			for (int p = 0; p < periodo; p++){
				if (contemDisciplina(p,req)){
					pagavel = true; 
				}
			}
			if (!pagavel) {
				throw new PrerequisitosInsuficientesException();
			}
		}
		addCadeiraNoPeriodo(periodo, c);
	}

	/**
	 * Remove uma cadeira de um determinado periodo. 
	 * @param periodo Inteiro indicando o periodo (de 0...n-1)
	 * @param disciplina String contendo a identificação da Disciplina a ser removida
	 */
	public void removeCadeira(int periodo, String disciplina) {

		Disciplina disciplinaARemover = catalogoDeDisciplinas.getCadeira(disciplina);
		
		getPeriodo(periodo).removeDisciplina(disciplinaARemover);
		
		if (temDependentes(periodo, disciplina)){ 
			// A recursão nessa parte do código garante que todos os dependentes
			// serão removidos do plano de curso
			removerDependentes(periodo, disciplina); 
		}
		
		
		disciplinasNaoAlocadas.add(disciplinaARemover);		
	}
	

	/**
	 * Remove as disciplinas que são dependentes de uma determinada disciplina. 
	 * @param periodo Inteiro identificando o periodo da displina 'pivot'.
	 * @param disciplina String identificando a disciplina 'pivot'
	 */
	private void removerDependentes(int periodo, String disciplina) {
		// para cada cadeira que tem o prerequisito
		// retirado da grade também sai da grade;
		Disciplina disciplinaARemover = catalogoDeDisciplinas.getCadeira(disciplina);
		for (int p = periodo + 1; p < getPeriodos().size(); p++) {
			for (int d = getNumeroDeDisciplinas(p) - 1; d >= 0; d--) {
				Disciplina cadTemp = getPeriodo(p).getDisciplinas().get(d);
				if (cadTemp.ehPreRequisito(disciplinaARemover)) {
					removeCadeira(p, cadTemp.getNomeCadeira());
				}
			}
		}
	}

	/**
	 * Retorna um boolean mostrando se uma cadeira tem ou não dependentes. 
	 * @param periodo Inteiro com o periodo da cadeira a ser analisada. 
	 * @param disciplina String com a identificação 
	 * @return boolean informando se existe alguma cadeira no plano de curso que dependa da cadeira passada como argumento
	 */
	public boolean temDependentes(int periodo, String disciplina){
		Disciplina disciplinaAnalisada = catalogoDeDisciplinas.getCadeira(disciplina);
		for (int indicePeriodo = periodo + 1; indicePeriodo < getPeriodos().size(); indicePeriodo++){
			for (int d = getNumeroDeDisciplinas(indicePeriodo) - 1; d >= 0; d--){
				Disciplina cadTemp = getPeriodo(indicePeriodo).getDisciplinas().get(d);
				if (cadTemp.ehPreRequisito(disciplinaAnalisada)){
					return true;
				}
			}
		}
		return false;	
	}
	
	
	
	
}