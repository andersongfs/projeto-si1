package controllers;

import models.CadastroDeUsuario;
import models.CamposEmBrancoException;

import models.JaContemDisciplinaException;
import models.LimitesExcedidosException;
import models.PlanoDeCurso;
import models.PrerequisitosInsuficientesException;
import models.SenhaErradaException;
import models.Usuario;
import models.CadastroDeUsuarioException;
import play.mvc.*;
import views.html.*;
import play.data.DynamicForm;


public class Autenticacao extends Controller {

	private static CadastroDeUsuario novoCadastro;
	
	
	//Login e logout de usuario
	
	
	public static Result cadastro() {
		return ok(cadastro.render());
	}
	
	public static Result login() {
		return ok(login.render());
	}
	
	public static Result autenticarUsuario() throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String email = form.get("email");
		final String senha = form.get("senha");
		System.out.println(" EMAIL " + email);
		System.out.println(" SENHA " + senha);
		Usuario usuarioTemp = Usuario.authenticate(email, senha);
		System.out.println(usuarioTemp.getNome());
		System.out.println("To aqui antes do if");
		if (usuarioTemp != null) {
			System.out.println("Entrei aqui");
			Application.usuario = usuarioTemp;
			return Application.index(usuarioTemp);
		}

		flash("Verifique se seus estão corretamente!");
		return badRequest(login.render());
	}
	
	//Cadastro e cadastro de novos usuarios.
	
	
	
	public static Result cadastrarNovoUsuario() throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String nome = form.get("nome");
		final String email = form.get("email");
		final String senha = form.get("senha");
		final String confirmaSenha = form.get("confirm_senha");

		novoCadastro = new CadastroDeUsuario(nome, email, senha, confirmaSenha);
	

		try {
			if (novoCadastro.cadastroValido()) {
				criarNovoUsuario(email, nome, senha);
			
				return login();
			}
		} catch (CamposEmBrancoException e) {
			
			return badRequest(cadastro.render());
		} catch (SenhaErradaException e) {
			
			return badRequest(cadastro.render());
		} catch (CadastroDeUsuarioException e) {
			
			return badRequest(cadastro.render());
		}
		return badRequest(cadastro.render());
	}

	private static void criarNovoUsuario(String email, String nome, String senha) {
		PlanoDeCurso plano = new PlanoDeCurso();
		Usuario usuario = new Usuario(email, nome, senha, plano);
		usuario.save();
		Application.usuario = usuario;
	
		
	}

}
