package modelo;

import java.sql.*;
import controlador.*;

public class CargaSecciones {

	Conexion miConexion;
	private ResultSet rs;
	public CargaSecciones() {
		miConexion = new Conexion();
	}

	public String ejecutaConsultas() {
		Productos miProducto = null;
		Connection accesoBBDD = miConexion.dameConexion();

		try {
			
			Statement secciones = accesoBBDD.createStatement();
			rs=secciones.executeQuery("select distinctrow sección from productos");
			
			while(rs.next()) {
				miProducto = new Productos();
				miProducto.setSeccion(rs.getString(1));
				
				return miProducto.getSeccion();
				
			}
			rs.close();
			
		}catch(Exception e){
			
		}
		return miProducto.getSeccion();
		
		
	}

}
