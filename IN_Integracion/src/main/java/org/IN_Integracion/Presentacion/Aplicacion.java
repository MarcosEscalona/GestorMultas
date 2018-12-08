/**
 Clase: Aplicacion
 Fecha: 06/12/2016
 Version: 1.0.0 
 Novedades version 1.0.0:
   -Creacion de la ventana de Principal y los componentes que la integran
 */

package org.IN_Integracion.Presentacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import org.I1_AperturaExpedientes.Persistencia.*;
import org.I1_AperturaExpedientes.Presentacion.Presentacion_VerActividad;
import org.I2_SancionConductor.Presentacion.*;
import org.I4_CambioPropietario.Presentacion.*;

import I3_PagoSancion.Presentacion.Presentacion_PagarSancion;


import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Aplicacion {

	private boolean change = false;
	private JFrame frmSistemaDeMultas;
	private JTabbedPane tbPanel;
	private JLabel lblLogo;
	ConectorBBDD conexion;
	private JPanel pnlPagarSancion;
	private JPanel pnlCrearSancion;
	private JPanel pnlCambiarPropietario;
	private JPanel pnlVerActividad;

	/**
	 * Lanzamiento de Aplicacion.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frmSistemaDeMultas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creacion de la Aplicacion.
	 */
	public Aplicacion() {
		conexion = new ConectorBBDD();
		initialize();
	}

	/**
	 * Inicializacion del contenido del Frame.
	 **/
	private void initialize() {
		frmSistemaDeMultas = new JFrame();
		frmSistemaDeMultas.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSistemaDeMultas.addWindowListener(new FrmSistemaDeMultasWindowListener());
		frmSistemaDeMultas.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Aplicacion.class.getResource("icon.png")));
		frmSistemaDeMultas.setTitle("Sistema de Multas ~ D.G.T.");
		frmSistemaDeMultas.setBounds(100, 100, 450, 300);
		frmSistemaDeMultas.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		{
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(Aplicacion.class.getResource("logo.png")));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setFont(new Font("Tahoma", Font.BOLD, 28));
			frmSistemaDeMultas.getContentPane().add(lblLogo, BorderLayout.NORTH);
		}
		{
			tbPanel = new JTabbedPane(JTabbedPane.TOP);
			tbPanel.addChangeListener(new TbPanelChangeListener());
			frmSistemaDeMultas.getContentPane().add(tbPanel, BorderLayout.CENTER);

			{
				pnlVerActividad = new Presentacion_VerActividad(conexion);
				pnlVerActividad.setToolTipText("Ver Actividad de los Radares");
				tbPanel.addTab("Ver Actividad", null, pnlVerActividad, null);
				tbPanel.setMnemonicAt(0, KeyEvent.VK_A);
			}
			{
				pnlCrearSancion = new Presentacion_CrearSancion(conexion);
				pnlCrearSancion.setToolTipText("Crear una Sancion");
				tbPanel.addTab("Crear Sancion", null, pnlCrearSancion, null);
				tbPanel.setMnemonicAt(1, KeyEvent.VK_S);
			}
			{
				pnlPagarSancion = new Presentacion_PagarSancion(conexion);
				pnlPagarSancion.setToolTipText("Pagar una Sancion");
				tbPanel.addTab("Pagar Sancion", null, pnlPagarSancion, null);
				tbPanel.setMnemonicAt(2, KeyEvent.VK_P);
			}
			{
				pnlCambiarPropietario = new Presentacion_CambioPropietario(conexion);
				pnlCambiarPropietario.setToolTipText("Cambiar Propietario de un Vehiculo");
				tbPanel.addTab("Cambio Propietario", null, pnlCambiarPropietario, null);
				tbPanel.setMnemonicAt(3, KeyEvent.VK_C);
			}
		}
	}

	/**
	 * Evento de cierre de Formulario.
	 **/
	private class FrmSistemaDeMultasWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			cerrarAplicacion();
		}
	}

	/**
	 * Oyente de Refresco de Pestañas
	 **/
	private class TbPanelChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			if (change) {
				Presentacion_CrearSancion.cargarExpediente();
				Presentacion_PagarSancion.cargarDatos();
				////////
			} else
				change = true;
		}
	}

	/**
	 * Metodo de cierre de Aplicacion.
	 **/
	private void cerrarAplicacion() {
		if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de que quieres cerrar la aplicación?", "¡Advertencia!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			conexion.cerrarBBDD();
			((Presentacion_VerActividad) pnlVerActividad).stopDominio();
			System.exit(0);
		}
	}
}