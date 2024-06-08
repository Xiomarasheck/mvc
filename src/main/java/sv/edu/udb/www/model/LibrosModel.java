package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Autor;
import sv.edu.udb.www.beans.Editorial;
import sv.edu.udb.www.beans.Genero;
import sv.edu.udb.www.beans.Libro;

public class LibrosModel extends Conexion {

	public LibrosModel() {
		// TODO Auto-generated constructor stub
	}

	public List<Libro> listarLibros() throws SQLException {
		List<Libro> lista = new ArrayList<>();
		try {

			String sql = "select lb.*, au.nombre_autor, ed.nombre_editorial, gn.nombre_genero from inventario.libros as lb inner join inventario.autores as au on au.codigo_autor = lb.codigo_autor inner join inventario.editoriales ed on ed.codigo_editorial = lb.codigo_editorial inner join inventario.generos gn on gn.id_genero = lb.id_genero";
			this.conectar();
			st = conexion.prepareCall(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoLibro(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setAutor(new Autor(rs.getString("nombre_autor")));
				libro.setEditorial(new Editorial(rs.getString("nombre_editorial")));
				libro.setGenero(new Genero(rs.getString("nombre_genero")));
				lista.add(libro);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return lista;
		}
	}

	public int insertarLibro(Libro libro) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO `inventario`.`libros` (`codigo_libro`, `nombre_libro`, `existencias`, `precio`, `codigo_autor`, `codigo_editorial`,`id_genero`, `descripcion`) VALUES (?,?,?,?,?,?,?,?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, libro.getCodigoLibro());
			st.setString(2, libro.getNombreLibro());
			st.setInt(3, libro.getExistencias());
			st.setDouble(4, libro.getPrecio());
			st.setString(5, libro.getCodigoAutor());
			st.setString(6, libro.getCodigoEditorial());
			st.setInt(7, libro.getIdGenero());
			st.setString(8, libro.getDescripcion());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarLibro(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM `inventario`.`libros` WHERE codigo_libro = ?";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, codigo);
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Libro obtenerLibro(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM inventario.libros where codigo_libro = ?";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			if (rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoLibro(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setCodigoAutor(rs.getString("codigo_autor"));
				libro.setCodigoEditorial(rs.getString("codigo_editorial"));
				libro.setIdGenero(rs.getInt("id_genero"));
				this.desconectar();
				return libro;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarLibro(Libro libro) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarLibro(?,?,?,?,?,?,?,?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, libro.getCodigoLibro());
			st.setString(2, libro.getNombreLibro());
			st.setInt(3, libro.getExistencias());
			st.setDouble(4, libro.getPrecio());
			st.setString(5, libro.getCodigoAutor());
			st.setString(6, libro.getCodigoEditorial());
			st.setInt(7, libro.getIdGenero());
			st.setString(8, libro.getDescripcion());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Libro detalleLibro(String codigo) throws SQLException {
		try {
			String sql = "CALL sp_detalleLibro(?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			if (rs.next()) {
				Libro libro = new Libro();
				libro.setCodigoLibro(rs.getString("codigo_libro"));
				libro.setNombreLibro(rs.getString("nombre_libro"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setAutor(new Autor(rs.getString("nombre_autor")));
				libro.setEditorial(new Editorial(rs.getString("nombre_editorial")));
				libro.setGenero(new Genero(rs.getString("nombre_genero")));
				this.desconectar();
				return libro;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int totalLibros() throws SQLException {
		try {
			int total = 0;
			String sql = "SELECT COUNT(codigo_libro) as total FROM libros";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
			this.desconectar();
			return total;
		} catch (SQLException ex) {
			Logger.getLogger(LibrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

}
