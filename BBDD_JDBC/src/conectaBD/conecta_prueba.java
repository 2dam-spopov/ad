package conectaBD;
import java.sql.*;

public class conecta_prueba {

	public static void main(String[] args) {
		
		//1. CREA CONEXIÓN.
		
		try {
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba?useSSL=false","root","cbb74bc");
			
		//2. CREAR OBJETO STATEMENT.
			
			Statement miStatement = miConexion.createStatement();
			
			
		//3. EJECUTAR SQL.	
			
			ResultSet miResultSet = miStatement.executeQuery("select * from productos");
			
		//4. LEER RESULTSET.
			
		while (miResultSet.next()) {
				System.out.println(miResultSet.getString("NOMBREARTÍCULO")+" "+miResultSet.getString("CÓDIGOARTÍCULO")+" "+miResultSet.getString("PRECIO"));
			}
			
		}catch(Exception e) {
			System.out.println("Error de conexión");
			e.printStackTrace();
		}
		
		
		
		
	}

}
