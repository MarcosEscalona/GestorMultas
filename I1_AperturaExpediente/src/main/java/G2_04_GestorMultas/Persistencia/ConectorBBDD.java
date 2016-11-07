package Hito1.Persistencia;

import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.*;

import Hito1.Dominio.Foto;
import Hito1.Dominio.Propietario;
import Hito1.Dominio.Radar;
import Hito1.Dominio.Vehiculo;

/**
 * Clase que nos permite conectarnos con la Base de datos donde se guardan los datos de los propietarios de los vehículos
 *
 */
public class ConectorBBDD {
	

	private Connection con;
	private Statement st;
	private ResultSet rs; 

	/**
	 * Método que nos permite conectarnos con la Base de datos
	 * en caso de que no pueda nos da un error
	 */
	public ConectorBBDD() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba","root","");
			st = (Statement) con.createStatement();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Método para cerrar la conexion con la base de datos después de haberla usado 
	 */
	public void cerrarBBDD(){
		try{
			rs.close();
			st.close();
			con.close();
		}catch(Exception e){
			System.out.println("Error cerrando la bbdd "+e.getMessage());
		}
	}
	
	
	/**
	 * Método que crea un objeto vehículo para después usarlo para coger la matrícula de un vehíuclo de la base de datos 
	 * y lo guarda en el objeto vehículo creado anteriormente
	 * @return v el vehículo creado
	 */
	public Vehiculo getVehiculoAleatorio(){
		
		Vehiculo v = null;
		
		try{
			
			String consulta = "SELECT * FROM vehiculo ORDER BY RAND() LIMIT 1";			
			rs = st.executeQuery(consulta);		
			rs.next();
			Propietario propietario = new Propietario(rs.getString("dniPropietario"));
			v = new Vehiculo(rs.getString("matricula"),propietario);	
			
		}catch(Exception e){
			System.out.println("No se ha podido obtener un vehiculo aleatorio");
		}
		
		return v;
	}
	
	
	/**
	 * Creamos un radar vacío, para coger
	 * un radar aleatorio sacado de la base de datos y guardarlo en el radar creado anteriormente
	 * @return r radar creado
	 */
	public Radar getRadarAleatorio(){
		
		Radar r = null;
		
		try{

			String consulta = "SELECT * FROM radar ORDER BY RAND() LIMIT 1";			
			rs = st.executeQuery(consulta);
			rs.next();
			r = new Radar(rs.getInt("idRadar"), rs.getInt("velMax"), rs.getString("localizacion"));
			
		}catch(Exception e){
			System.out.println("No se ha podido obtener un radar aleatorio "+e);
		}
		
		return r;
		
	}


	/**
	 * Método para insertar una foto realizada a la base de datos que haya superado la velocidad máxima permitida del radar
	 * @param aux objeto auxiliar de foto que nos servirá para introducir los datos del vehículo que ha superado un velocidad
	 */
	public void insertarExpediente(Foto aux) {
		try{
			
			String matricula = aux.getV().getMatricula();
			int idRadar = aux.getR().getIdentificador();
			int velocidad = aux.getVelocidad();
			String fechayhora= ""+aux.getFechayhora();
			
			String consulta = "INSERT INTO expedientes VALUES (DEFAULT,'"+matricula+"', "+idRadar+", "+velocidad+", '"+fechayhora+"')";			
			st.executeUpdate(consulta);
			
		}catch(Exception e){
			System.out.println("No se ha podido insertar el Expediente "+e);
		}
	}
	
	
	

}
