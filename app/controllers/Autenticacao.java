package controllers;

import models.Cadastro;
import models.CamposEmBrancoException;
import models.CatalogoDisciplinas;
import models.PlanoDeCurso;
import models.SenhaErradaException;
import models.Usuario;
import models.UsuarioCadastradoException;
import play.mvc.*;
import views.html.*;
import play.data.DynamicForm;


public class Autenticacao extends Controller {

	private static Cadastro novoCadastro;
	
	
	//Login e logout de usuario
	
	
	public static Result cadastro() {
		return ok(cadastro.render());
	}
	
	public static Result login() {
		return ok(login.render());
	}
	
	public static Result autenticarUsuario() {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String email = form.get("email");
		final String senha = form.get("senha");

		Usuario usuarioTemporario = Usuario.authenticate(email, senha);

		if (usuarioTemporario != null) {
			Application.usuario = usuarioTemporario;
			return redirect(routes.Application.index());
		}

		flash("Verifique se seus dados estão inseridos corretamente!");
		return badRequest(login.render());
	}
	
	
	public static Result logout() {
		Application.usuario = null;
		return redirect(routes.Application.index());
	}
	
	//Cadastro e cadastro de novos usuarios.
	
	
	
	public static Result cadastrarNovoUsuario() {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String nome = form.get("nome");
		final String email = form.get("email");
		final String senha = form.get("senha");
		final String confSenha = form.get("confirmacao_senha");

		novoCadastro = new Cadastro(nome, email, senha, confSenha);
		System.out.println(novoCadastro.getEmail());
		System.out.println(novoCadastro.getNome());
		System.out.println(novoCadastro.getConfirmaSenha());
		System.out.println(novoCadastro.getSenha());

		try {
			if (novoCadastro.cadastroValido()) {
				criarNovoUsuario(email, nome, senha);
				return redirect(routes.Application.index());
			}
		} catch (CamposEmBrancoException e) {
			flash("erro", "Campos em branco");
			return badRequest(cadastro.render());
		} catch (SenhaErradaException e) {
			flash("erro", "Senha errada");
			return badRequest(cadastro.render());
		} catch (UsuarioCadastradoException e) {
			flash("erro", "usuário já cadastrado nesse e-mail");
			return badRequest(cadastro.render());
		}
		return badRequest(cadastro.render());
	}

	private static void criarNovoUsuario(String email, String nome, String senha) {
		PlanoDeCurso planoDeCurso = null;
		PlanoDeCurso plano = planoDeCurso.find.findUnique();
		CatalogoDisciplinas catalogo = new CatalogoDisciplinas();
		catalogo.save();
		plano = new PlanoDeCurso(catalogo);
		planoDeCurso = plano;
		planoDeCurso.save();			
		
		Usuario usuario = new Usuario(email, nome, senha, plano);
		usuario.save();
		Application.usuario = usuario;
	
		
	}

}
