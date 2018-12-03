package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ModificaBBDD {
	public static void main(String[] args) {

		// 1. CREA CONEXIÓN.

		try {
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba?useSSL=false",
					"root", "cbb74bc");

			// 2. CREAR OBJETO STATEMENT.

			Statement miStatement = miConexion.createStatement();

			// INSERTAR.
			
//			String instruccionSql = "insert into productos (CÓDIGOARTÍCULO, NOMBREARTÍCULO,PRECIO) values ('AR77','PANTALÓN',25.35)";
//			miStatement.executeUpdate(instruccionSql);
//			System.out.println("Datos introducidos");
			
			// ACTUALIZAR.
//			String instruccionSql = "update productos set precio=precio*2 where códigoartículo='ar77'";
//			miStatement.executeUpdate(instruccionSql);
//			System.out.println("Datos Modificados");
			
			// BORRAR.
			String instruccionSql = "delete from productos where códigoartículo='ar41'";
			miStatement.executeUpdate(instruccionSql);
			System.out.println("Datos Borrados");

		} catch (Exception e) {
			System.out.println("Error de conexión");
			e.printStackTrace();
		}

	}

}
