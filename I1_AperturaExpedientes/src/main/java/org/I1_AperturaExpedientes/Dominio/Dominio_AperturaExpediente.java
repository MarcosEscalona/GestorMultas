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
package Hito1.Dominio;

/**
 * Importaciones
 **/
import java.util.Date;
import Hito1.Persistencia.ConectorBBDD;
import Hito1.Presentacion.Presentacion_VerActividad;

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
		
		while(true){
			try {
				Thread.sleep((int) Math.floor(Math.random()*(5-10+5)+10)*1000);
				
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
