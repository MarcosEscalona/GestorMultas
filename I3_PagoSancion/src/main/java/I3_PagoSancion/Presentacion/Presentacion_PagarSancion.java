/**
 Clase: Presentacion_PagarSancion
 Fecha: 06/12/2016
 Version: 2.0.0
 Novedades version 2.0.0:
   - Cambio de estructura de Clase, de JFrame a JPanel
 Novedades version 1.0.0:
   - Creacion de la interfaz grafica de usario y añadirle los componentes gráficos
   - Creacion de la actividad en el evento de pulsar el boton btnPagarSancion
 **/
package I3_PagoSancion.Presentacion;

import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.I1_AperturaExpedientes.Persistencia.*;
//import Hito3.Presentacion.Presentacion_PagarSancion2.BtnPagarSancionActionListener;


import I3_PagoSancion.Dominio.Dominio_PagodeSancion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

@SuppressWarnings("serial")
public class Presentacion_PagarSancion extends JPanel {

	static Dominio_PagodeSancion sancio;
	private static DefaultTableModel dtm;
	private JPanel pnlPagarSancion;
	private JPanel pnlBoton;
	private JPanel pnlPrincipal;
	private JButton btnPagarSancion;
	private JScrollPane scrollPane;
	private static JTable tblPagarSanciones;
	ConectorBBDD conexion;

	/**
	 * Create the panel.
	 */
	public Presentacion_PagarSancion(ConectorBBDD conexion) {
		this.conexion = conexion;
		sancio = new Dominio_PagodeSancion(conexion);
		setLayout(new BorderLayout(0, 0));

		pnlPagarSancion = new JPanel();
		add(pnlPagarSancion);
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

		String header[] = new String[] { "ID Sancion", "Matricula", "Fecha y Hora", "Conductor", "Puntos a Restar",
				"Importe" };

		dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);

		tblPagarSanciones.setModel(dtm);

	}

	/**
	 * Boton Pagar Sancion
	 **/
	private class BtnPagarSancionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int idSancion;
			idSancion = Integer.parseInt((String) tblPagarSanciones.getModel().getValueAt(tblPagarSanciones.getSelectedRow(), 0));
		
			Dominio_PagodeSancion sancio = new Dominio_PagodeSancion(conexion);
			sancio.pagarSancion(idSancion);
			cargarDatos();
		}
	}

	/**
	 * Cargar Datos de Sanciones
	 **/
	public static void cargarDatos() {

		int rowCount = dtm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		
		String[][] datos = sancio.devolverDatosSanciones();
		if (datos != null)
			for (int i = 0; i < datos.length; i++) {
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
