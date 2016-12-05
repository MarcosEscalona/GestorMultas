/**
 Clase: Dominio_CreacionDeSancion
 Fecha: 5/12/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion del Panel
 **/
package Hito4.Presentacion;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Hito1.Persistencia.ConectorBBDD;
import Hito4.Dominio.Dominio_CambioPropietarioVeh;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class Presentacion_CambioPropietario extends JPanel {
	private JPanel pnlBotones;
	private JPanel pnlFormulario;
	private JButton btnCambiar;
	private JTextField txfMatricula;
	private JTextField txfPropietario;
	private JLabel lblMatricula;
	private JLabel lblPropietario;
	ConectorBBDD conexion;

	/**
	 * Creacion del Panel.
	 */
	public Presentacion_CambioPropietario() {
		setLayout(new GridLayout(2, 0, 0, 0));
		{
			pnlFormulario = new JPanel();
			add(pnlFormulario);
			GridBagLayout gbl_pnlFormulario = new GridBagLayout();
			gbl_pnlFormulario.columnWidths = new int[]{0, 0, 0};
			gbl_pnlFormulario.rowHeights = new int[]{0, 0, 0};
			gbl_pnlFormulario.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_pnlFormulario.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			pnlFormulario.setLayout(gbl_pnlFormulario);
			{
				lblMatricula = new JLabel("Matricula Vehiculo:");
				GridBagConstraints gbc_lblMatricula = new GridBagConstraints();
				gbc_lblMatricula.insets = new Insets(0, 0, 5, 5);
				gbc_lblMatricula.anchor = GridBagConstraints.EAST;
				gbc_lblMatricula.gridx = 0;
				gbc_lblMatricula.gridy = 0;
				pnlFormulario.add(lblMatricula, gbc_lblMatricula);
			}
			{
				txfMatricula = new JTextField();
				GridBagConstraints gbc_txfMatricula = new GridBagConstraints();
				gbc_txfMatricula.anchor = GridBagConstraints.WEST;
				gbc_txfMatricula.insets = new Insets(0, 0, 5, 0);
				gbc_txfMatricula.gridx = 1;
				gbc_txfMatricula.gridy = 0;
				pnlFormulario.add(txfMatricula, gbc_txfMatricula);
				txfMatricula.setColumns(10);
			}
			{
				lblPropietario = new JLabel("DNI Nuevo Propietario:");
				GridBagConstraints gbc_lblPropietario = new GridBagConstraints();
				gbc_lblPropietario.insets = new Insets(0, 0, 0, 5);
				gbc_lblPropietario.anchor = GridBagConstraints.NORTHEAST;
				gbc_lblPropietario.gridx = 0;
				gbc_lblPropietario.gridy = 1;
				pnlFormulario.add(lblPropietario, gbc_lblPropietario);
			}
			{
				txfPropietario = new JTextField();
				GridBagConstraints gbc_txfPropietario = new GridBagConstraints();
				gbc_txfPropietario.anchor = GridBagConstraints.NORTHWEST;
				gbc_txfPropietario.gridx = 1;
				gbc_txfPropietario.gridy = 1;
				pnlFormulario.add(txfPropietario, gbc_txfPropietario);
				txfPropietario.setColumns(10);
			}
		}
		{
			pnlBotones = new JPanel();
			add(pnlBotones);
			{
				btnCambiar = new JButton("Cambiar");
				btnCambiar.addActionListener(new BtnCambiarActionListener());
				pnlBotones.add(btnCambiar);
			}
		}
		
		conexion= new ConectorBBDD();

	}

	
	/**
	 * Oyente del Boton Cambiar
	 */
	private class BtnCambiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Dominio_CambioPropietarioVeh cambioPropietario = new Dominio_CambioPropietarioVeh(conexion);
			cambioPropietario.actualizarPropietarioVehiculo(txfMatricula.getText(), txfPropietario.getText());
		}
	}
}
