package Hito1.Presentacion;

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

import java.util.Date;
import java.util.Vector;

import Hito1.Dominio.Dominio_AperturaExpediente;
import Hito1.Persistencia.ConectorBBDD;

public class AppDGT {

	private JFrame frmSistemaDeMultas;
	private JTabbedPane tbPanel1;
	private JPanel pnlVerActividad;
	private JLabel lblLogo;
	private JScrollPane scrlPanel;
	private static JTable tblActividad;
	private static DefaultTableModel dtm ;
	ConectorBBDD conexion;

	/**
	 * Lanzamiento de Aplicacion.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppDGT window = new AppDGT();
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
	public AppDGT() {
		initialize();
	}

	/**
	 * Inicializacion del contenido del Frame.
	 */
	private void initialize() {
		frmSistemaDeMultas = new JFrame();
		frmSistemaDeMultas.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSistemaDeMultas.addWindowListener(new FrmSistemaDeMultasWindowListener());
		frmSistemaDeMultas.setIconImage(Toolkit.getDefaultToolkit().getImage(AppDGT.class.getResource("/Hito1/Presentacion/icon.png")));
		frmSistemaDeMultas.setTitle("Sistema de Multas ~ D.G.T.");
		frmSistemaDeMultas.setBounds(100, 100, 450, 300);
		frmSistemaDeMultas.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		{
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(AppDGT.class.getResource("/Hito1/Presentacion/logo.png")));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setFont(new Font("Tahoma", Font.BOLD, 28));
			frmSistemaDeMultas.getContentPane().add(lblLogo, BorderLayout.NORTH);
		}
		{
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
	}
	
	
	private class FrmSistemaDeMultasWindowListener extends WindowAdapter {
		
		/**
		 * Evento de cierre de Formulario.
		 */
		@Override
		public void windowClosing(WindowEvent arg0) {
			cerrarAplicacion();
		}
		
		/**
		 * Evento de apertura de Formulario.
		 */
		@Override
		public void windowOpened(WindowEvent arg0) {
			conexion= new ConectorBBDD();
			Dominio_AperturaExpediente Dominio = new Dominio_AperturaExpediente(conexion) ;
			Dominio.start();
		}
	}
	
	/**
	 * Metodo de cierre de Aplicacion.
	 */
	private void cerrarAplicacion(){
		if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de que quieres cerrar la aplicación?", "¡Advertencia!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			conexion.cerrarBBDD();
			System.exit(0);
		}
	}
	
	/**
	 * Insertar Foto en Actividad.
	 */
	@SuppressWarnings("deprecation")
	public static void insertarFoto(Date Fecha, String Matricula,String dniPropietario ,int VelMAX, int Vel, String Direccion, boolean Infraccion){
		
        Vector<Object> data = new Vector<Object>();
        data.add(Fecha.getDate()+"/"+Fecha.getMonth()+"/"+Fecha.getYear());
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
