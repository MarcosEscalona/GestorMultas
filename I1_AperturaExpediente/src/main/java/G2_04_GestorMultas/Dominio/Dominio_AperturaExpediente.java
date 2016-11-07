/**
 * 
 */
package Hito1.Dominio;

import java.util.Date;
import Hito1.Persistencia.ConectorBBDD;
import Hito1.Presentacion.AppDGT;

/**
 * Clase que hereda de hilos y genera fotos aleatorias
 *
 */
public class Dominio_AperturaExpediente extends Thread {
	
	ConectorBBDD conexion;
	
	public Dominio_AperturaExpediente(ConectorBBDD conexion) {
		super();
		this.conexion = conexion;
	}
	
	/**
	 * Método que cada cierto tiempo genera fotos y si sobrepasa la velocidad máxima Abre un expediente y lo muestra 
	 */
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
				
				AppDGT.insertarFoto(new Date(), aux.getV().getMatricula(), aux.getV().getPropietario().getDniPropietario(),aux.getR().getVelocidadMaxima(), aux.getVelocidad(), aux.getR().getLocalizacion(), infraccion);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * Método necesario para trabajacon hilos
	 */
	@Override
	public void run() {
		generarFotos();
	}
	
	
		
	

}
