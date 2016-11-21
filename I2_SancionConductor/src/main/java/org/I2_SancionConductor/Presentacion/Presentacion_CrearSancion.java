/**
 Clase: Presentacion_CrearSancion
 Fecha: 20/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de la interfaz grafica de usario y añadirle los componentes gráficos
   - Creacion de las actividad en el evento de pulsar el boton btnProcesar
 **/
package Hito2.Presentacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Hito1.Persistencia.ConectorBBDD;
import Hito2.Dominio.Dominio_CreacionDeSancion;
import Hito2.Dominio.Sancion;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Presentacion_CrearSancion {

	private JFrame frmSistemaDeMultas;
	private static JTabbedPane tbPanel1;
	ConectorBBDD conexion;
	private JPanel pnlSanciones;
	private JButton btnProcesar;
	private JLabel lblDni;
	private JTextField txF_DNI;
	private static Dominio_CreacionDeSancion Dominio;
	private static JComboBox comboBox;

	/**
	 * Creacion de la Aplicacion.
	 */
	public Presentacion_CrearSancion(JFrame frmSistemaDeMultas) {
		this.frmSistemaDeMultas = frmSistemaDeMultas
		initialize();
		frmSistemaDeMultas.setVisible(true);
	}

	/**
	 * Inicializacion del contenido del Frame.
	 */
	private void initialize() {
		{
			tbPanel1 = new JTabbedPane(JTabbedPane.TOP);
			tbPanel1.setToolTipText("Ver Actividad de los Radares");
			frmSistemaDeMultas.getContentPane().add(tbPanel1, BorderLayout.CENTER);
			
			pnlSanciones = new JPanel();
			tbPanel1.addTab("Sanciones", null, pnlSanciones, null);
			GridBagLayout gbl_pnlSanciones = new GridBagLayout();
			gbl_pnlSanciones.columnWidths = new int[]{52, 102, 0, 0, 143, 0};
			gbl_pnlSanciones.rowHeights = new int[]{0, 0, 0, 23, 0, 0};
			gbl_pnlSanciones.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnlSanciones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			pnlSanciones.setLayout(gbl_pnlSanciones);
			
			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			pnlSanciones.add(comboBox, gbc_comboBox);
			
			lblDni = new JLabel("DNI:");
			GridBagConstraints gbc_lblDni = new GridBagConstraints();
			gbc_lblDni.anchor = GridBagConstraints.EAST;
			gbc_lblDni.insets = new Insets(0, 0, 5, 5);
			gbc_lblDni.gridx = 0;
			gbc_lblDni.gridy = 1;
			pnlSanciones.add(lblDni, gbc_lblDni);
			
			txF_DNI = new JTextField();
			GridBagConstraints gbc_txF_DNI = new GridBagConstraints();
			gbc_txF_DNI.insets = new Insets(0, 0, 5, 5);
			gbc_txF_DNI.fill = GridBagConstraints.HORIZONTAL;
			gbc_txF_DNI.gridx = 1;
			gbc_txF_DNI.gridy = 1;
			pnlSanciones.add(txF_DNI, gbc_txF_DNI);
			txF_DNI.setColumns(10);
			
			btnProcesar = new JButton("Procesar");
			btnProcesar.addActionListener(new BtnProcesarActionListener());
			GridBagConstraints gbc_btnProcesar = new GridBagConstraints();
			gbc_btnProcesar.anchor = GridBagConstraints.SOUTHEAST;
			gbc_btnProcesar.gridx = 4;
			gbc_btnProcesar.gridy = 4;
			pnlSanciones.add(btnProcesar, gbc_btnProcesar);
		}
	}
		
	/**
	 * Evento al que se accede cuando el usuario presiona el btnProcesar
	 *
	 */
	private class BtnProcesarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Sancion s = new Sancion(null, null);
			String exp = comboBox.getSelectedItem().toString();
			String strexp[] = exp.split(", ");
			
			Dominio.insertarSancion(Integer.parseInt(strexp[0]), txF_DNI.getText());
			
		}
	}
	
	
	/**
	 * Metodo encargado de cargarlosExpedientes que no hayan sido sancionados
	 */
	public static void cargarExpediente(/*informacion*/){
		String[][] expedientes = Dominio.getExpedientes();
		
		for(int i=0; i<expedientes[0].length; i++)
			comboBox.addItem(expedientes[i][0]+", "+expedientes[i][1]+", "+expedientes[i][2]);
		
	}
	
}
