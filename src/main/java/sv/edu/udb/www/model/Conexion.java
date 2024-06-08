/**
 * 
 */
package sv.edu.udb.www.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 */
public class Conexion {

	protected static Connection conexion = null;
	protected PreparedStatement st;
	protected ResultSet rs;

	/**
	 * 
	 */
	public Conexion() {
		// TODO Auto-generated constructor stub
		this.st = null;
		this.rs = null;
	}

	public void conectar() throws SQLException {
		try {
			if (conexion == null || conexion.isClosed()) {
				Context init = new InitialContext();
				Context context = (Context) init.lookup("java:comp/env");
				DataSource datasource = (DataSource) context.lookup("jdbc/mysql");
				conexion = datasource.getConnection();

			}
		} catch (NamingException e) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void desconectar() throws SQLException {
		conexion.close();
		conexion = null;
	}
}
