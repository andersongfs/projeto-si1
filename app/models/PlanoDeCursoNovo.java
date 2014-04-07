package models;


public class PlanoDeCursoNovo implements PreenchedorDePlano{

	@Override
	public void preenchePlanoDeCurso(PlanoDeCurso plano) {
		try {
			//1 periodo
			plano.addCadeira("Matematica Discreta I", 4, 4, 0);
			plano.addCadeira("Programação I", 4, 3, 0);
			plano.addCadeira("Lab Programação I", 4, 3, 0);
			plano.addCadeira("Introdução a Computacao", 4, 3, 0);
			plano.addCadeira("Optativa Geral I", 4, 3, 0);

			//2 periodo
			plano.addCadeira("Matematica Discreta II", 4, 4, 1);
			plano.addCadeira("Calculo I", 4, 5, 1);
			plano.addCadeira("Programação II", 4, 4, 1);
			plano.addCadeira("Lab Programação II", 4, 4, 1);
			plano.addCadeira("Optativa Geral II", 4, 4, 1);
			
			//3periodo
			plano.addCadeira("Algebra Linear", 4, 5, 2);
			plano.addCadeira("Teoria dos Grafos", 4, 3, 2);
			plano.addCadeira("Calculo II", 4, 5, 2);
			plano.addCadeira("Estrutura de Dados", 4, 5, 2);
			plano.addCadeira("Lab Estrutura de Dados", 4, 5, 2);
			plano.addCadeira("Logica", 4, 5, 2);
			
			//4 periodo
			plano.addCadeira("Introd a Probabilidade", 4, 5, 3);
			plano.addCadeira("Projeto de Software", 4, 5, 3);
			plano.addCadeira("PLP", 4, 5, 3);
			plano.addCadeira("Banco de Dados I", 4, 5, 3);
			plano.addCadeira("Org e Arq de Computadores", 4, 5, 3);
			plano.addCadeira("Lab Org e Arq de Computadores", 4, 5, 3);
			
			//5 periodo
			plano.addCadeira("Estatistica Aplicada", 4, 5, 4);
			plano.addCadeira("Analise de Sistemas", 4, 4, 4);
			plano.addCadeira("Engenharia de Software", 4, 4, 4);
			plano.addCadeira("Redes de Comp", 4, 4, 4);
			plano.addCadeira("Sistemas Operacionais", 4, 4, 4);
			plano.addCadeira("Teoria da Computacao", 4, 4, 4);

			//6 periodo
			plano.addCadeira("Metodologia Cientifica", 4, 4, 5);
			plano.addCadeira("Prog Concorrente", 4, 4, 5);
			plano.addCadeira("Intelig Artificial", 4, 4, 5);
			plano.addCadeira("Optativa Especifica I", 4, 3, 5);
			plano.addCadeira("Optativa Especifica II", 4, 3, 5);
			
			//7 periodo
			plano.addCadeira("Analise Tec de Algoritmos", 4, 4, 6);
			plano.addCadeira("Compiladores", 4, 4, 6);
			plano.addCadeira("Optativa Especifica III", 4, 3, 6);
			plano.addCadeira("Optativa Especifica IV", 4, 3, 6);
			plano.addCadeira("Optativa Geral III", 4, 3, 6);
			
			//8 periodo
			plano.addCadeira("Projeto em Computacao I", 4, 5, 7);
			plano.addCadeira("Optativa Especifica V", 4, 5, 7);
			plano.addCadeira("Optativa Especifica VI", 4, 5, 7);
			plano.addCadeira("Optativa Geral IV", 4, 5, 7);
			plano.addCadeira("Trabalho de Conclusao de Curso I", 4, 5, 7);
			
			//9 periodo
			plano.addCadeira("Projeto em Computacao II", 4, 5, 8);
			plano.addCadeira("Optativa Especifica VII", 4, 5, 8);
			plano.addCadeira("Optativa Especifica VIII", 4, 5, 8);
			plano.addCadeira("Optativa Especifica IX", 4, 5, 8);
			plano.addCadeira("Optativa Especifica X", 4, 5, 8);
			plano.addCadeira("Trabalho de Conclusao de Curso II", 4, 5, 8);
			
			//adicionando requisitos
//			plano.getCadeira("Matematica Discreta II").addRequisitos(plano.getCadeira("Matematica Discreta I"));
//			plano.getCadeira("Programação II").addRequisitos(plano.getCadeira("Programação I"));
//			plano.getCadeira("Programação II").addRequisitos(plano.getCadeira("Lab Programação I"));
//
//			plano.getCadeira("Algebra Linear").addRequisitos(plano.getCadeira("Matematica Discreta I"));
//			plano.getCadeira("Calculo II").addRequisitos(plano.getCadeira("Calculo I"));
//			plano.getCadeira("Estrutura de Dados").addRequisitos(plano.getCadeira("Programacão II"));
//			plano.getCadeira("Estrutura de Dados").addRequisitos(plano.getCadeira("Lab Programacão II"));
//			plano.getCadeira("Lab Estrutura de Dados").addListaRequisitos(plano.getCadeira("Estrutura de Dados").getRequisitos());
//			plano.getCadeira("Logica").addRequisitos(plano.getCadeira("Matematica Discreta I"));
//			
//			plano.getCadeira("Introd a Probabilidade").addRequisitos(plano.getCadeira("Matematica Discreta II"));
//			plano.getCadeira("Introd a Probabilidade").addRequisitos(plano.getCadeira("Calculo I"));
//			plano.getCadeira("Banco de Dados I").addRequisitos(plano.getCadeira("Estrutura de Dados"));
//			
//			plano.getCadeira("Estatistica Aplicada").addRequisitos(plano.getCadeira("Introd a Probabilidade"));
//			plano.getCadeira("Teoria da Computacao").addRequisitos(plano.getCadeira("PLP"));
//
//			plano.getCadeira("Prog Concorrente").addRequisitos(plano.getCadeira("Sistemas Operacionais"));
//			plano.getCadeira("Intelig Artificial").addRequisitos(plano.getCadeira("Teoria da Computacao"));
//			
//			plano.getCadeira("Projeto em Computacao I").addRequisitos(plano.getCadeira("Engenharia de Software"));
//			
//			plano.getCadeira("Projeto em Computacao II").addRequisitos(plano.getCadeira("Projeto em Computacao I"));
//			plano.getCadeira("Trabalho de Conclusao de Curso II").addRequisitos(plano.getCadeira("Trabalho de Conclusao de Curso I"));
		}catch (Exception e){
			
		}
	}
}
