/**
 Clase: Presentacion_VerActividad
 Fecha: 20/11/2016
 Version: 1.0.2 
 Novedades version 1.0.2:
   -Renombrado de la clase a Presentacion_VerActividad
 Novedades version 1.0.1:
   -Arreglado formato reloj
 Novedades version 1.0.0:
   -Creacion de la ventana de Presentacion y los componentes que la integran
 */

package Hito1.Presentacion;

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

import java.util.Date;
import java.util.Vector;

import Hito1.Dominio.Dominio_AperturaExpediente;
import Hito1.Persistencia.ConectorBBDD;

public class Presentacion_VerActividad {

	private JFrame frmSistemaDeMultas;
	private JTabbedPane tbPanel1;
	private JPanel pnlVerActividad;
	private JLabel lblLogo;
	private JScrollPane scrlPanel;
	private static JTable tblActividad;
	private static DefaultTableModel dtm ;
	ConectorBBDD conexion;

	/***
	 * Creacion de la Aplicacion.
	 */
	public Presentacion_VerActividad(JFrame frmSistemaDeMultas) {
		this.frmSistemaDeMultas = frmSistemaDeMultas;
		initialize();
		frmSistemaDeMultas.setVisible(true);
		
	}

	/**
	 * Inicializacion del contenido del Frame.
	 */
	private void initialize() {
			tbPanel1 = new JTabbedPane(JTabbedPane.TOP);
			tbPanel1.setToolTipText("Ver Actividad de los Radares");
			frmSistemaDeMultas.getContentPane().add(tbPanel1, BorderLayout.CENTER);
			pnlVerActividad = new JPanel();
			tbPanel1.addTab("Ver Actividad", null, pnlVerActividad, null);
			
			tbPanel1.setMnemonicAt(0, KeyEvent.VK_A);
			
			pnlVerActividad.setLayout(new BorderLayout(0, 0));
			scrlPanel = new JScrollPane();
			scrlPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			pnlVerActividad.add(scrlPanel, BorderLayout.CENTER);
			tblActividad = new JTable();
			tblActividad.setEnabled(false);
			tblActividad.setColumnSelectionAllowed(true);
			
			String header[] = new String[] { "Fecha", "Hora", "Matricula", "DNI Propietario", "Velocidad MAX", "Velocidad", "Direcci\u00F3n", "Infracci\u00F3n" };
			
			dtm = new DefaultTableModel(0, 0);
			dtm.setColumnIdentifiers(header);
			
			tblActividad.setModel(dtm);
			
			tblActividad.getColumnModel().getColumn(3).setPreferredWidth(87);
			tblActividad.getColumnModel().getColumn(4).setPreferredWidth(91);
			scrlPanel.setViewportView(tblActividad);
	}
	
	/**
	 * Insertar Foto en Actividad.
	 */
	@SuppressWarnings("deprecation")
	public static void insertarFoto(Date Fecha, String Matricula,String dniPropietario ,int VelMAX, int Vel, String Direccion, boolean Infraccion){
		
        Vector<Object> data = new Vector<Object>();
        data.add(Fecha.getDate()+"/"+Fecha.getMonth()+"/"+(1900+Fecha.getYear()));
        data.add(Fecha.getHours()+":"+Fecha.getMinutes()+":"+Fecha.getSeconds());
        data.add(Matricula);
        data.add(dniPropietario);
        data.add(""+VelMAX);
        data.add(""+Vel);
        data.add(Direccion);
        if(Infraccion)
        	data.add("SI");
        else
        	data.add("NO");
        dtm.addRow(data); 
        
        tblActividad.scrollRectToVisible(tblActividad.getCellRect(tblActividad.getRowCount()-1, 0, true));
	}	
}
