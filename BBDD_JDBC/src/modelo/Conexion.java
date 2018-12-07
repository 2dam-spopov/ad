package modelo;

import java.sql.*;

public class Conexion {

	Connection miConexion = null;

	public Conexion() {

	}

	public Connection dameConexion() {

		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba?useSSL=false", "root",
					"cbb74bc");
		} catch (Exception e) {
			System.out.println("EL ERROR ESTÁ AQUÍ");
			e.printStackTrace();

		}
		return miConexion;
	}
}
