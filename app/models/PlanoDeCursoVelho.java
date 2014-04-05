package models;

import java.util.ArrayList;

import javax.persistence.*;

public class PlanoDeCursoVelho implements PreenchedorDePlano{
	
	@Override
	public void preenchePlanoDeCurso(PlanoDeCurso plano) {
		try {
			plano.addCadeira("Calculo I", 4, 5, 0);
			plano.addCadeira("Vetorial", 4, 4, 0);
			plano.addCadeira("LPT", 4, 2, 0);
			plano.addCadeira("Programacao I", 4, 3, 0);
			plano.addCadeira("Lab Programacao I", 4, 3, 0);
			plano.addCadeira("Introducao a Computacao", 4, 3, 0);

			// cadeiras 2 periodo
			plano.addCadeira("Programacao II", 4, 4, 1);
			plano.addCadeira("Lab Programacao II", 4, 4, 1);
			plano.addCadeira("Teoria dos Grafos", 2, 3, 1);
			plano.addCadeira("Calculo II", 4, 5, 1);
			plano.addCadeira("Matematica Discreta", 4, 4, 1);
			plano.addCadeira("Metodologia Cientifica", 4, 3, 1);
			plano.addCadeira("Fund Fisica Classica", 4, 4, 1);

			// cadeiras 3 periodo
			plano.addCadeira("Estrutura de Dados", 4, 5, 2);
			plano.addCadeira("Lab Estrutura de Dados", 4, 5, 2);
			plano.addCadeira("Algebra Linear", 4, 5, 2);
			plano.addCadeira("Probabilidade e Estat.", 4, 5, 2);
			plano.addCadeira("Teoria da Computacao", 4, 3, 2);
			plano.addCadeira("Fund Fisica Moderna", 4, 5, 2);
			plano.addCadeira("Gerencia da Informacao", 4, 4, 2);

			// cadeiras 4 periodo
			plano.addCadeira("Org Arquitetura de Computadores", 4, 4, 3);
			plano.addCadeira("Lab Org Arquitetura de Computadores", 4, 4, 3);
			plano.addCadeira("Paradigmas de Ling de Prog", 2, 4, 3);
			plano.addCadeira("Metodos Estatisticos", 4, 5, 3);
			plano.addCadeira("Logica Matematica", 4, 5, 3);
			plano.addCadeira("Sistemas da Informacao I", 4, 4, 3);
			plano.addCadeira("Eng de Software I", 4, 4, 3);

			// cadeiras 5 periodo
			plano.addCadeira("Analise Tec de Algoritmos", 4, 4, 4);
			plano.addCadeira("Redes de Computadores", 4, 4, 4);
			plano.addCadeira("Compiladores", 4, 5, 4);
			plano.addCadeira("Banco de Dados I", 4, 4, 4);
			plano.addCadeira("Sistemas da Informacao II", 4, 4, 4);
			plano.addCadeira("Lab Eng de Software", 2, 4, 4);
			plano.addCadeira("Informatica e Sociedade", 2, 2, 4);

			// cadeiras 6 periodo
			plano.addCadeira("Sistemas Operacionais", 4, 4, 5);
			plano.addCadeira("Interconexao de Redes de Comp.", 2, 4, 5);
			plano.addCadeira("Lab Interconexao de Redes de Comp.", 2, 4, 5);
			plano.addCadeira("Inteligencia Artificial", 4, 4, 5);
			plano.addCadeira("Banco de Dados II", 4, 4, 5);
			plano.addCadeira("Optativa 1", 4, 3, 5);
			plano.addCadeira("Optatiiva 2", 4, 3, 5);
			plano.addCadeira("Direito e Cidadania", 4, 2, 5);

			// cadeiras 7 periodo
			plano.addCadeira("Metodos e Software Num", 4, 4, 6);
			plano.addCadeira("Av. de Desempenho de Sist. Discretos", 4, 5, 6);
			plano.addCadeira("Optativa 3", 4, 3, 6);
			plano.addCadeira("Optativa 4", 4, 3, 6);
			plano.addCadeira("Optativa 5", 4, 3, 6);
			plano.addCadeira("Projeto I", 4, 5, 6);
			plano.addCadeira("Optativa 6", 4, 3, 6);

			// cadeiras 8 periodo
			plano.addCadeira("Projeto II", 6, 5, 7);
			plano.addCadeira("Optativa 7", 4, 3, 7);
			plano.addCadeira("Optativa 8", 4, 3, 7);
			plano.addCadeira("Optativa 9", 4, 3, 7);
			plano.addCadeira("Optativa 10", 4, 3, 7);
			plano.addCadeira("Optativa 11", 4, 3, 7);
			//
			// // adicionar requisitos
			//
			// 2 periodo
			plano.getCadeira("Calculo II").addRequisitos(plano.getCadeira("Calculo I"));
			plano.getCadeira("Programacao II").addRequisitos(
					plano.getCadeira("Programacao I"));
			plano.getCadeira("Programacao II").addRequisitos(
					plano.getCadeira("Lab Programacao I"));
			plano.getCadeira("Programacao II").addRequisitos(
					plano.getCadeira("Introducao a Computacao"));
			plano.getCadeira("Lab Programacao II").addListaRequisitos(
					plano.getCadeira("Programacao II").getRequisitos());
			plano.getCadeira("Teoria dos Grafos").addRequisitos(
					plano.getCadeira("Programacao I"));
			plano.getCadeira("Teoria dos Grafos").addRequisitos(
					plano.getCadeira("Lab Programacao I"));
			plano.getCadeira("Fund Fisica Moderna").addRequisitos(
					plano.getCadeira("Calculo I"));
			plano.getCadeira("Fund Fisica Moderna").addRequisitos(
					plano.getCadeira("Vetorial"));

			// 3 periodo
			plano.getCadeira("Estrutura de Dados").addRequisitos(
					plano.getCadeira("Programacao II"));
			plano.getCadeira("Estrutura de Dados").addRequisitos(
					plano.getCadeira("Lab Programacao II"));
			plano.getCadeira("Estrutura de Dados").addRequisitos(
					plano.getCadeira("Teoria dos Grafos"));
			plano.getCadeira("Lab Estrutura de Dados").addListaRequisitos(
					plano.getCadeira("Estrutura de Dados").getRequisitos());
			plano.getCadeira("Algebra Linear").addRequisitos(plano.getCadeira("Vetorial"));
			plano.getCadeira("Probabilidade e Estat.").addRequisitos(
					plano.getCadeira("Calculo II"));
			plano.getCadeira("Teoria da Computacao").addRequisitos(
					plano.getCadeira("Teoria dos Grafos"));
			plano.getCadeira("Teoria da Computacao").addRequisitos(
					plano.getCadeira("Matematica Discreta"));
			plano.getCadeira("Teoria da Computacao").addRequisitos(
					plano.getCadeira("Introducao a Computacao"));
			plano.getCadeira("Fund Fisica Moderna").addRequisitos(
					plano.getCadeira("Fund Fisica Classica"));
			plano.getCadeira("Fund Fisica Moderna").addRequisitos(
					plano.getCadeira("Calculo II"));

			// 4 periodo
			plano.getCadeira("Org Arquitetura de Computadores").addRequisitos(
					plano.getCadeira("Estrutura de Dados"));
			plano.getCadeira("Org Arquitetura de Computadores").addRequisitos(
					plano.getCadeira("Lab Estrutura de Dados"));
			plano.getCadeira("Org Arquitetura de Computadores").addRequisitos(
					plano.getCadeira("Fund Fisica Moderna"));
			plano.getCadeira("Lab Org Arquitetura de Computadores")
					.addListaRequisitos(
							plano.getCadeira("Org Arquitetura de Computadores")
									.getRequisitos());
			plano.getCadeira("Paradigmas de Ling de Prog").addRequisitos(
					plano.getCadeira("Estrutura de Dados"));
			plano.getCadeira("Paradigmas de Ling de Prog").addRequisitos(
					plano.getCadeira("Lab Estrutura de Dados"));
			plano.getCadeira("Paradigmas de Ling de Prog").addRequisitos(
					plano.getCadeira("Teoria da Computacao"));
			plano.getCadeira("Metodos Estatisticos").addRequisitos(
					plano.getCadeira("Probabilidade e Estat."));
			plano.getCadeira("Metodos Estatisticos").addRequisitos(
					plano.getCadeira("Algebra Linear"));
			plano.getCadeira("Logica Matematica").addRequisitos(
					plano.getCadeira("Teoria da Computacao"));
			plano.getCadeira("Sistemas da Informacao I").addRequisitos(
					plano.getCadeira("Gerencia da Informacao"));
			plano.getCadeira("Eng de Software I").addRequisitos(
					plano.getCadeira("Probabilidade e Estat."));
			plano.getCadeira("Eng de Software I").addRequisitos(
					plano.getCadeira("Programacao II"));
			plano.getCadeira("Eng de Software I").addRequisitos(
					plano.getCadeira("Lab Programacao II"));

			// // 5 periodo
			plano.getCadeira("Analise Tec de Algoritmos").addRequisitos(
					plano.getCadeira("Estrutura de Dados"));
			plano.getCadeira("Analise Tec de Algoritmos").addRequisitos(
					plano.getCadeira("Lab Estrutura de Dados"));
			plano.getCadeira("Analise Tec de Algoritmos").addRequisitos(
					plano.getCadeira("Calculo II"));
			plano.getCadeira("Analise Tec de Algoritmos").addRequisitos(
					plano.getCadeira("Logica Matematica"));
			plano.getCadeira("Redes de Computadores").addRequisitos(
					plano.getCadeira("Org Arquitetura de Computadores"));
			plano.getCadeira("Redes de Computadores").addRequisitos(
					plano.getCadeira("Lab Org Arquitetura de Computadores"));
			plano.getCadeira("Compiladores").addListaRequisitos(
					plano.getCadeira("Redes de Computadores").getRequisitos());
			plano.getCadeira("Compiladores").addRequisitos(
					plano.getCadeira("Paradigmas de Ling de Prog"));
			plano.getCadeira("Banco de Dados I").addRequisitos(
					plano.getCadeira("Sistemas da Informacao I"));
			plano.getCadeira("Sistemas da Informacao II").addRequisitos(
					plano.getCadeira("Sistemas da Informacao I"));
			plano.getCadeira("Lab Eng de Software").addRequisitos(
					plano.getCadeira("Eng de Software I"));

			// 6 periodo
			plano.getCadeira("Sistemas Operacionais").addListaRequisitos(
					plano.getCadeira("Redes de Computadores").getRequisitos());
			plano.getCadeira("Interconexao de Redes de Comp.").addRequisitos(
					plano.getCadeira("Redes de Computadores"));
			plano.getCadeira("Lab Interconexao de Redes de Comp.").addRequisitos(
					plano.getCadeira("Redes de Computadores"));
			plano.getCadeira("Inteligencia Artificial").addRequisitos(
					plano.getCadeira("Metodos Estatisticos"));
			plano.getCadeira("Inteligencia Artificial").addRequisitos(
					plano.getCadeira("Paradigmas de Ling de Prog"));
			plano.getCadeira("Inteligencia Artificial").addRequisitos(
					plano.getCadeira("Analise Tec de Algoritmos"));
			plano.getCadeira("Banco de Dados II").addRequisitos(
					plano.getCadeira("Banco de Dados I"));
			plano.getCadeira("Banco de Dados II").addRequisitos(
					plano.getCadeira("Sistemas da Informacao II"));

			// // 7 periodo
			plano.getCadeira("Metodos e Software Num").addRequisitos(
					plano.getCadeira("Analise Tec de Algoritmos"));
			plano.getCadeira("Metodos e Software Num").addRequisitos(
					plano.getCadeira("Algebra Linear"));
			plano.getCadeira("Av. de Desempenho de Sist. Discretos").addRequisitos(
					plano.getCadeira("Probabilidade e Estat."));
			plano.getCadeira("Projeto I").addRequisitos(
					plano.getCadeira("Lab Eng de Software"));
			plano.getCadeira("Projeto I").addRequisitos(
					plano.getCadeira("Metodologia Cientifica"));

			// // 8 periodo
			plano.getCadeira("Projeto II").addRequisitos(plano.getCadeira("Projeto I"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
