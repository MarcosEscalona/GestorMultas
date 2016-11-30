/**
 Clase: Presentacion_PagarSancion
 Fecha: 23/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de la interfaz grafica de usario y añadirle los componentes gráficos
   - Creacion de las actividad en el evento de pulsar el boton btnPagarSancion
 **/
package I3_PagoSancion.Presentacion

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
//import java.awt.GridLayout;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.util.Vector;

//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//import java.util.Date;
//import java.util.Vector;

//import Hito1.Dominio.Dominio_AperturaExpediente;
import Hito1.Persistencia.ConectorBBDD;
import Hito2.Dominio.Dominio_CreacionDeSancion;
//import Hito3.Dominio.Dominio_PagodeSancion;
import Hito3.Dominio.Dominio_PagodeSancion;
import javax.swing.ListSelectionModel;

public class Presentacion_PagarSancion {

	private JFrame frmSistemaDeMultas;
	private static JTabbedPane tbPanel1;
	private JLabel lblLogo;
	private static DefaultTableModel dtm ;
	private JPanel pnlPagarSancion;
	private JPanel pnlBoton;
	private JPanel pnlPrincipal;
	private JButton btnPagarSancion;
	private JScrollPane scrollPane;
	private JTable tblPagarSanciones;
	ConectorBBDD conexion;

	/**
	 * Lanzamiento de Aplicacion.
	 */
	/**
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion_PagarSancion window = new Presentacion_PagarSancion();
					window.frmSistemaDeMultas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
**/
	
	/**
	 * Creacion de la Aplicacion.
	 */
	public Presentacion_PagarSancion() {
		initialize();
		frmSistemaDeMultas.setVisible(true);
	}

	/**
	 * Inicializacion del contenido del Frame.
	 */
	private void initialize() {
		/***/
		frmSistemaDeMultas = new JFrame();
		frmSistemaDeMultas.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSistemaDeMultas.setIconImage(Toolkit.getDefaultToolkit().getImage(Presentacion_PagarSancion.class.getResource("/Hito1/Presentacion/icon.png")));
		frmSistemaDeMultas.setTitle("Sistema de Multas ~ D.G.T.");
		frmSistemaDeMultas.setBounds(100, 100, 450, 300);
		frmSistemaDeMultas.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		{
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(Presentacion_PagarSancion.class.getResource("/Hito1/Presentacion/logo.png")));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setFont(new Font("Tahoma", Font.BOLD, 28));
			frmSistemaDeMultas.getContentPane().add(lblLogo, BorderLayout.NORTH);
		}
		/***/
		{
			tbPanel1 = new JTabbedPane(JTabbedPane.TOP);
			tbPanel1.setToolTipText("Pagar Sanciones");
			frmSistemaDeMultas.getContentPane().add(tbPanel1, BorderLayout.CENTER);
			
			pnlPagarSancion = new JPanel();
			tbPanel1.addTab("Pagar Sancion", null, pnlPagarSancion, null);
			pnlPagarSancion.setLayout(new BorderLayout(0, 0));
			
			pnlPrincipal = new JPanel();
			pnlPagarSancion.add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(new BorderLayout(0, 0));
			
			scrollPane = new JScrollPane();
			pnlPrincipal.add(scrollPane);
			
			tblPagarSanciones = new JTable();
			tblPagarSanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tblPagarSanciones);
			
			pnlBoton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlBoton.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlPagarSancion.add(pnlBoton, BorderLayout.SOUTH);
			
			btnPagarSancion = new JButton("Pagar Sancion");
			btnPagarSancion.addActionListener(new BtnPagarSancionActionListener());
			pnlBoton.add(btnPagarSancion);
			
			String header[] = new String[] { "ID Sancion", "Matricula", "Fecha y Hora", "Conductor", "Puntos a Restar", "Importe"};
			
			dtm = new DefaultTableModel(0, 0);
			dtm.setColumnIdentifiers(header);
			
			tblPagarSanciones.setModel(dtm);
			
			conexion= new ConectorBBDD();
			cargarDatos(conexion);
			
		}
	}
	
	
	
	/**
	 * Boton Pagar Sancion
	 **/
	private class BtnPagarSancionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int idSancion ;
			idSancion = Integer.parseInt((String) tblPagarSanciones.getModel().getValueAt(tblPagarSanciones.getSelectedRow(), 0));
			System.out.println(idSancion);
			Dominio_PagodeSancion sancio = new Dominio_PagodeSancion(conexion);
			sancio.pagarSancion(idSancion);
			tblPagarSanciones.remove(tblPagarSanciones.getSelectedRow());
		}
	}

	
	/**
	 * Cargar Datos de Sanciones
	 **/
	public void cargarDatos(ConectorBBDD conexion){
		
		Dominio_PagodeSancion sancio = new Dominio_PagodeSancion(conexion);
		String[][] datos = sancio.devolverDatosSanciones();
		
		for(int i=0; i<datos.length;i++){
			Vector<Object> data = new Vector<Object>();
			
	        data.add(datos[i][0]);
	        data.add(datos[i][1]);
	        data.add(datos[i][2]);
	        data.add(datos[i][3]);
	        data.add(datos[i][4]);
	        data.add(datos[i][5]);
	        
	        dtm.addRow(data); 
	        
		}
		
	}
		
}
