/**
 Clase: Dominio_CreacionDeSancion
 Fecha: 06/12/2016
 Version: 1.0.2
 Novedades version 1.0.2:
   - Limpieza de campos al realizar un cambio de propietario
 Novedades version 1.0.1:
   - Optimizacion y eliminacion de importaciones innecesarias
 Novedades version 1.0.0:
   - Creacion del Panel
 **/
package org.I4_CambioPropietario.Presentacion;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import org.I1_AperturaExpedientes.Persistencia.*;
import org.I4_CambioPropietario.Dominio.*;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Presentacion_CambioPropietario extends JPanel {
	private JPanel pnlBotones;
	private JPanel pnlFormulario;
	private JButton btnCambiar;
	private JTextField txfMatricula;
	private JTextField txfPropietario;
	private JLabel lblMatricula;
	private JLabel lblPropietario;
	ConectorBBDD conexion;
	private JPanel pnlPrincipal;

	/**
	 * Creacion del Panel.
	 */
	public Presentacion_CambioPropietario(ConectorBBDD conexion) {
		this.conexion = conexion ;
		setLayout(new BorderLayout(0, 0));
		{
			pnlPrincipal = new JPanel();
			add(pnlPrincipal);
			pnlPrincipal.setLayout(new GridLayout(2, 0, 0, 0));
			{
				pnlFormulario = new JPanel();
				pnlPrincipal.add(pnlFormulario);
				GridBagLayout gbl_pnlFormulario = new GridBagLayout();
				gbl_pnlFormulario.columnWidths = new int[]{0, 0, 0};
				gbl_pnlFormulario.rowHeights = new int[]{50, 0, 0, 0};
				gbl_pnlFormulario.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				gbl_pnlFormulario.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				pnlFormulario.setLayout(gbl_pnlFormulario);
				{
					lblMatricula = new JLabel("Matricula Vehiculo:");
					GridBagConstraints gbc_lblMatricula = new GridBagConstraints();
					gbc_lblMatricula.insets = new Insets(0, 0, 5, 5);
					gbc_lblMatricula.anchor = GridBagConstraints.EAST;
					gbc_lblMatricula.gridx = 0;
					gbc_lblMatricula.gridy = 1;
					pnlFormulario.add(lblMatricula, gbc_lblMatricula);
				}
				{
					txfMatricula = new JTextField();
					GridBagConstraints gbc_txfMatricula = new GridBagConstraints();
					gbc_txfMatricula.anchor = GridBagConstraints.WEST;
					gbc_txfMatricula.insets = new Insets(0, 0, 5, 0);
					gbc_txfMatricula.gridx = 1;
					gbc_txfMatricula.gridy = 1;
					pnlFormulario.add(txfMatricula, gbc_txfMatricula);
					txfMatricula.setColumns(10);
				}
				{
					lblPropietario = new JLabel("DNI Nuevo Propietario:");
					GridBagConstraints gbc_lblPropietario = new GridBagConstraints();
					gbc_lblPropietario.insets = new Insets(0, 0, 0, 5);
					gbc_lblPropietario.anchor = GridBagConstraints.NORTHEAST;
					gbc_lblPropietario.gridx = 0;
					gbc_lblPropietario.gridy = 2;
					pnlFormulario.add(lblPropietario, gbc_lblPropietario);
				}
				{
					txfPropietario = new JTextField();
					GridBagConstraints gbc_txfPropietario = new GridBagConstraints();
					gbc_txfPropietario.anchor = GridBagConstraints.NORTHWEST;
					gbc_txfPropietario.gridx = 1;
					gbc_txfPropietario.gridy = 2;
					pnlFormulario.add(txfPropietario, gbc_txfPropietario);
					txfPropietario.setColumns(10);
				}
			}
			{
				pnlBotones = new JPanel();
				pnlPrincipal.add(pnlBotones);
				{
					btnCambiar = new JButton("Cambiar");
					btnCambiar.addActionListener(new BtnCambiarActionListener());
					pnlBotones.add(btnCambiar);
				}
			}
		}
	}

	
	/**
	 * Oyente del Boton Cambiar
	 */
	private class BtnCambiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Dominio_CambioPropietarioVeh cambioPropietario = new Dominio_CambioPropietarioVeh(conexion);
			cambioPropietario.actualizarPropietarioVehiculo(txfMatricula.getText(), txfPropietario.getText());
			txfMatricula.setText("");
			txfPropietario.setText("");
		}
	}
}
