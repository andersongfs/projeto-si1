package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javassist.SerialVersionUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.base.Objects;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Disciplina extends Model implements Comparable<Disciplina>{
	/**
	 * 
	 */
	

	private static final long SerialVersionUID = 1L;
	
	@Id
	Long id;
	
	/*INFORMATION EXPERT: Disciplina precisa saber seu nome, pois é uma
	 * propriedade da disciplina.
	*/
	
	@Constraints.Required
	@Column(unique = true, nullable=false)
	private String nomeCadeira;
	
	/*INFORMATION EXPERT: Disciplina precisa saber seus créditos, pois é uma
	 * propriedade da disciplina.
	*/
	private int creditos;
	
	/*INFORMATION EXPERT: Disciplina precisa saber seus requisitos, pois é uma
	 * responsabilidade da disciplina conhecer seus requisitos.
	*/
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "cadeira_requisito", joinColumns = {@JoinColumn (name = "fk_cadeira")}, inverseJoinColumns = {@JoinColumn(name = "fk_requisito")})
	private List<Disciplina> requisitos;
	
	// information expert
	private int dificuldade;
	private int periodoDefault; 
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

	public Disciplina(String cadeira, int creditos, int dificuldade, int periodoDefault) {
		setNomeCadeira(cadeira);
		requisitos = new ArrayList<Disciplina>();
		setCreditos(creditos);
		setDificuldade(dificuldade); // dificuldade usada no sistema é de 0 a 5
		setPeriodoDefault(periodoDefault);
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
	
	public static Finder<Long,Disciplina> find = new Finder<Long,Disciplina>(
		    Long.class, Disciplina.class
	); 
	
	public static void create(Disciplina disciplina) {
		disciplina.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		Disciplina disciplina = find.ref(id);
		disciplina.update();
	}
	
	public int compareTo(Disciplina disciplina) {
		return getNomeCadeira().compareTo(disciplina.getNomeCadeira());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNomeCadeira(), creditos);
	}

	public int getPeriodoDefault() {
		return periodoDefault;
	}

	public void setPeriodoDefault(int periodoDefault) {
		this.periodoDefault = periodoDefault;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Disciplina other = (Disciplina) obj;
//		return Objects.equal(this.getCreditos(), other.getCreditos())
//				&& Objects.equal(this.getNomeCadeira(), other.getNomeCadeira());
//	}

//	@Override
//	public String toString() {
//		return "Cadeira [id=" + id + ", nome=" + nome + ", periodo=" + periodo
//				+ "]";
//	}
}