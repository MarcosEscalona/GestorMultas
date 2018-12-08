/**
 Clase: Presentacion_VerActividad
 Fecha: 06/12/2016
 Version: 2.1.0
 Novedades version 2.1.0 :
   -Creacion del metodo para parar el dominio
 Novedades version 2.0.1 :
   - Optimizacion y eliminacion de importaciones innecesarias
 Novedades version 2.0.0:
   - Cambio de estructura de Clase, de JFrame a JPanel
 Novedades version 1.0.2:
   -Renombrado de la clase a Presentacion_VerActividad
 Novedades version 1.0.1:
   -Arreglado formato reloj
 Novedades version 1.0.0:
   -Creacion de la ventana de Presentacion y los componentes que la integran
 */

package org.I1_AperturaExpedientes.Presentacion;

import org.I1_AperturaExpedientes.Dominio.Dominio_AperturaExpediente;
import org.I1_AperturaExpedientes.Persistencia.ConectorBBDD;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class Presentacion_VerActividad extends JPanel {
	private JPanel pnlVerActividad;
	private JScrollPane scrollPane;
	private static JTable tblActividad;
	private static DefaultTableModel dtm;
	Dominio_AperturaExpediente Dominio;
	ConectorBBDD conexion;

	/**
	 * Crear Panel
	 */
	public Presentacion_VerActividad(ConectorBBDD conexion) {
		this.conexion = conexion;
		setLayout(new BorderLayout(0, 0));
		{
			pnlVerActividad = new JPanel();
			add(pnlVerActividad);
			pnlVerActividad.setLayout(new BorderLayout(0, 0));
			
				scrollPane = new JScrollPane();
				pnlVerActividad.add(scrollPane, BorderLayout.CENTER);
			
			
				tblActividad = new JTable();
				tblActividad.setEnabled(false);
				tblActividad.setColumnSelectionAllowed(true);

				String header[] = new String[] { "Fecha", "Hora", "Matricula", "DNI Propietario", "Velocidad MAX",
						"Velocidad", "Direcci\u00F3n", "Infracci\u00F3n" };

				dtm = new DefaultTableModel(0, 0);
				dtm.setColumnIdentifiers(header);

				tblActividad.setModel(dtm);

				tblActividad.getColumnModel().getColumn(3).setPreferredWidth(87);
				tblActividad.getColumnModel().getColumn(4).setPreferredWidth(91);
				scrollPane.setViewportView(tblActividad);
			
		}
		Dominio = new Dominio_AperturaExpediente(conexion) ;
		Dominio.start();
	}

	/**
	 * Insertar Foto en Actividad.
	 */
	@SuppressWarnings("deprecation")
	public static void insertarFoto(Date Fecha, String Matricula, String dniPropietario, int VelMAX, int Vel,
			String Direccion, boolean Infraccion) {

		Vector<Object> data = new Vector<Object>();
		data.add(Fecha.getDate() + "/" + Fecha.getMonth() + "/" + (1900 + Fecha.getYear()));
		data.add(Fecha.getHours() + ":" + Fecha.getMinutes() + ":" + Fecha.getSeconds());
		data.add(Matricula);
		data.add(dniPropietario);
		data.add("" + VelMAX);
		data.add("" + Vel);
		data.add(Direccion);
		if (Infraccion)
			data.add("SI");
		else
			data.add("NO");
		dtm.addRow(data);

		tblActividad.scrollRectToVisible(tblActividad.getCellRect(tblActividad.getRowCount() - 1, 0, true));
	}

	@SuppressWarnings("deprecation")
	public void stopDominio(){
		Dominio.stop();
	}
}
