package models;

import java.util.ArrayList;
import java.util.List;
	

public class CatalogoDisciplinas {
	
	
	//CREATOR: Nessa classe são criadas as disciplinas pois CatalogoDeDisciplinas contém todas as disciplinas do sistema.
	private  List<Disciplina> disciplinas;

	public CatalogoDisciplinas() {
			disciplinas = new ArrayList<Disciplina>();
			preencherCadeiras();	
	}
	
	/**
	 * Preenche as disciplinas ofertadas
	 */
	public void preencherCadeiras(){
		// adicionar cadeiras...
		addCadeira("CALCULO I", 4);
		addCadeira("VETORIAL", 4);
		addCadeira("LPT", 4);
		addCadeira("PROG1", 4);
		addCadeira("LPROG1", 4);
		addCadeira("IC", 4);
		addCadeira("PROG2",4);
		addCadeira("LPROG2",4);
		addCadeira("LPROG2",4);
		addCadeira("CALC II",4);
		addCadeira("DISCRETA",4);
		addCadeira("MC",4);
		addCadeira("FFC",4);
		addCadeira("GRAFOS",2);
		addCadeira("LINEAR",4);
		addCadeira("PROBABILIDADE",4);
		addCadeira("TC",4);
		addCadeira("EDA",4);
		addCadeira("FFM",4);
		addCadeira("GI",4);
		addCadeira("LEDA",4);
		addCadeira("METODOS",4);
		addCadeira("PLP",2);
		addCadeira("LOGICA",4);
		addCadeira("OAC",4);
		addCadeira("LOAC",4);
		addCadeira("ES I",4);
		addCadeira("SI I",4);
		addCadeira("SI II",4);
		addCadeira("INFOSOC",2);
		addCadeira("ATAL",4);
		addCadeira("COMPILADORES",4);
		addCadeira("REDES",4);
		addCadeira("BD I",4);
		addCadeira("LES",2);
		addCadeira("DIREITO",4);
		addCadeira("SO",4);
		addCadeira("IRC",2);
		addCadeira("BD II",4);
		addCadeira("IA",4);
		addCadeira("LIRC",2);
		addCadeira("OPTATIVA 1",4);
		addCadeira("OPTATIVA 2",4);
		addCadeira("MSN",4);
		addCadeira("ADSD",4);
		addCadeira("PROJETO I",4);
		addCadeira("OPTATIVA 3",4);
		addCadeira("OPTATIVA 4",4);
		addCadeira("OPTATIVA 5",4);
		addCadeira("OPTATIVA 6",4);
		addCadeira("PROJETO II",6);
		addCadeira("OPTATIVA 7",4);
		addCadeira("OPTATIVA 8",4);
		addCadeira("OPTATIVA 9",4);
		addCadeira("OPTATIVA 10",4);
		addCadeira("OPTATIVA 11",4);
		
		// adicionar requisitos
		// Ficaria muito mais fácil usar um BD pra manter isso ao invés de um 
		// objeto hmm? 
		
		
		// 2 periodo
		getCadeira("CALC II").addRequisitos(getCadeira("CALC I"));
		getCadeira("PROG2").addRequisitos(getCadeira("PROG1"));
		getCadeira("PROG2").addRequisitos(getCadeira("LPROG1"));
		getCadeira("PROG2").addRequisitos(getCadeira("IC"));
		getCadeira("LPROG2").addListaRequisitos(getCadeira("PROG2").getRequisitos());
		getCadeira("GRAFOS").addRequisitos(getCadeira("PROG1"));
		getCadeira("GRAFOS").addRequisitos(getCadeira("LPROG1"));
		getCadeira("FFM").addRequisitos(getCadeira("CALC I"));
		getCadeira("FFM").addRequisitos(getCadeira("VETORIAL"));
		
		// 3 periodo
		getCadeira("EDA").addRequisitos(getCadeira("PROG2"));
		getCadeira("EDA").addRequisitos(getCadeira("LPROG2"));
		getCadeira("EDA").addRequisitos(getCadeira("GRAFOS"));
		getCadeira("LEDA").addListaRequisitos(getCadeira("EDA").getRequisitos());
		getCadeira("LINEAR").addRequisitos(getCadeira("VETORIAL"));
		getCadeira("PROBABILIDADE").addRequisitos(getCadeira("CALC II"));
		getCadeira("TC").addRequisitos(getCadeira("GRAFOS"));
		getCadeira("TC").addRequisitos(getCadeira("DISCRETA"));
		getCadeira("TC").addRequisitos(getCadeira("IC"));
		getCadeira("FFM").addRequisitos(getCadeira("FFC"));
		getCadeira("FFM").addRequisitos(getCadeira("CALC II"));
		
		// 4 periodo
		getCadeira("OAC").addRequisitos(getCadeira("EDA"));
		getCadeira("OAC").addRequisitos(getCadeira("LEDA"));
		getCadeira("OAC").addRequisitos(getCadeira("FFM"));
		getCadeira("LOAC").addListaRequisitos(getCadeira("OAC").getRequisitos());
		getCadeira("PLP").addRequisitos(getCadeira("EDA"));
		getCadeira("PLP").addRequisitos(getCadeira("LEDA"));
		getCadeira("PLP").addRequisitos(getCadeira("TC"));
		getCadeira("METODOS").addRequisitos(getCadeira("PROBABILIDADE"));
		getCadeira("METODOS").addRequisitos(getCadeira("LINEAR"));
		getCadeira("LOGICA").addRequisitos(getCadeira("TC"));
		getCadeira("SI I").addRequisitos(getCadeira("GI"));
		getCadeira("ES I").addRequisitos(getCadeira("PROBABILIDADE"));
		getCadeira("ES I").addRequisitos(getCadeira("PROG2"));
		getCadeira("ES I").addRequisitos(getCadeira("LPROG2"));
		
		// 5 periodo
		
		getCadeira("ATAL").addRequisitos(getCadeira("EDA"));
		getCadeira("ATAL").addRequisitos(getCadeira("LEDA"));
		getCadeira("ATAL").addRequisitos(getCadeira("CALC II"));
		getCadeira("ATAL").addRequisitos(getCadeira("LOGICA"));
		
		getCadeira("REDES").addRequisitos(getCadeira("OAC"));
		getCadeira("REDES").addRequisitos(getCadeira("LOAC"));
		getCadeira("COMPILADORES").addListaRequisitos(getCadeira("REDES").getRequisitos());
		getCadeira("COMPILADORES").addRequisitos(getCadeira("PLP"));
		getCadeira("BD I").addRequisitos(getCadeira("SI I"));
		getCadeira("SI II").addRequisitos(getCadeira("SI I"));
		getCadeira("LES").addRequisitos(getCadeira("ES I"));
		
		// 6 periodo
		getCadeira("SO").addListaRequisitos(getCadeira("REDES").getRequisitos());
		getCadeira("IRC").addRequisitos(getCadeira("REDES"));
		getCadeira("LIRC").addRequisitos(getCadeira("REDES"));
		getCadeira("IA").addRequisitos(getCadeira("METODOS"));
		getCadeira("IA").addRequisitos(getCadeira("PLP"));
		getCadeira("IA").addRequisitos(getCadeira("ATAL"));
		getCadeira("BD II").addRequisitos(getCadeira("BD I"));
		getCadeira("BD II").addRequisitos(getCadeira("SI II"));
		
		// 7 periodo
		getCadeira("MSN").addRequisitos(getCadeira("ATAL"));
		getCadeira("MSN").addRequisitos(getCadeira("LINEAR"));
		getCadeira("ADSD").addRequisitos(getCadeira("PROBABILIDADE"));
		getCadeira("PROJETO I").addRequisitos(getCadeira("LES"));
		getCadeira("PROJETO I").addRequisitos(getCadeira("MC"));
		
		// 8 periodo
		getCadeira("PROJETO II").addRequisitos(getCadeira("PROJETO I"));		
	}
	
	
	/**
	 * Retorna uma Disciplina, achando ela pelo nome. Null caso a disciplina não
	 * seja encontrada.
	 * @param String contendo o nome da cadeira a ser pesquisada
	 * @return Objeto do tipo Disciplina. Null caso nada seja encontrado.
	 */
	public Disciplina getCadeira(String string) {
		for(Disciplina d : disciplinas){
			if(d.getNomeCadeira().trim().toLowerCase().equals(string.trim().toLowerCase())){
				return d;
			}
		}
		return null;
	}
	
	/**
	 * Adiciona uma nova cadeira aos requisitos
	 * @param nome
	 * @param creditos
	 * @param requisitos
	 */
	public void addCadeira(String nome, int creditos){
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Disciplina novaCadeira = new Disciplina(nome, creditos);
		if (!disciplinas.contains(novaCadeira)){
			disciplinas.add(novaCadeira);
			for (Disciplina d : requisitos) {
				novaCadeira.addRequisitos(d);
			}
		}	
	}
	
	/**
	 * Retorna o array com todas as disciplinas
	 * @return
	 */
	public List<Disciplina> getCadeiras(){
		return disciplinas;
	}

}
