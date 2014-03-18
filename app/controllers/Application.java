package controllers;

import java.util.List;

import models.CatalogoDisciplinas;
import models.Disciplina;
import models.JaContemDisciplinaException;
import models.PlanoDeCurso;
import models.LimitesExcedidosException;
import models.PrerequisitosInsuficientesException;
import play.data.DynamicForm;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	private static final int PERIODO_INEXISTENTE = -1;

	public static PlanoDeCurso planoDeCurso;
	public static String errorMessage = "";

	public static Result index() throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {
		List<PlanoDeCurso> planos = planoDeCurso.find.all();

		if (planos.isEmpty()) {
			planoDeCurso = new PlanoDeCurso();
			System.out.println("Plano Criado");
			planoDeCurso.save();

		} else {
			planoDeCurso = planos.get(0); // pega o primeiro e unico plano
			for (Disciplina d : planoDeCurso.getCadeirasDisponiveis()) {
					planoDeCurso.adicionaCadeira(d.getPeriodo(), d.getNomeCadeira());
					planoDeCurso.update();
				}
			System.out.println("Plano Populadoss");
			}
			
		
		return ok(index.render(planoDeCurso, planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));
	}

	public static Result addCadeira() {
		try {
			DynamicForm formDisciplina = new DynamicForm();
			final DynamicForm form = formDisciplina.bindFromRequest();
			final String nome = form.get("nomeCadeira");
			final int periodo = Integer.parseInt(form.get("periodo")) - 1;

			planoDeCurso.adicionaCadeira(periodo, nome);
			return index();
		} catch (LimitesExcedidosException e) {
			return badRequest(index.render(planoDeCurso,
					planoDeCurso.getPeriodos(),
					planoDeCurso.getCadeirasDisponiveis(),
					"Limite de Creditos no periodo excedido.", null,
					new Integer(PERIODO_INEXISTENTE)));
		} catch (PrerequisitosInsuficientesException e) {
			return badRequest(index.render(planoDeCurso,
					planoDeCurso.getPeriodos(),
					planoDeCurso.getCadeirasDisponiveis(),
					"Você não tem os pré-requisitos necessários.", null,
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

		if (planoDeCurso.temDependentes(periodo, nomeDisciplina)) {
			return badRequest(index.render(planoDeCurso,
					planoDeCurso.getPeriodos(),
					planoDeCurso.getCadeirasDisponiveis(), errorMessage,
					nomeDisciplina, new Integer(periodo)));
		} else {
			planoDeCurso.removeCadeira(periodo, nomeDisciplina);
			return index();
		}
	}

	public static Result removerCadeiraComPosRequisito()
			throws PrerequisitosInsuficientesException,
			LimitesExcedidosException, JaContemDisciplinaException {
		DynamicForm formDisciplina = new DynamicForm();
		final DynamicForm form = formDisciplina.bindFromRequest();
		final String nomeDisciplina = form.get("nomeCadeira");
		final int periodo = Integer.parseInt(form.get("periodo"));

		planoDeCurso.removeCadeira(periodo, nomeDisciplina);
		return index();

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
		
		planoDeCurso.realocaCadeiras(periodo, periodoARealocar - 1,
				nomeDisciplina);
		planoDeCurso.update();
		return ok(index.render(planoDeCurso, planoDeCurso.getPeriodos(), planoDeCurso.getCadeirasDisponiveis(), errorMessage, "", 0));

	}

}