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
	
	public static Result logout(){
		return ok(login.render());
		
	}
	
	public static Result autenticarUsuario() throws PrerequisitosInsuficientesException, LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String email = form.get("email");
		final String senha = form.get("senha");
		Usuario usuarioTemp = Usuario.authenticate(email, senha);
		if (usuarioTemp != null) {
			Application.usuario = usuarioTemp;
			return Application.index(usuarioTemp);
		}

		flash("Exception", "Verifique se seus estão corretos!");
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
		final int tipoPlano = Integer.parseInt(form.get("tipo_plano"));

		novoCadastro = new CadastroDeUsuario(nome, email, senha, confirmaSenha);
	

		try {
			if (novoCadastro.cadastroValido()) {
				criarNovoUsuario(email, nome, senha, tipoPlano);
				flash("Cadastro_Sucesso", "Cadastro realizado com sucesso!");
				return login();
			}
		} catch (CamposEmBrancoException e) {
			flash("Exception", "Campos em branco. Preencha todos os campos.");
			return badRequest(cadastro.render());
		} catch (SenhaErradaException e) {
			flash("Exception", "As senhas não correspondem.");
			return badRequest(cadastro.render());
		} catch (CadastroDeUsuarioException e) {
			flash("Exception", "Email já cadastrado no sistema Minha Grade."); 
			return badRequest(cadastro.render());
		}
		return badRequest(cadastro.render());
	}

	private static void criarNovoUsuario(String email, String nome, String senha, int tipoPlano) {
		PlanoDeCurso plano = new PlanoDeCurso();
		plano.setPreenchedor(tipoPlano);
		plano.povoaPlano();
		Usuario usuario = new Usuario(email, nome, senha, plano);
		usuario.save();
		Application.usuario = usuario;
	
		
	}

}
