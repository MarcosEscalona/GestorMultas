/**
 Clase: Vehiculo
 Fecha: 07/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor y generacion de los metodos get y set
 */
package Hito1.Dominio;

public class Vehiculo {

	private String matricula;
	private Propietario propietario;
	
	/**
	 * Constructor
	 * @param matricula
	 * @param dnipropietario
	 */
	public Vehiculo(String matricula, Propietario propietario) {
		this.matricula = matricula;
		this.propietario = propietario;
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the Propietario
	 */
	public Propietario getPropietario() {
		return propietario;
	}

	/**
	 * @param dniPropietario the dniPropietario to set
	 */
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	

}
