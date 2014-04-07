package models;

public class PlanoDeCursoComum implements PreenchedorDePlano {

	@Override
	public void preenchePlanoDeCurso(PlanoDeCurso plano) {
		try{
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
			plano.addCadeira("Informatica e Sociedade", 4, 3, 1);
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
			plano.addCadeira("Direito e Cidadania", 4, 5, 3);
			plano.addCadeira("Logica Matematica", 4, 5, 3);
			plano.addCadeira("Sistemas da Informacao I", 4, 4, 3);
			
			// cadeiras 5 periodo
			plano.addCadeira("Eng de Software I", 4, 4, 4);
			plano.addCadeira("Analise Tec de Algoritmos", 4, 4, 4);
			plano.addCadeira("Banco de Dados I", 4, 4, 4);
			plano.addCadeira("Metodos Estatisticos", 4, 4, 4);		

			// cadeiras 6 periodo
			plano.addCadeira("Redes de Computadores", 4, 4, 5);
			plano.addCadeira("Lab Eng de Software", 2, 4, 5);
			plano.addCadeira("Sistemas da Informacao II", 4, 4, 5);

			// cadeiras 7 periodo
			plano.addCadeira("Interconexao de Redes de Comp.", 2, 4, 6);
			plano.addCadeira("Lab Interconexao de Redes de Comp.", 2, 4, 6);
			plano.addCadeira("Sistemas Operacionais", 4, 4, 6);
			plano.addCadeira("Metodos e Software Num", 4, 4, 6);
			plano.addCadeira("Banco de Dados II", 4, 4, 6);

			// cadeiras 8 periodo
			plano.addCadeira("Projeto I", 4, 5, 7);
			plano.addCadeira("Compiladores", 4, 5, 7);
			plano.addCadeira("Inteligencia Artificial", 4, 4, 7);
			plano.addCadeira("Av. de Desempenho de Sist. Discretos", 4, 5, 7);
			
			//9 periodo
			plano.addCadeira("Projeto II", 6, 5, 8);
			plano.addCadeira("Estagio Integrado", 6, 5, 8);
		}catch(Exception e){
			
		}
	}

}
