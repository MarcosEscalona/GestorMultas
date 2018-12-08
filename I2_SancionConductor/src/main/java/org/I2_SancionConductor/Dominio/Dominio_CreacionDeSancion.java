/**
 Clase:Dominio_CreacionDeSancion
 Fecha: 20/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor.
   - getExpedientes(), 
   insertarSancion(int idExpediente,
    String dniConductor)
 **/

package org.I2_SancionConductor.Dominio;

import org.I1_AperturaExpedientes.Dominio.Conductor;
import org.I1_AperturaExpedientes.Dominio.Expediente;
import org.I1_AperturaExpedientes.Dominio.Sancion;
//import java.util.Date;
import org.I1_AperturaExpedientes.Persistencia.ConectorBBDD;


/*
 * Clase que hereda de hilos y genera fotos aleatorias
 *
 */
public class Dominio_CreacionDeSancion {

  ConectorBBDD conexion;

  /**
  * Constructor
  */
  
  public Dominio_CreacionDeSancion(ConectorBBDD conexion) {
    super();
    this.conexion = conexion;
  }

  /**
  * Metodo que obtiene una serie de valores de los Expedientes que no han sido sancionados
  *  mediante la llamada al metodo getExpedientesNoSancionados de la conexion con la bbdd 
  * @return
  */
  public String[][] getExpedientes() {
    return conexion.getExpedientesNoSancionados();
  }
  
  /**
  * Metodo que pasado un identidficador de Expediente
  * y el dni de un conductor, genera una sancion
  * la cual, le hes pasada al metodo insertarSancion
  * de la conexion con la bbdd para que la inserte en esta.
  */
  public void insertarSancion(int idExpediente, String dniConductor) {
    Conductor c = conexion.devolverConductor(dniConductor);
    Expediente e = conexion.devolverExpediente(idExpediente);
    Sancion s = new Sancion(c, e);
    conexion.insertarSancion(s);
  }
}
