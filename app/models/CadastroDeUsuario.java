package models;

import java.util.List;

import com.avaje.ebean.Ebean;

// Cadastro, responsável por cadastrar os usuários

public class CadastroDeUsuario {

	private static String nome;
	private static String email;
	private static String senha;
	private static String confirmaSenha;

	// Contrutor de Cadastro.
	public CadastroDeUsuario(String nome, String email, String senha,
			String confirmaSenha) {
		// setNome(nome);
		this.nome = nome;
		// setEmail(email);
		this.email = email;
		// setSenha(senha);
		this.senha = senha;
		// setConfirmaSenha(confirmaSenha);
		this.confirmaSenha = confirmaSenha;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		CadastroDeUsuario.nome = nome;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		CadastroDeUsuario.email = email;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		CadastroDeUsuario.senha = senha;
	}

	public static String getConfirmaSenha() {
		return confirmaSenha;
	}

	public static void setConfirmaSenha(String confSenha) {
		CadastroDeUsuario.confirmaSenha = confSenha;
	}

	public static boolean verificaCadastroDeUsuario() {
		List<Usuario> usuarios = Ebean.find(Usuario.class).findList();
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	private static boolean ChecaCamposEmBranco() {
		
		if (getNome().isEmpty() || getEmail().isEmpty() || getSenha().isEmpty()
				|| getConfirmaSenha().isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean cadastroValido() throws CamposEmBrancoException,
			SenhaErradaException, CadastroDeUsuarioException {

		if (ChecaCamposEmBranco()) {
			throw new CamposEmBrancoException();
		}

		if (!getSenha().equals(getConfirmaSenha())) {
			throw new SenhaErradaException();
		}

		if (verificaCadastroDeUsuario()) {
			throw new CadastroDeUsuarioException();
		}
		return true;
	}
}