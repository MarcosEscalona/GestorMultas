/**
 Clase: ConectorBBDD
 Fecha: 06/12/2016
 Version: 1.3.1
  Novedades version 1.3.1 :
   - Optimizacion y eliminacion de importaciones innecesarias
 Novedades version 1.3.0 :
   - Creacion de los metodos actualizarVehiculos() y devolverPropietario
 Novedades version 1.2.0 :
   - Creacion de los metodos devolverDatosSancionesNoPagadas(), actualizarPuntosConductor(),
   		pagarSancion(), devolverSancion()
 Novedades version 1.1.0 :
   - Creacion de los metodos getExpedientesNoSancionados(), existeConductor(Conductor c), insertarSancion(Sancion s),
   		devolverExpediente(int id), devolverVehiculo(String matricula), devolverRadar(int id), 
   		devolverConductor(String dniConductor)
 Novedades version 1.0.0 :
   - Creacion de los metodos ConnectorBBDD(), cerrarBBDD(), getVehiculoAleatorio(), 
    	getRadarAleatorio(), insertarExpediente(Foto aux).
 **/

package org.I1_AperturaExpedientes.Persistencia;

import org.I1_AperturaExpedientes.Dominio.Conductor;
import org.I1_AperturaExpedientes.Dominio.Expediente;
import org.I1_AperturaExpedientes.Dominio.Foto;
import org.I1_AperturaExpedientes.Dominio.Propietario;
import org.I1_AperturaExpedientes.Dominio.Radar;
import org.I1_AperturaExpedientes.Dominio.Sancion;
import org.I1_AperturaExpedientes.Dominio.Vehiculo;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.*;


/**
 * Clase que nos permite conectarnos con la Base de datos donde se guardan los
 * datos de los propietarios de los veh�culos
 **/
public class ConectorBBDD {

	private Connection con;
	private Statement st;
	private ResultSet rs;

	/**
	 * Metodo que nos permite conectarnos con la Base de datos en caso de que no
	 * pueda nos da un error
	 **/
	public ConectorBBDD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String uname = getEncryptedUser();
			String password = getEncryptedPass();

			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba" +
			        "user=" + uname + "&password=" + password);
			
			st = (Statement) con.createStatement();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo para cerrar la conexion con la base de datos despu�s de haberla
	 * usado
	 **/
	public void cerrarBBDD() {
		try {
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error cerrando la bbdd " + e.getMessage());
		}
	}

	/**
	 * M�todo que crea un objeto veh�culo para despu�s usarlo para coger la
	 * matr�cula de un veh�uclo de la base de datos y lo guarda en el objeto
	 * veh�culo creado anteriormente
	 * 
	 * @return v el veh�culo creado
	 */
	public Vehiculo getVehiculoAleatorio() {

		Vehiculo v = null;

		try {

			String consulta = "SELECT * FROM vehiculo ORDER BY RAND() LIMIT 1";
			rs = st.executeQuery(consulta);
			rs.next();
			Propietario propietario = new Propietario(rs.getString("dniPropietario"));
			v = new Vehiculo(rs.getString("matricula"), propietario);

		} catch (Exception e) {
			System.out.println("No se ha podido obtener un vehiculo aleatorio");
		}

		return v;
	}

	/**
	 * Creamos un radar vac�o, para coger un radar aleatorio sacado de la base
	 * de datos y guardarlo en el radar creado anteriormente
	 * 
	 * @return r radar creado
	 */
	public Radar getRadarAleatorio() {

		Radar r = null;

		try {

			String consulta = "SELECT * FROM radar ORDER BY RAND() LIMIT 1";
			rs = st.executeQuery(consulta);
			rs.next();
			r = new Radar(rs.getInt("idRadar"), rs.getInt("velMax"), rs.getString("localizacion"));

		} catch (Exception e) {
			System.out.println("No se ha podido obtener un radar aleatorio " + e);
		}

		return r;

	}

	/**
	 * M�todo para insertar una foto realizada a la base de datos que haya
	 * superado la velocidad m�xima permitida del radar
	 * 
	 * @param aux
	 *            objeto auxiliar de foto que nos servir� para introducir los
	 *            datos del veh�culo que ha superado un velocidad
	 */
	public void insertarExpediente(Foto aux) {
		try {

			String matricula = aux.getV().getMatricula();
			int idRadar = aux.getR().getIdentificador();
			int velocidad = aux.getVelocidad();
			String fechayhora = "" + aux.getFechayhora();
			
			String consulta = "INSERT INTO expedientes VALUES (DEFAULT,'" + matricula + "', " + idRadar + ", "
					+ velocidad + ", '" + fechayhora + "')";
			st.executeUpdate(consulta);

		} catch (Exception e) {
			System.out.println("No se ha podido insertar el Expediente " + e);
		}
	}

	// HITO2

	/**
	 * Metodo que devuelve un array con los datos de los expedientes que aun no
	 * han sido sancionados
	 * 
	 * @return
	 */
	/*
	 * public String[][] getExpedientesNoSancionados() {
	 * 
	 * String[][] idExpedientes = new String[0][0]; int cant = 0;
	 * 
	 * try {
	 * 
	 * String consulta =
	 * "SELECT count(expedientes.idExpediente) as cantidad,expedientes.idExpediente as idExpediente, expedientes.matricula as matricula, expedientes.fechayHora as fechayHora FROM expedientes,sanciones WHERE expedientes.idExpediente != sanciones.idExpediente"
	 * ; rs = st.executeQuery(consulta);
	 * 
	 * System.out.println(rs.getInt("cantidad")); idExpedientes = new
	 * String[rs.getInt("cantidad")][3]; while (rs.next()) {
	 * idExpedientes[cant][0] = rs.getString("idExpediente");
	 * idExpedientes[cant][1] = rs.getString("matricula");
	 * idExpedientes[cant][2] = rs.getString("fechayHora"); cant++; }
	 * 
	 * } catch (Exception e) { System.out.
	 * println("No se ha podido obtener los expedientes no sancionados " +
	 * e.toString()); }
	 * 
	 * return idExpedientes;
	 * 
	 * }
	 */

	public String[][] getExpedientesNoSancionados() {

		String[][] idExpedientes = null;/* = new String[0][0]; */
		int cant = 0;

		try {
			String consulta = "SELECT expedientes.idExpediente as idExpediente, expedientes.matricula as matricula, expedientes.fechayHora as fechayHora FROM expedientes WHERE expedientes.idExpediente NOT IN (SELECT sanciones.idExpediente FROM sanciones)";
			
			rs = st.executeQuery(consulta);
			rs.last();
		
			if (rs.getRow() > 0) {
				
				idExpedientes = new String[rs.getRow()][3];
			
				rs.first();
				do {
					idExpedientes[cant][0] = rs.getString("idExpediente");
					idExpedientes[cant][1] = rs.getString("matricula");
					idExpedientes[cant][2] = rs.getString("fechayHora");
					cant++;
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("No se ha podido obtener los expedientes no sancionados " + e.toString());
		}

		return idExpedientes;

	}

	/**
	 * Metodo que pasado un conductor, comprueba que ese conductor esta en la
	 * bbdd
	 * 
	 * @param c
	 * @return
	 */
	public boolean existeConductor(Conductor c) {
		boolean existe = false;

		try {

			String consulta = "SELECT COUNT(*) AS total FROM conductores WHERE dniConductor = '" + c.getDni() + "'";
			rs = st.executeQuery(consulta);
			rs.next();

			if (rs.getInt("total") > 0) {
				existe = true;
			}

		} catch (Exception e) {
			System.out.println("No se ha podido obtener un radar aleatorio " + e);
		}

		return existe;
	}

	/**
	 * Metodo que inserte en la bbdd una sancion
	 * 
	 * @param s
	 */
	public void insertarSancion(Sancion s) {
		try {

			int pag;

			if (s.isPagado() == false) {
				pag = 0;
			} else {
				pag = 1;
			}
			
			String consulta = "INSERT INTO sanciones VALUES (DEFAULT,'" + s.getCon().getDni() + "', "
					+ s.getExpediente().getId() + ", " + s.getImporte() + ", " + s.getPuntosrestar() + " ," + pag + ")";
			
			st.executeUpdate(consulta);

		} catch (Exception e) {
			System.out.println("No se ha podido insertar la sancion " + e);
		}

	}

	/**
	 * Metodo que devuelve un objeto Expediente buscado en la bbdd por su
	 * identificador
	 * 
	 * @param id
	 * @return
	 */
	public Expediente devolverExpediente(int id) {

		Expediente e = null;
		try {

			String consulta = "SELECT expedientes.matricula as matricula, expedientes.idRadar as idRadar, expedientes.velocidad as velocidad, expedientes.fechayHora as fechayHora FROM expedientes where idExpediente = '" + id + "' ";
			
			rs = st.executeQuery(consulta);
			rs.next();
			
			int iradar = rs.getInt("idRadar");
			int vel = rs.getInt("velocidad");
			String fech = rs.getString("fechayHora");
			
			Vehiculo v = devolverVehiculo(rs.getString("matricula"));
			
			Radar r = devolverRadar(iradar);
			
			e = new Expediente(id, v, r, vel, fech);
		} catch (Exception ex) {
			System.out.println("No se ha podido obtener el expediente " + ex);
		}

		return e;
	}

	/**
	 * Metodo que devuelve un objeto Vehiculo buscado en la bbdd por su
	 * matricula
	 * 
	 * @param matricula
	 * @return
	 */
	public Vehiculo devolverVehiculo(String matricula) {

		Vehiculo v = null;

		try {

			String consulta = "SELECT * FROM vehiculo where matricula = '" + matricula + "' ";
			rs = st.executeQuery(consulta);
			rs.next();
			Propietario p = new Propietario(rs.getString("dniPropietario"));
			v = new Vehiculo(matricula, p);

		} catch (Exception ex) {
			System.out.println("No se ha podido obtener el vehiculo " + ex);
		}

		return v;
	}

	/**
	 * Metodo que devuelve un objeto Radar buscado en la bbdd por su
	 * identificador
	 * 
	 * @param id
	 * @return
	 */
	public Radar devolverRadar(int id) {

		Radar r = null;

		try {

			String consulta = "SELECT * FROM radar where idRadar = " + id + " ";
			rs = st.executeQuery(consulta);
			rs.next();
			r = new Radar(id, rs.getInt("velMax"), rs.getString("localizacion"));

		} catch (Exception ex) {
			System.out.println("No se ha podido obtener el radar " + ex);
		}

		return r;
	}

	/**
	 * Metodo que devuelve un objeto Conductor buscado en la base de datos por
	 * su dni
	 * 
	 * @param dniConductor
	 * @return
	 */
	public Conductor devolverConductor(String dniConductor) {

		Conductor c = null;

		try {

			String consulta = "SELECT puntos FROM conductores where dniConductor = '" + dniConductor + "' ";
			rs = st.executeQuery(consulta);
			rs.next();
			int puntos = rs.getInt("puntos");
			c = new Conductor(dniConductor,puntos);

		} catch (Exception ex) {
			System.out.println("No se ha podido obtener el conductor " + ex);
		}

		return c;
	}

	// Hito3

	/**
	 * Metodo que devuelve un objeto sancion construido con los valores de la
	 * bbdd, pasado un id
	 * 
	 * @param id
	 * @return
	 */
	public Sancion devolverSancion(int id) {
		Sancion s = null;
		Expediente e = null;
		Conductor c = null;

		try {
			String consulta = "SELECT * FROM sanciones where idSancion = '" + id + "' ";

			rs = st.executeQuery(consulta);
			rs.next();
			
			int idExp = rs.getInt("idExpediente");
			String dni = rs.getString("dniConductor");
			
			e = devolverExpediente(idExp);
			c = devolverConductor(dni);
			
			s = new Sancion(c, e);

		} catch (Exception ex) {
			System.out.println("No se ha podido obtener la sancion " + ex);
		}

		return s;
	}

	/**
	 * Metodo que pasada una sancion, se actualiza su estado de no pagada a
	 * pagada en la bbdd
	 * 
	 * @param s
	 */
	public void pagarSancion(Sancion s) {
		try {

			String consulta = "UPDATE sanciones SET pagado=1 where idExpediente = " + s.getExpediente().getId() + "";
			st.executeUpdate(consulta);

		} catch (Exception e) {
			System.out.println("No se ha podido pagar la sancion " + e);
		}
	}

	/**
	 * Metodo que pasado un objeto conductor con los puntos actuales, actualiza
	 * dichos puntos en la bbdd
	 * 
	 * @param c
	 */
	public void actualizarPuntosConductor(Conductor c) {
		try {

			String consulta = "UPDATE conductores SET puntos=" + c.getPuntos() + " where dniConductor = '" + c.getDni()
					+ "'";
			st.executeUpdate(consulta);

		} catch (Exception e) {
			System.out.println("No se han podido actualizar los puntos del conductor " + e);
		}
	}

	/**
	 * Metodo que devuelve los siguientes datos de aquellas sanciones que no han
	 * sido pagadas idSancion, matricula, fechayHora, conductor, puntosarestar,
	 * importe
	 * 
	 * @return
	 */
	public String[][] devolverDatosSancionesNoPagadas() {

		String[][] sanciones = null;/* new String[0][0]; */
		int cant = 0;

		try {

			String consulta = "SELECT idSancion,expedientes.matricula as matricula,expedientes.fechayHora as fechayHora, sanciones.dniConductor as conductor, sanciones.puntosarestar as puntosarestar, sanciones.importe as importe from sanciones, expedientes where pagado = 0 and sanciones.idExpediente = expedientes.idExpediente";
			rs = st.executeQuery(consulta);
			rs.last();
			if (rs.getRow() > 0) {
				sanciones = new String[rs.getRow()][6];
				rs.first();
				do {
					sanciones[cant][0] = "" + rs.getInt("idSancion");
					sanciones[cant][1] = rs.getString("matricula");
					sanciones[cant][2] = rs.getString("fechayHora");
					sanciones[cant][3] = rs.getString("conductor");
					sanciones[cant][4] = "" + rs.getInt("puntosarestar");
					sanciones[cant][5] = "" + rs.getInt("importe");
					cant++;
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("No se ha podido obtener las sanciones no pagadas " + e.toString());
		}

		return sanciones;

	}

	// HITO 4

	/**
	 * Metodo que devuelve un propietario con los datos obtenidos de la bd a
	 * partir de un dniPropoetario pasado
	 * 
	 * @param dniPropietario
	 * @return
	 */
	public Propietario devolverPropietario(String dniPropietario) {

		Propietario p = null;

		try {

			String consulta = "SELECT dniPropietario FROM propietario where dniPropietario = '" + dniPropietario + "' ";
			rs = st.executeQuery(consulta);
			rs.next();
			p = new Propietario(rs.getString("dniPropietario"));

		} catch (Exception ex) {
			System.out.println("No se ha podido obtener el propietario " + ex);
		}

		return p;
	}

	/**
	 * Metodo que pasado un vehiculo, actualiza sus valores en la bbdd
	 * 
	 * @param v
	 */
	public void actualizarVehiculos(Vehiculo v) {
		try {

			String consulta = "UPDATE vehiculo SET dniPropietario= '" + v.getPropietario().getDniPropietario()
					+ "' where matricula = '" + v.getMatricula() + "'";
			st.executeUpdate(consulta);

		} catch (Exception e) {
			System.out.println("No se han podido actualizar el propietario del vehiculo " + e);
		}
	}

}
