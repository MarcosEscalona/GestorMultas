/**
 Clase: Conductor
 Fecha: 20/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor y generacion de los metodos get y set
   - Crecion del metodo restarPuntos(int puntosmenos)
 **/
package Hito2.Dominio;


public class Conductor {

	private String dni;
	private int puntos;
	
	
	/**
	 * Constructor
	 */
	public Conductor(String dni, int puntos) {
		this.dni = dni;
		this.puntos = puntos;
	}


	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}


	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}


	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}


	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	/**
	 * Metodo que le resta los puntos al conductor
	 * @param puntosmenos
	 */
	public void restarPuntos(int puntosmenos){
		if(this.puntos-puntosmenos <= 0){
			this.puntos = 0;
		}else{
			this.puntos = this.puntos - puntosmenos;
		}
	}
	
	

}
