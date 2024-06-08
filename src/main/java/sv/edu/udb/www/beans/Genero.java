/**
 * 
 */
package sv.edu.udb.www.beans;

/**
 * 
 */
public class Genero {
	
	private int idGenero;
	private String nombreGenero;
	private String descripcion;

	/**
	 * 
	 */
	public Genero() {
		// TODO Auto-generated constructor stub
		this.idGenero = 0;
		this.nombreGenero = "";
		this.descripcion = "";
	}
	
	public Genero(String nombreGenero) {
		// TODO Auto-generated constructor stub
		this.idGenero = 0;
		this.nombreGenero = nombreGenero;
		this.descripcion = "";
	}


	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
