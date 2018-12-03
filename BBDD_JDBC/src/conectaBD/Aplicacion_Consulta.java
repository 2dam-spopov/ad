package conectaBD;

import javax.swing.*;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame mimarco = new Marco_Aplicacion();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mimarco.setVisible(true);

	}

}

class Marco_Aplicacion extends JFrame {

	public Marco_Aplicacion() {

		setTitle("Consulta BBDD");

		setBounds(500, 300, 400, 400);

		setLayout(new BorderLayout());

		JPanel menus = new JPanel();

		menus.setLayout(new FlowLayout());

		secciones = new JComboBox();

		secciones.setEditable(false);

		secciones.addItem("Todos");

		paises = new JComboBox();

		paises.setEditable(false);

		paises.addItem("Todos");

		resultado = new JTextArea(4, 50);

		resultado.setEditable(false);

		add(resultado);

		menus.add(secciones);

		menus.add(paises);

		add(menus, BorderLayout.NORTH);

		add(resultado, BorderLayout.CENTER);

		JButton botonConsulta = new JButton("Consulta");
		
		//------PONE A LA ESCUCHA EL BOTÓN-----
		
		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
				
			}
			
		});
		

		add(botonConsulta, BorderLayout.SOUTH);
		
		
		

		// --------CONEXIÓN CON BBDD------------

		try {

			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba?useSSL=false", "root",
					"cbb74bc");

			Statement sentencia = miConexion.createStatement();

			String consulta;
			ResultSet rs;

			// ------CARGA JCOMBOBOX SECCIÓN------

			consulta = "select distinctrow sección from productos";

			rs = sentencia.executeQuery(consulta);

			while (rs.next()) {
				secciones.addItem(rs.getString(1));
			}
			rs.close();

			// ------CARGA JCOMBOBOX PAISES------

			consulta = "select distinctrow paísdeorigen from productos";

			rs = sentencia.executeQuery(consulta);

			while (rs.next()) {
				paises.addItem(rs.getString(1));
			}
			rs.close();

		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	private void ejecutaConsulta() {

		ResultSet rs = null;

		try {

			resultado.setText("");
			
			String seccion = (String) secciones.getSelectedItem();

			String pais=(String)paises.getSelectedItem();
			
			
			if(!seccion.equals("Todos")&&pais.equals("Todos")) {
			
			enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);

			enviaConsultaSeccion.setString(1, seccion);

			rs = enviaConsultaSeccion.executeQuery();
			
			} else if(seccion.equals("Todos")&&!pais.equals("Todos")){
				
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);

				enviaConsultaPais.setString(1, pais);

				rs = enviaConsultaSeccion.executeQuery();
				
			} else if (!seccion.equals("Todos")&&!pais.equals("Todos")) {
			
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);

				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setString(2, pais);

				rs = enviaConsultaTodos.executeQuery();
				
			}
			
			
			while (rs.next()) {
				resultado.append(rs.getString(1));
				resultado.append("          ");
				resultado.append(rs.getString(2));
				resultado.append("          ");
				resultado.append(rs.getString(3));
				resultado.append("          ");
				resultado.append(rs.getString(4));
				resultado.append("\n");
			}

		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	private Connection miConexion;

	private PreparedStatement enviaConsultaSeccion;
	
	private PreparedStatement enviaConsultaPais;
	
	private PreparedStatement enviaConsultaTodos;

	private final String consultaSeccion = "select nombreartículo, sección, precio, paísdeorigen from productos where sección=?";

	private final String consultaPais = "select nombreartículo, sección, precio, paísdeorigen from productos where paísdeorigen=?";
	
	private final String consultaTodos = "select nombreartículo, sección, precio, paísdeorigen from productos where sección=? paísdeorigen=?";
	
	private JComboBox secciones;

	private JComboBox paises;

	private JTextArea resultado;

}
