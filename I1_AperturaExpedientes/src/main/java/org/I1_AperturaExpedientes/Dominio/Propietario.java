/**
Clase: Propietario
Fecha: 07/11/2016
Version: 1.0.0
Novedades en esta version:
  -Creacion de constructor y generacion de los metodos get y set
*/
package Hito1.Dominio;

public class Propietario {

	String dniPropietario;
	
	public Propietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}

	/**
	 * @return the dniPropietario
	 * Nos devuelve el DNI del propietario del veh�culo que ha excedido la velocidad m�xima
	 * 
	 */
	public String getDniPropietario() {
		return dniPropietario;
	}

	/**
	 * @param dniPropietario the dniPropietario to set
	 * set de la clase Propietario
	 */
	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}
	
	

}
