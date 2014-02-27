package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Disciplina {

	/*INFORMATION EXPERT: Disciplina precisa saber seu nome, pois é uma
	 * propriedade da disciplina.
	*/
	private String nomeCadeira;
	
	/*INFORMATION EXPERT: Disciplina precisa saber seus créditos, pois é uma
	 * propriedade da disciplina.
	*/
	private int creditos;
	
	/*INFORMATION EXPERT: Disciplina precisa saber seus requisitos, pois é uma
	 * responsabilidade da disciplina conhecer seus requisitos.
	*/
	private List<Disciplina> requisitos;
	
	// information expert
	private int dificuldade;
	
	/**
	 * Construtor de Disciplina
	 * @param cadeira
	 * @param creditos
	 */
	public Disciplina(String cadeira, int creditos) {
		setNomeCadeira(cadeira);
		requisitos = new ArrayList<Disciplina>();
		setCreditos(creditos);
		setDificuldade(1 + new Random().nextInt(5)); // dificuldade de 0 a 5
	}

	public Disciplina(String cadeira, int creditos, int dificuldade) {
		setNomeCadeira(cadeira);
		requisitos = new ArrayList<Disciplina>();
		setCreditos(creditos);
		setDificuldade(dificuldade); // dificuldade usada no sistema é de 0 a 5
	}
	
	/**
	 * Adiciona requisitos a disciplina
	 * @param d
	 * @return
	 */
	public boolean addRequisitos(Disciplina d) {
		return requisitos.add(d);
	}
	
	/**
	 * Adiciona uma lista de requisitos a disciplina
	 * @param l
	 */
	public void addListaRequisitos(List<Disciplina> l){
		for (Disciplina req: l){
			requisitos.add(req);
		}
	}
	
	/**
	 * Remove requisito
	 * @param d
	 * @return
	 */
	public boolean removeRequisito(Disciplina d){
		return requisitos.remove(d);
	}
	
	/**
	 * Retorna a quantidade da disciplina
	 * @return
	 */
	public int getCreditos() {
		return creditos;
	}
	
	public void setCreditos(int n){
		this.creditos = n;
	}
	
	@Override
	public String toString() {
		return String.format("%s %02d", getNomeCadeira(), getCreditos());
	}

	public String getNomeCadeira() {
		return nomeCadeira;
	}

	public void setNomeCadeira(String nomeCadeira) {
		this.nomeCadeira = nomeCadeira;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Disciplina)){
			return false;
		}
		Disciplina disc = (Disciplina) obj;
		return this.getNomeCadeira().trim().toLowerCase().equals(disc.getNomeCadeira().trim().toLowerCase());
	}

	/**
	 * Retorna requisitos da disciplina
	 * @return uma lista com os prerequisitos requisito da disciplina.
	 */
	public List<Disciplina> getRequisitos() {
		return requisitos;
	}
	
	/**
	 * Dada uma disciplina como parametro verifica se a disciplina passada como
	 * parametro é pre-requisito da disciplina corrente... 
	 * @param disciplina disciplina a qual se deseja verificar se é prerequisito
	 * @return true caso seja um prerequisito.
	 */
	public boolean ehPreRequisito(Disciplina disciplina){
		return this.getRequisitos().contains(disciplina);
	}

	/**
	 * @return the dificuldade
	 */
	public int getDificuldade() {
		return dificuldade;
	}

	/**
	 * @param dificuldade the dificuldade to set
	 */
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}
}
