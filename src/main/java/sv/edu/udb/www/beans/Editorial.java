/**
 * 
 */
package sv.edu.udb.www.beans;

/**
 * 
 */
public class Editorial {
	
	private String codigoEditorial;
	private String nombreEditorial;
	private String contacto;
	private String telefono;

	/**
	 * 
	 */
	public Editorial() {
		this.codigoEditorial ="";
		this.nombreEditorial="";
		this.contacto="";
		this.telefono="";
	}
	
	/**
	 * 
	 */
	public Editorial(String nombreEditorial) {
		this.codigoEditorial ="";
		this.nombreEditorial=nombreEditorial;
		this.contacto="";
		this.telefono="";
	}

	public String getCodigoEditorial() {
		return codigoEditorial;
	}

	public void setCodigoEditorial(String codigoEditorial) {
		this.codigoEditorial = codigoEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
