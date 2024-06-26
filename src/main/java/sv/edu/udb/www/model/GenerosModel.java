package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Genero;

public class GenerosModel extends Conexion {

	public GenerosModel() {
		// TODO Auto-generated constructor stub
	}

	public List<Genero> listarGeneros() throws SQLException {
		try {
			List<Genero> lista = new ArrayList<>();
			String sql = "SELECT * FROM inventario.generos";
			this.conectar();
			st = conexion.prepareCall(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getString("nombre_genero"));
				genero.setDescripcion(rs.getString("descripcion"));
				lista.add(genero);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarGeneros(Genero genero) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarGenero(?,?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, genero.getNombreGenero());
			st.setString(2, genero.getDescripcion());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Genero obtenerGenero(String id) throws SQLException {
		try {
			String sql = "SELECT * FROM inventario.generos where id_genero = ?";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getString("nombre_genero"));
				genero.setDescripcion(rs.getString("descripcion"));
				this.desconectar();
				return genero;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarGenero(Genero genero) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarGenero(?,?,?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, genero.getNombreGenero());
			st.setString(2, genero.getDescripcion());
			st.setInt(3, genero.getIdGenero());
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarGenero(String id) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarGenero(?)";
			this.conectar();
			st = conexion.prepareCall(sql);
			st.setString(1, id);
			filasAfectadas = st.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int totalGeneros() throws SQLException {
		try {
			int total = 0;
			String sql = "SELECT COUNT(id_genero) as total FROM generos";
			this.conectar();
			st = conexion.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
			this.desconectar();
			return total;
		} catch (SQLException ex) {
			Logger.getLogger(GenerosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

}
