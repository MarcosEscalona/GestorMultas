/**
 Clase: Presentacion_CrearSancion
 Fecha: 06/12/2016
 Version: 2.0.1
 Novedades version 2.0.1 :
   -Optimizacion y eliminacion de importaciones innecesarias
 Novedades version 2.0.0:
   - Cambio de estructura de Clase, de JFrame a JPanel
 Novedades version 1.0.0:
   - Creacion de la interfaz grafica de usario y añadirle los componentes gráficos
   - Creacion de la actividad en el evento de pulsar el boton btnProcesar
 **/
package org.I2_SancionConductor.Presentacion;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import org.I2_SancionConductor.Dominio.Dominio_CreacionDeSancion;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import org.I1_AperturaExpedientes.Persistencia.*;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class Presentacion_CrearSancion extends JPanel {

	ConectorBBDD conexion;
	private JPanel pnlSanciones;
	private JButton btnProcesar;
	private JLabel lblDni;
	private JTextField txF_DNI;
	private static Dominio_CreacionDeSancion Dominio;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox;
	private JPanel panel;
	private JPanel pnlBotones;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public Presentacion_CrearSancion(ConectorBBDD conexion) {
		this.conexion = conexion;
		Dominio = new Dominio_CreacionDeSancion(conexion);

		setLayout(new BorderLayout(0, 0));
		{
			pnlSanciones = new JPanel();
			add(pnlSanciones);
			pnlSanciones.setLayout(new GridLayout(2, 0, 0, 0));

			panel = new JPanel();
			pnlSanciones.add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 50, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			
			lblNewLabel = new JLabel("ID Expediente, Matricula, Fecha");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			panel.add(lblNewLabel, gbc_lblNewLabel);

			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			panel.add(comboBox, gbc_comboBox);

			lblDni = new JLabel("DNI Conductor:");
			GridBagConstraints gbc_lblDni = new GridBagConstraints();
			gbc_lblDni.insets = new Insets(0, 0, 0, 5);
			gbc_lblDni.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblDni.gridx = 0;
			gbc_lblDni.gridy = 3;
			panel.add(lblDni, gbc_lblDni);

			txF_DNI = new JTextField();
			GridBagConstraints gbc_txF_DNI = new GridBagConstraints();
			gbc_txF_DNI.anchor = GridBagConstraints.NORTHWEST;
			gbc_txF_DNI.gridx = 1;
			gbc_txF_DNI.gridy = 3;
			panel.add(txF_DNI, gbc_txF_DNI);
			txF_DNI.setColumns(10);

			pnlBotones = new JPanel();
			pnlSanciones.add(pnlBotones);

			btnProcesar = new JButton("Procesar");
			pnlBotones.add(btnProcesar);
			btnProcesar.addActionListener(new BtnProcesarActionListener());

		}
	}

	private class BtnProcesarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String exp = comboBox.getSelectedItem().toString();
			String strexp[] = exp.split(", ");

			Dominio.insertarSancion(Integer.parseInt(strexp[0]), txF_DNI.getText());
			txF_DNI.setText("");
			cargarExpediente();
		}
	}

	@SuppressWarnings("unchecked")
	public static void cargarExpediente() {
		comboBox.removeAllItems();
		String[][] expedientes = Dominio.getExpedientes();
		if (expedientes != null)
			for (int i = 0; i < expedientes.length; i++)
				comboBox.addItem(expedientes[i][0] + ", " + expedientes[i][1] + ", " + expedientes[i][2]);
	}
}
