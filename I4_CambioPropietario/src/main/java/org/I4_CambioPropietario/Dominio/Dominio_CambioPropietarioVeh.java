/**
 Clase: Dominio_CreacionDeSancion
 Fecha: 2/12/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor.
   - Creacion del metodo actualizarPropietatioVehiculos
 **/
package org.I4_CambioPropietario.Dominio;

import javax.swing.JOptionPane;

import org.I1_AperturaExpedientes.Dominio.*;
import org.I1_AperturaExpedientes.Persistencia.ConectorBBDD;

public class Dominio_CambioPropietarioVeh {

	ConectorBBDD conexion;
	
	/**
	 * Constructor de la clase Domino_CambioPropietarioVeh
	 * @param conexion
	 */
	public Dominio_CambioPropietarioVeh(ConectorBBDD conexion) {
		super();
		this.conexion = conexion;
	}
	
	/**
	 * Metodo encargado de crar el vehiculo con los datos actualizados y actualizar su valor en la bd
	 * mediante llamadas a metodos de la clae ConectorBBDD
	 * @param matricula
	 * @param dniPropietario
	 */
	public void actualizarPropietarioVehiculo(String matricula, String dniPropietario){
		
		Vehiculo v = conexion.devolverVehiculo(matricula);
		Propietario p = conexion.devolverPropietario(dniPropietario);
		
		if (v == null) {
			JOptionPane.showMessageDialog(null, "Ese vehiculo no existe");
		}else if(p == null){
			JOptionPane.showMessageDialog(null, "Ese propietario no existe");
		}else{
			v.setPropietario(p);
			conexion.actualizarVehiculos(v);
		}
		
	}

}
