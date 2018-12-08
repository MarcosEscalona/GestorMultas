/**
 Clase: Foto
 Fecha: 06/12/2016
 Version: 1.0.1
 Novedades version 1.0.1:
   - Cambio tipo Fecha
 Novedades version 1.0.0:
   - Creacion de constructor y generacion de los metodos get y set
   - Se comprueba si es una infraccion
 **/

/**
 * Paquetes
 **/
package org.I1_AperturaExpedientes.Dominio;


/**
 * Clase que nos permite "sacar" las fotos a los veh�culos
 * 
 *
 **/
public class Foto {
	
	private Vehiculo v;
	private Radar r;
	private int velocidad;
	private String fechayhora;
	
	/**
	 * Constructor
	 * @param v
	 * @param r
	 * @param velocidad
	 * @param fechayhora
	 **/
	public Foto(Vehiculo v, Radar r, int velocidad, String fechayhora) {
		this.v = v;
		this.r = r;
		this.velocidad = velocidad;
		this.fechayhora = fechayhora;
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
	public String getFechayhora() {
		return fechayhora;
	}

	/**
	 * Se asigna la fecha y hora
	 * @param fechayhora
	 **/
	public void setFechayhora(String fechayhora) {
		this.fechayhora = fechayhora;
	}
	
	/**
	 * Se comprueba si la velocidad supera la velocidad maxima permitida
	 * @return boolean
	 **/
	public boolean esInfraccion(){
		if(velocidad > r.getVelocidadMaxima()){
			return true;
		}
		return false;
	}
}
