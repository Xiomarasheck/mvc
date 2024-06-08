/**
 * 
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Autor;

/**
 * 
 */
public class AutoresModel extends Conexion {

	public List<Autor> listarAutores() throws SQLException {
		List<Autor> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM autores";
			this.conectar();
			st = conexion.prepareCall(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Autor autor = new Autor();
				autor.setCodigoAutor(rs.getString("codigo_autor"));
				autor.setNombreAutor(rs.getString("nombre_autor"));
				autor.setNacionalidad(rs.getString("nacionalidad"));
				lista.add(autor);
			}
			this.desconectar();
			for (Autor autor : lista) {
				
			}
			
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return lista;
		}
	}

	public int insertarAutor(Autor autor) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO `inventario`.`autores` (`codigo_autor`, `nombre_autor`, `nacionalidad`) VALUES (?,?,?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, autor.getCodigoAutor());
			st.setString(2, autor.getNombreAutor());
			st.setString(3, autor.getNacionalidad());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarAutor(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM `inventario`.`autores` WHERE  codigo_autor = ?";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, codigo);
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return 0;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int modificarAutor(Autor autor) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE `inventario`.`autores` SET `nombre_autor` = ?, `nacionalidad` = ? WHERE `codigo_autor` = ?";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, autor.getNombreAutor());
			st.setString(2, autor.getNacionalidad());
			st.setString(3, autor.getCodigoAutor());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Autor obtenerAutor(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM inventario.autores where codigo_autor = ? ";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			if (rs.next()) {
				Autor autor = new Autor();
				autor.setCodigoAutor(rs.getString("codigo_autor"));
				autor.setNombreAutor(rs.getString("nombre_autor"));
				autor.setNacionalidad(rs.getString("nacionalidad"));
				this.desconectar();
				return autor;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int totalAutores() throws SQLException {
		try {
			int totalaut = 0;
			String sql = "SELECT COUNT(codigo_autor) as totalaut FROM autores";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				totalaut = rs.getInt("totalaut");
			}
			return totalaut;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		} finally {
			this.desconectar();
		}
	}
}
