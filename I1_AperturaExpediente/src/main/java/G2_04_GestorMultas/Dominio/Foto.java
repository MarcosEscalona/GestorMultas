/**
 * 
 */
package Hito1.Dominio;

import java.util.Date;

/**
 * Clase que nos permite "sacar" las fotos a los veh�culos
 * 
 *
 */
public class Foto {
	
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
	 * Asiganmos a los atributos los valores de los coches que pasan
	 */
	public Foto(Vehiculo v, Radar r, int velocidad, Date fechayhora) {
		this.v = v;
		this.r = r;
		this.velocidad = velocidad;
		this.fechayhora = fechayhora;
	}


	
	
	/**
	 * @return the v
	 * M�todo que nos devuelve un veh�culo
	 */
	public Vehiculo getV() {
		return v;
	}


	/**
	 * @param v the v to set
	 * setV nos permite modificar un veh�culo 
	 * 
	 */
	public void setV(Vehiculo v) {
		this.v = v;
	}


	/**
	 * @return the r
	 * getR nos devuelve el radar 
	 */
	public Radar getR() {
		return r;
	}


	/**
	 * @param r the r to set
	 * setR nos permite cambiar el radar 
	 */
	public void setR(Radar r) {
		this.r = r;
	}


	/**
	 * @return the maxVelocidad
	 * getVelocidad nos devuelve la velocidad de un  veh�culo 
	 */
	public int getVelocidad() {
		return velocidad;
	}


	/**
	 * @param maxVelocidad the maxVelocidad to set
	 * setVelocidad nos permite 
	 */
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	/**
	 * @return the fechayhora
	 * getFechayHora nos permite ver la fecha y hora a la que se tom� una foto
	 */
	public Date getFechayhora() {
		return fechayhora;
	}


	/**
	 * @param fechayhora the fechayhora to set
	 * set para cambiar la FechayHora
	 */
	public void setFechayhora(Date fechayhora) {
		this.fechayhora = fechayhora;
	}
	
	
	/**
	 * 
	 * @return
	 * M�todo que en funci�n de la velocidad si supera la Velocidad M�xima devuevlve verdadero y si no devuelve falso 
	 * 
	 */
	public boolean esInfraccion(){
		if(velocidad > r.getVelocidadMaxima()){
			return true;
		}
		return false;
	}
	
	

}
