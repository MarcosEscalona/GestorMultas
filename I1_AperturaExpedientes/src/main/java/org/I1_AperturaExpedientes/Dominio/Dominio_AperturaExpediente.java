/**
 Clase: Dominio_AperturaExpediente
 Fecha: 07/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Generacion de Fotos Aleatorias con Hilos 
 **/

/**
 * Paquetes
 **/

/**
 * Importaciones
 **/


package org.I1_AperturaExpedientes.Dominio;

import java.util.Date;
import org.I1_AperturaExpedientes.Persistencia.ConectorBBDD;
import org.I1_AperturaExpedientes.Presentacion.Presentacion_VerActividad;

/**
 * Generacion de Fotos Aleatorias | Herencia de Hilos
 **/
public class Dominio_AperturaExpediente extends Thread {
	
	ConectorBBDD conexion;
	
	/**
	 * Constructor
	 * @param conexion
	 **/
	public Dominio_AperturaExpediente(ConectorBBDD conexion) {
		super();
		this.conexion = conexion;
	}
	
	/**
	 * Generacion de Fotos Aleatorias cada un tiempo aleatorio
	 * si se sobrepasa la velocidad maxima se inserta la foto 
	 **/
	public void generarFotos(){
		Radar r ;
		Foto aux ;
		boolean infraccion;
		int i = 0;
		
		while(i<999999){
			try {
				i++;
				Thread.sleep((int) Math.floor(Math.random()*(5.0-10.0+5.0)+10.0)*1000.0);
				
				r = conexion.getRadarAleatorio();
				aux = r.realizarFoto(conexion);
				
				infraccion = aux.esInfraccion();
					
				if(infraccion){
					conexion.insertarExpediente(aux);
				}
				
				Presentacion_VerActividad.insertarFoto(new Date(), aux.getV().getMatricula(), aux.getV().getPropietario().getDniPropietario(),aux.getR().getVelocidadMaxima(), aux.getVelocidad(), aux.getR().getLocalizacion(), infraccion);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * Ejecucion de hilos
	 **/
	@Override
	public void run() {
		generarFotos();
	}
}
