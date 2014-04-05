package models;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings{
	
	private int NUMERO_PERIODOS = 8;
	private final int NUMERO_DE_USUARIOS_PADRAO = 30;

	public Logger log = new Logger();
	@Override
	public void onStart(Application app){
//		povoaBd();
//		log.info("Disciplinas Criadas - BD Povoado");
//		if(Usuario.find.findRowCount() == 0){
//			Usuario admin = new Usuario("admin@admin", "admin", "admin", new PlanoDeCurso());
//			admin.save();
//			//criaTrintaUsuarios();
//		}
	}
	
//	private void criaTrintaUsuarios(){
//		log.info("Usuarios criados");
//		for (int i = 0; i < NUMERO_DE_USUARIOS_PADRAO; i++ ){
//			Usuario user = new Usuario("user"+i+"@email.com" , "user"+i, "user"+i, new PlanoDeCurso());
//			user.save();
//		}
//	}
}
