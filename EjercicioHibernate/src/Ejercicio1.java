import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.hibernate.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ejercicio1 {

      private JFrame frmDepartamentos;
      private JTextField txtPregunta;
      JLabel lblPregunta = new JLabel("Introduce N\u00BA Departamento:");
      JButton btnPregunta = new JButton("Comprobar");
      JLabel lblResultado = new JLabel("Introduce N\u00BA Departamento:");
      JTextArea txtResultado = new JTextArea();
      private SessionFactory sessionFactory;

      //Lanza aplicación
      public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        try {
                             Ejercicio1 window = new Ejercicio1();
                             window.frmDepartamentos.setVisible(true);
                        } catch (Exception e) {
                             e.printStackTrace();
                        }
                  }
            });
      } // Fin Main

      //Constructor
      public Ejercicio1() {
            initialize();
      } 

      //Inicia conexión BBDD
      private void conectar(){
            sessionFactory = SessionFactoryUtil.getSessionFactory();
      }
     
      //Libera recursos de conexión
      private void desconectar(){
            sessionFactory.close();
            sessionFactory = null;
      }
     
      //Salida
      private void salir(){
            if(sessionFactory != null){
                  desconectar();
            }
      }
      //Carga datos en tablas
      private void cargarDatos(int d){
            Iterator<Departamentos> i;
            Iterator<Empleados> iE;
            conectar();
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("FROM Departamentos where dept_no = " + d);
            List<Departamentos> departamentos = query.list();
           
            if (departamentos.isEmpty()){
                  JOptionPane.showMessageDialog(null, "No existe este departamento");
            }
            else{
                 
                  i = departamentos.iterator();
                  while(i.hasNext()){
                        if (!this.lblResultado.isVisible())
                             this.lblResultado.setVisible(true);
                        this.lblResultado.setText("Nombre departamento: " + i.next().getDnombre());
                  }
                 
                  query = session.createQuery("FROM Empleados where dept_no = " + d);
                  List<Empleados> empleados = query.list();
                  this.txtResultado.setVisible(true);
                  this.txtResultado.setText("Numero de empleados: " + empleados.size() + "\n");
                  this.txtResultado.setText(txtResultado.getText() + "----------------------------------------------------\n");
                  this.txtResultado.setText(txtResultado.getText() + "APELLIDO\tOFICIO\n");
                  this.txtResultado.setText(txtResultado.getText() + "----------------------------------------------------\n");
                  iE = empleados.iterator();
                  while(iE.hasNext()){
                        this.txtResultado.setText(this.txtResultado.getText() + iE.next().toString() + "\n");
                  }
            }
            session.close();
      } 
     
      
      //Inicializa contenido del frame
      private void initialize() {
            frmDepartamentos = new JFrame();
            frmDepartamentos.addWindowListener(new WindowAdapter() {
                  @Override
                  public void windowClosing(WindowEvent arg0) {
                        salir();
                  }
            });
            frmDepartamentos.setTitle("Departamentos");
            frmDepartamentos.setBounds(100, 100, 488, 427);
            frmDepartamentos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frmDepartamentos.setLocationRelativeTo(null);
            frmDepartamentos.getContentPane().setLayout(null);
           
           
            lblPregunta.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblPregunta.setBounds(10, 11, 220, 14);
            frmDepartamentos.getContentPane().add(lblPregunta);
           
            txtPregunta = new JTextField();
            txtPregunta.setHorizontalAlignment(SwingConstants.RIGHT);
            txtPregunta.setBounds(232, 10, 86, 20);
            frmDepartamentos.getContentPane().add(txtPregunta);
            txtPregunta.setColumns(10);
           
           
            btnPregunta.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent arg0) {
                        if (txtPregunta.getText().equals("")){
                             JOptionPane.showMessageDialog(null, "Debes poner un número de departamento");
                        }
                        else{
                             int x = Integer.parseInt(txtPregunta.getText());
                             cargarDatos(x);
                        }
                  }
            });
            btnPregunta.setFont(new Font("Tahoma", Font.BOLD, 14));
            btnPregunta.setBounds(335, 9, 127, 23);
            frmDepartamentos.getContentPane().add(btnPregunta);
           
           
            lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblResultado.setBounds(10, 47, 416, 14);
            lblResultado.setVisible(false);
            frmDepartamentos.getContentPane().add(lblResultado);
           
           
            txtResultado.setBounds(10, 72, 452, 305);
            txtResultado.setVisible(false);
            this.txtResultado.setEditable(false);
            frmDepartamentos.getContentPane().add(txtResultado);
      } 
} 