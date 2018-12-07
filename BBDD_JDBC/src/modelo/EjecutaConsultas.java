package modelo;
import java.sql.*;

public class EjecutaConsultas {

	private Conexion miConexion;
	private ResultSet rs;
	private PreparedStatement enviaConsultaSeccion;
	private final StringConsultaSeccion="select nombreartículo, sección, precio,paísdeorigen from productos where sección=?";
	//private String pruebas;
	
	
	public EjecutaConsultas() {
		miConexion=new Conexion();
	}
	
	public String filtraBBDD(String seccion, String pais) {
		//pruebas="";
		if(!seccion.equals("Todos") && pais.equals("Todos")) {
			
			//pruebas="Has escogido sección";
			
		} else if(seccion.equals("Todos") && !pais.equals("Todos")) {
			
			//pruebas="Has escogido país";
			
		} else {
			
			//pruebas="Has escogido ambos";
			
		}
		
		//return pruebas;
	}
	
	
	
	
	
	
}
