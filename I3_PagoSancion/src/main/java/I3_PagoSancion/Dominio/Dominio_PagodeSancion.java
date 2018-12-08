/**
 Clase: Dominio_PagodeSancion
 Fecha: 22/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor.
   - Creacion de los metodos devolverDatosSanciones(), pagarSancion(int idSancion) 
 **/
package I3_PagoSancion.Dominio;

import org.I1_AperturaExpedientes.Dominio.Conductor;
import org.I1_AperturaExpedientes.Dominio.Sancion;
import org.I1_AperturaExpedientes.Persistencia.ConectorBBDD;


public class Dominio_PagodeSancion {

	ConectorBBDD conexion;
	
	/**
	 * Constructor
	 */
	public Dominio_PagodeSancion(ConectorBBDD conexion) {
		this.conexion = conexion;
	}
	
	/**
	 * Metodo que devuelve una serie de datos de las sanciones que aun no han sido pagadas haciendo para ello
	 * una llamada al metodo de la conexion devolverDatosSancionesNoPagadas
	 * 
	 * @return
	 */
	public String[][] devolverDatosSanciones(){
		
		return conexion.devolverDatosSancionesNoPagadas();
		
	}
	
	/**
	 * Metodo que actualiza los datos correspondiente en de en la bbdd de que una sancion ha sido pagada
	 * y en caso de que haya que restar puntos al conductor, tambien los resta y actualiza en la bbdd
	 * @param idSancion
	 */
	public void pagarSancion(int idSancion){
		Sancion s = conexion.devolverSancion(idSancion);
		s.setPagado(true);
		conexion.pagarSancion(s);
		
		if(s.getPuntosrestar() > 0){
			Conductor c = s.getCon();
			c.restarPuntos(s.getPuntosrestar());
			conexion.actualizarPuntosConductor(c);
		}		
	}

}
