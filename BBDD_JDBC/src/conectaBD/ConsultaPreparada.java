package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConsultaPreparada {
public static void main(String[] args) {
		
		//1. CREA CONEXIÓN.
		
		try {
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba?useSSL=false","root","cbb74bc");
			
		//2. PREPARA CONSULTA.
			
			PreparedStatement miSentencia=miConexion.prepareStatement("select nombreartículo, sección, paísdeorigen from productos where sección=? and paísdeorigen=?");
			
			
			
		//3. ESTABLECER PARÁMETROS DE CONSULTA.
			
			miSentencia.setString(1, "deportes");
			miSentencia.setString(2, "usa");
			
		//4. EJECUTAR Y RECORRER LA CONSULTA.
			
			ResultSet rs = miSentencia.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2)+" "+rs.getString(3));
			}
			rs.close();
		
		//5. REUTILIZACIÓN DE CONSULTA SQL.
			
			System.out.println("\nEjecución segunda consulta \n");
			
			miSentencia.setString(1, "cerámica");
			miSentencia.setString(2, "china");
			
			rs = miSentencia.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2)+" "+rs.getString(3));
			}
			rs.close();
			
		}catch(Exception e) {
			System.out.println("Error de conexión");
			e.printStackTrace();
		}
		
		
		
		
	}
}
