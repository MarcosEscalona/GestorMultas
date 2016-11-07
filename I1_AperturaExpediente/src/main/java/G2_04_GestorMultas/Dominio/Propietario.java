package Hito1.Dominio;
/**
 * Clase Propietario donde almacenamos a los propietarios de los vehículos
 *
 */
public class Propietario {

	String dniPropietario;
	
	public Propietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}

	/**
	 * @return the dniPropietario
	 * Nos devuelve el DNI del propietario del vehículo que ha excedido la velocidad máxima
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
