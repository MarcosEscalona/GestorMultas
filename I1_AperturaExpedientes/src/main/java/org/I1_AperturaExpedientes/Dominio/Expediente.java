/**
 Clase: Expediente
 Fecha: 06/12/2016
 Version: 1.0.1
 Novedades version 1.0.1:
   - Cambio en el Tipo de Fecha
 Novedades version 1.0.0:
   - Creacion de constructor y generacion de los metodos get y set
 **/

/**
 * Paquetes
 **/
package org.I1_AperturaExpedientes.Dominio;

/**
 * Importaciones
 **/
import org.I1_AperturaExpedientes.Dominio.*;


/**
 * Clase Expediente
 * 
 **/
public class Expediente {
	
	int id;
	private Vehiculo veh;
	private Radar rad;
	private int vel;
	private String fyh;
	

	public Expediente(int id, Vehiculo veh, Radar rad, int vel, String fyh) {
		this.id = id;
		this.veh = veh;
		this.rad = rad;
		this.vel = vel;
		this.fyh = fyh;
	}	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Se devuelve un vehiculo
	 * @return v
	 **/
	public Vehiculo getV() {
		return veh;
	}

	/**
	 * Se asigna un vehiculo
	 * @param v
	 **/
	public void setV(Vehiculo veh) {
		this.veh = veh;
	}

	/**
	 * Se devuelve un radar
	 * @return r 
	 **/
	public Radar getR() {
		return rad;
	}

	/**
	 * Se asigna un radar
	 * @param r
	 **/
	public void setR(Radar rad) {
		this.rad = rad;
	}

	/**
	 * Se devuelve la velocidad
	 * @return Velocidvd
	 **/
	public int getVelocidad() {
		return vel;
	}

	/**
	 * Se asigna la velocidad
	 * @param velocidad
	 **/
	public void setVelocidad(int vel) {
		this.vel = vel;
	}

	/**
	 * Se devuelve la fecha y hora
	 * @return fechayhora
	 **/
	public String getFechayhora() {
		return fyh;
	}

	/**
	 * Se asigna la fecha y hora
	 * @param fechayhora
	 **/
	public void setFechayhora(String fyh) {
		this.fyh = fyh;
	}
	
}