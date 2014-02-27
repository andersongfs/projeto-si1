package models;

import java.util.ArrayList;
import java.util.List;

public class Periodo {
	public final int LIMITE_CREDITOS = 28;
	
	//INFORMATION EXPERT: O periodo usa e gerencia a disciplina então ele precisa saber quais disciplinas ele contém.
	
	private List<Disciplina> disciplinas;
	
	public Periodo() {
		disciplinas = new ArrayList<Disciplina>();
	}
	

	/**
	 * Retorna disciplinas do periodo
	 * @return
	 */
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	
	/**
	 * Retorna quantidade de creditos do periodo
	 * @return
	 */
	public int creditosTotal() {
		int saida = 0;
		for (Disciplina d: disciplinas){
			saida += d.getCreditos();
		}
		return saida;
	}
	
	/**
	 * Inteiro informando a quantidade de cadeiras atualmente alocadas no 
	 * periodo
	 * @return
	 */
	public int qtdeCadeiras(){
		return disciplinas.size();
	}
	
	
	/**
	 * Adiciona uma cadeira ao periodo.
	 * @param cadeira Objeto do tipo Displina contendo a cadeira a ser adicionada.
	 * @throws LimitesExcedidosException Caso o número limite de créditos por 
	 * periodo seja excedido. 
	 * @throws JaContemDisciplinaException 
	 */
	public void addCadeira(Disciplina cadeira) throws LimitesExcedidosException, JaContemDisciplinaException {
		if (creditosTotal() + cadeira.getCreditos() <= LIMITE_CREDITOS){
			if(!disciplinas.contains(cadeira)){
				disciplinas.add(cadeira);
			}
			else if(disciplinas.contains(cadeira)){
				throw new JaContemDisciplinaException();
			}
		} else {
			throw new LimitesExcedidosException();
		}		
	}
	
	/**
	 * Retorna a dificuldade total do periodo... 
	 * @return
	 */
	public int getDificuldadePeriodo(){
		int somaDificuldades = 0;
		for (Disciplina d: disciplinas){
			somaDificuldades += d.getDificuldade();
		}
		return somaDificuldades;
	}
	
	
	/**
	 *  Remove uma disciplina do periodo
	 * @param cadeira
	 */
	public void removeDisciplina(Disciplina cadeira) {
		disciplinas.remove(cadeira);
	}

	public boolean contains(Disciplina disciplina) {
		return disciplinas.contains(disciplina);
	}

}
