package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;




@Entity
public class Periodo extends Model {
	public final int LIMITE_CREDITOS = 28;
	private static final long serialVersionUID = 1L;
	//INFORMATION EXPERT: O periodo usa e gerencia a disciplina então ele precisa saber quais disciplinas ele contém.
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "periodo_cadeira",
    joinColumns = {@JoinColumn (name = "fk_periodo")}, inverseJoinColumns = {@JoinColumn(name = "fk_cadeira")})
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
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public static Finder<Long,Periodo> find = new Finder<Long,Periodo>(
		    Long.class, Periodo.class
	); 
	
	public static void create(Periodo p) {
		p.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		Periodo p = find.ref(id);
		p.update();
	}


	public int getTotalCreditos() {
		int totalCreditos = 0;
		for(Disciplina d: this.disciplinas){
			totalCreditos += d.getCreditos();
		}
		return totalCreditos;
	}
	
	
	
	
	
}
