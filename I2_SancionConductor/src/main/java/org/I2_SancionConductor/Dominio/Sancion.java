/**
 Clase: Sancion
 Fecha: 20/11/2016
 Version: 1.0.0
 Novedades en esta version:
   - Creacion de constructor y generacion de los metodos get y set
   - Crecion de los metodos calcularImporte(int limite, int velocidad) y calcularRestaPuntos(int limite, int velocidad)
 **/
package Hito2.Dominio;


public class Sancion {

	private Conductor con;
	private Expediente expediente;
	private int importe;
	private int puntosrestar;
	private boolean pagado;
	
	
	/**
	 * Constructor
	 */
	public Sancion(Conductor con, Expediente expediente) {
		this.con = con;
		this.expediente = expediente;
		this.importe = calcularImporte(expediente.getR().getVelocidadMaxima(), expediente.getVelocidad());
		this.puntosrestar = calcularRestaPuntos(expediente.getR().getVelocidadMaxima(), expediente.getVelocidad());
		this.pagado = false;
	}

	

	/**
	 * @return the puntosrestar
	 */
	public int getPuntosrestar() {
		return puntosrestar;
	}



	/**
	 * @param puntosrestar the puntosrestar to set
	 */
	public void setPuntosrestar(int puntosrestar) {
		this.puntosrestar = puntosrestar;
	}



	/**
	 * @return the con
	 */
	public Conductor getCon() {
		return con;
	}


	/**
	 * @param con the con to set
	 */
	public void setCon(Conductor con) {
		this.con = con;
	}


	/**
	 * @return the expediente
	 */
	public Expediente getExpediente() {
		return expediente;
	}


	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}


	/**
	 * @return the importe
	 */
	public int getImporte() {
		return importe;
	}


	/**
	 * @param importe the importe to set
	 */
	public void setImporte(int importe) {
		this.importe = importe;
	}


	/**
	 * @return the pagado
	 */
	public boolean isPagado() {
		return pagado;
	}


	/**
	 * @param pagado the pagado to set
	 */
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	
	/**
	 * Metodo que calcula el importe que se debe pagar como multa de la sancion
	 * @param limite
	 * @param velocidad
	 * @return
	 */
	public int calcularImporte(int limite, int velocidad){
		
		int diferencia = velocidad-limite;
		
		
		if(limite == 20 || limite == 30 || limite == 40){
			if(diferencia <= 19){
				return 100;
			}else{
				if(diferencia <= 29){
					return 300;
				}else{
					if(diferencia <= 39){
						return 400;
					}else{
						if(diferencia <= 49){
							return 500;
						}else{
							return 600;
						}
					}
				}
			}
		}else{
			if(diferencia <= 29){
				return 100;
			}else{
				if(diferencia <= 49){
					return 300;
				}else{
					if(diferencia <= 59){
						return 400;
					}else{
						if(diferencia <= 69){
							return 500;
						}else{
							return 600;
						}
					}
				}
			}
		}
		
	}
	
	
	/**
	 * Metodo que calcula cuantos puntos hay que restarle al conductor de la sancion
	 * @param limite
	 * @param velocidad
	 * @return
	 */
	public int calcularRestaPuntos(int limite, int velocidad){
		
		int diferencia = velocidad-limite;
		
		
		if(limite == 20 || limite == 30 || limite == 40){
			if(diferencia <= 19){
				return 0;
			}else{
				if(diferencia <= 29){
					return 2;
				}else{
					if(diferencia <= 39){
						return 4;
					}else{
						if(diferencia <= 49){
							return 6;
						}else{
							return 6;
						}
					}
				}
			}
		}else{
			if(diferencia <= 29){
				return 0;
			}else{
				if(diferencia <= 49){
					return 2;
				}else{
					if(diferencia <= 59){
						return 4;
					}else{
						if(diferencia <= 69){
							return 6;
						}else{
							return 6;
						}
					}
				}
			}
		}
	}

}
