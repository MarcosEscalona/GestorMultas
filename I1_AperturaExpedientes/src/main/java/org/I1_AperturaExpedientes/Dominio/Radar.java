/**
 Clase: Radar
 Fecha: 07/11/2016
 Version: 1.0.0
 Novedades en esta version:
   -
   -
   - 
 */
package Hito1.Dominio;

import java.util.Date;

import Hito1.Persistencia.ConectorBBDD;

/**
 * Clase donde creamos el objeto Radar que nos permitirá saber los coches que exceden la velocidad
 *
 */
public class Radar {
	//Atributos necesarios para identificar el radar, configurar la vel. máxima y la localización de la misma 
	private int identificador;
	private int velocidadMaxima;
	private String localizacion;
	
	/**
	 * Constructor
	 * @param identificador
	 * @param velocidadMaxima
	 * @param localizacion
	 */
	public Radar(int identificador, int velocidadMaxima, String localizacion) {
		this.identificador = identificador;
		this.velocidadMaxima = velocidadMaxima;
		this.localizacion = localizacion;
	}
	/**
	 * 
	 * @param conector
	 * Método que se conecta con la BBDD y que realiza la foto calculando un número aleatorio, que simula la velocidad del vehículo, coge un
	 * vehículo aleatorio al que "saca la foto" y crea una nueva foto con los datos obtenidos anterior mente
	 * @return f devuelve la foto 
	 */
	public Foto realizarFoto(ConectorBBDD conector){
		
		int velocidad = (int) Math.floor(Math.random()*(20-140+1)+140);		
		Vehiculo v = conector.getVehiculoAleatorio();		
		Foto f = new Foto(v, this, velocidad, new Date());
		
		return f;
	}

	/**
	 * @return the identificador
	 * obtienes el identificador del radar
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 * método set de la clase Radar para el atributo identificador
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the velocidadMaxima
	 * nos devuelve la verlocidad maxima
	 */
	public int getVelocidadMaxima() {
		return velocidadMaxima;
	}

	/**
	 * @param velocidadMaxima the velocidadMaxima to set
	 * set para el atributo velocidadMaxima
	 * 
	 */
	public void setVelocidadMaxima(int velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	/**
	 * @return the localizacion
	 * Método que devuelve la localizacion del radar
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 * set de la localizacion de la clase Radar
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}	

}
