/**
 Clase: Radar
 Fecha: 06/12/2016
 Version: 1.0.1
 Novedades version 1.0.1:
   - Cambio de tipo Fecha
 Novedades version 1.0.0:
   - Creacion de Clase
 */
package org.I1_AperturaExpedientes.Dominio;

import java.text.ParseException;
import java.util.Date;

import org.I1_AperturaExpedientes.Persistencia.*;
import org.I1_AperturaExpedientes.Presentacion.*;

/**
 * Clase donde creamos el objeto Radar que nos permitir� saber los coches que exceden la velocidad
 *
 */
public class Radar {
	//Atributos necesarios para identificar el radar, configurar la vel. m�xima y la localizaci�n de la misma 
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
	 * M�todo que se conecta con la BBDD y que realiza la foto calculando un n�mero aleatorio, que simula la velocidad del veh�culo, coge un
	 * veh�culo aleatorio al que "saca la foto" y crea una nueva foto con los datos obtenidos anterior mente
	 * @return f devuelve la foto 
	 * @throws ParseException 
	 */
	public Foto realizarFoto(ConectorBBDD conector) throws ParseException{
		
		int velocidad = (int) Math.floor(Math.random()*(20-140+1)+140);		
		Vehiculo v = conector.getVehiculoAleatorio();	
		
		
	//	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 //   String stringdate = dt.format(new Date());
	  //  System.out.println("String.valueOf(date): "+stringdate);

	    
	   // Date date = dt.parse(stringdate);
	    Date date = new Date();
		Foto f = new Foto(v, this, velocidad, date+"");
		
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
	 * m�todo set de la clase Radar para el atributo identificador
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
	 * M�todo que devuelve la localizacion del radar
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
