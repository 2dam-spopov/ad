package controlador;
import java.awt.event.*;
import modelo.*;
import vista.*;

public class ControladorBotonEjecuta implements ActionListener{

	private Marco_Aplicacion2 elmarco;
	EjecutaConsultas obj=new EjecutaConsultas();
	
	public ControladorBotonEjecuta(Marco_Aplicacion2 elmarco) {
		this.elmarco=elmarco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String seleccionSeccion=(String)elmarco.secciones.getSelectedItem();
		String seleccionPais=(String)elmarco.paises.getSelectedItem();
		
		elmarco.resultado.append(obj.filtraBBDD(seleccionSeccion,seleccionPais));
		elmarco.resultado.append("\n");
	}

}
