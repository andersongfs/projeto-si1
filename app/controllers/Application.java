package controllers;

import java.util.List;

import models.Disciplina;
import models.JaContemDisciplinaException;
import models.PlanoDeCurso;
import models.LimitesExcedidosException;
import models.PrerequisitosInsuficientesException;
import models.Usuario;
import play.data.DynamicForm;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	private static final int PERIODO_INEXISTENTE = -1;

	public static PlanoDeCurso planoDeCurso;
	public static String errorMessage = "";
	public static Usuario usuario;

	public static Result index(Usuario usuario) throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {
		PlanoDeCurso plano = usuario.getPlano();
	
		if (plano == null) {
			plano = new PlanoDeCurso();
			planoDeCurso = plano;
			planoDeCurso.save();			

		}else{
			planoDeCurso = plano;
		}
		return ok(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(), planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));
	}

	public static Result addCadeira() {
		try {
			DynamicForm formDisciplina = new DynamicForm();
			final DynamicForm form = formDisciplina.bindFromRequest();
			final int periodo = Integer.parseInt(form.get("periodo")) - 1;
			final Long idDisciplina = Long.parseLong(form.get("idDisciplina"));
			Disciplina disciplina = Disciplina.find.byId(idDisciplina);
			planoDeCurso.addCadeiraNoPeriodo(periodo,disciplina);
			planoDeCurso.update();
			return ok(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(), planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));
		} catch (LimitesExcedidosException e) {
			return badRequest(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(),
					planoDeCurso.getPeriodos(),
					planoDeCurso.getCadeirasDisponiveis(),
					"Limite de Creditos no periodo excedido.", null,
					new Integer(PERIODO_INEXISTENTE)));
		} catch (Exception e) {
			// pass
		}
		return TODO;

	}

	public static Result removerCadeira()
			throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String nomeDisciplina = form.get("nomeCadeira");
		final int periodo = Integer.parseInt(form.get("periodo"));
		final Long idDisciplina = Long.parseLong(form.get("idDisciplina"));
		planoDeCurso.removeCadeira(periodo, idDisciplina);
		planoDeCurso.update();
		return ok(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(), planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));
		}

	public static Result realocarCadeira()
			throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();

		final String nomeDisciplina = form.get("nomeCadeira");
		final int periodo = Integer.parseInt(form.get("periodo"));
		final int periodoARealocar = Integer.parseInt(form
				.get("realoca_selecionado"));
		final Long idDisciplina = Long.parseLong(form.get("idDisciplina"));
		Disciplina disciplina = Disciplina.find.byId(idDisciplina);
		planoDeCurso.realocaCadeiras(periodo, periodoARealocar - 1,
				disciplina);
		planoDeCurso.update();
		return ok(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(), planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));

	}
	
	public static Result atualizaPerAtual(){
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final int periodoAtual = Integer.parseInt(form.get("periodoAtual"))-1;
		planoDeCurso.setPeriodoAtual(periodoAtual);
		planoDeCurso.save();
		
		return ok(index.render(planoDeCurso,usuario.getPlano().getPeriodoAtual(), planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));

	}
	
	public static Result listaUsuarios(){
		List<Usuario> usuariosDoSistema = Usuario.find.all();
		
		return ok(usuarios.render(usuariosDoSistema));
	}
	
	public static Result exibeGrade(String email){
		Usuario usuario = Usuario.find.where().eq("email", email).findUnique();
		return ok(gradeDoOutro.render(usuario, usuario.getPlano().getPeriodos(), usuario.getPlano()));
	}

}