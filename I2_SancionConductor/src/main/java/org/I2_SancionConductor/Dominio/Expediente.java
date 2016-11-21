/**
 Clase: Expediente
 Fecha: 20/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor y generacion de los metodos get y set
 **/

/**
 * Paquetes
 **/
package Hito2.Dominio;

/**
 * Importaciones
 **/
import java.util.Date;

import Hito1.Dominio.Radar;
import Hito1.Dominio.Vehiculo;

/**
 * Clase Expediente
 * 
 **/
public class Expediente {
	
	int id;
	private Vehiculo v;
	private Radar r;
	private int velocidad;
	private Date fechayhora;
	
	/**
	 * Constructor
	 * @param v
	 * @param r
	 * @param velocidad
	 * @param fechayhora
	 **/
	public Expediente(int id, Vehiculo v, Radar r, int velocidad, Date fechayhora) {
		this.id = id;
		this.v = v;
		this.r = r;
		this.velocidad = velocidad;
		this.fechayhora = fechayhora;
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
		return v;
	}

	/**
	 * Se asigna un vehiculo
	 * @param v
	 **/
	public void setV(Vehiculo v) {
		this.v = v;
	}

	/**
	 * Se devuelve un radar
	 * @return r 
	 **/
	public Radar getR() {
		return r;
	}

	/**
	 * Se asigna un radar
	 * @param r
	 **/
	public void setR(Radar r) {
		this.r = r;
	}

	/**
	 * Se devuelve la velocidad
	 * @return Velocidvd
	 **/
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * Se asigna la velocidad
	 * @param velocidad
	 **/
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Se devuelve la fecha y hora
	 * @return fechayhora
	 **/
	public Date getFechayhora() {
		return fechayhora;
	}

	/**
	 * Se asigna la fecha y hora
	 * @param fechayhora
	 **/
	public void setFechayhora(Date fechayhora) {
		this.fechayhora = fechayhora;
	}
	
}