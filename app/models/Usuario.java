package models;


import javax.persistence.*;

import play.db.ebean.*;
import com.avaje.ebean.*;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "Usuario")
public class Usuario extends Model{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	private String nome;
	private String email;
	private String senha;
	private int periodoAtual;
	@OneToOne(cascade=CascadeType.ALL)
	private PlanoDeCurso plano;

	
	public Usuario(String email, String nome, String senha, PlanoDeCurso plano){
		this.nome = nome;
		this.email = email;
		this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
		this.periodoAtual = periodoAtual;
		this.plano = plano;
	}
	
	public static Finder<String,Usuario> find = new Finder<String,Usuario>(
	        String.class, Usuario.class
	    ); 

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the login
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the login to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the periodoAtual
	 */
	public int getPeriodoAtual() {
		return periodoAtual;
	}
	
	 private String getSenha() {		  
		 return this.senha;
	 }

	/**
	 * @param periodoAtual the periodoAtual to set
	 */
	public void setPeriodoAtual(int periodoAtual) {
		this.periodoAtual = periodoAtual;
	}

	/**
	 * @return the plano
	 */
	public PlanoDeCurso getPlano() {
		return plano;
	}

	/**
	 * @param plano the plano to set
	 */
	public void setPlano(PlanoDeCurso plano) {
		this.plano = plano;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	


	
	public static Usuario authenticate(String email, String password) {
		Usuario u = find.where().eq("email", email).findUnique();
		if (u != null) {
			if (BCrypt.checkpw(password, u.getSenha())) {
				return u;
			}
		}
		return null;
	}



}
