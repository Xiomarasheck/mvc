package sv.edu.udb.www.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Autor;
import sv.edu.udb.www.model.AutoresModel;
import sv.edu.udb.www.util.Validaciones;

/**
 * Servlet implementation class AutoresController
 */
@WebServlet("/AutoresController")
public class AutoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> listaErrores = new ArrayList<>();
	AutoresModel modelo = new AutoresModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoresController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("op") == null) {
				listar(request, response);
				return;
			}
			String operacion = request.getParameter("op");
			switch (operacion) {
			case "listar":
				listar(request, response);
				break;
			case "nuevo":
				request.getRequestDispatcher("/autores/nuevoAutor.jsp").forward(request, response);
				break;
			case "insertar":
				insertar(request, response);
				break;
			case "obtener":
				obtener(request, response);
				break;
			case "modificar":
				modificar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				listar(request, response);
				break;
			}
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(modelo.listarAutores());
			request.setAttribute("listaAutores", modelo.listarAutores());
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
	
			listaErrores.clear();
			Autor miAutor = new Autor();
			miAutor.setCodigoAutor(request.getParameter("codigo"));
			miAutor.setNombreAutor(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			if (Validaciones.isEmpty(miAutor.getCodigoAutor())) {
				listaErrores.add("El codigo del autor es obligatorio");
			} else if (!Validaciones.esCodigoAutor(miAutor.getCodigoAutor())) {
				listaErrores.add("El codigo de la autor debe tener el formato correcto AUT000");
			}
			if (Validaciones.isEmpty(miAutor.getNombreAutor())) {
				listaErrores.add("El nombre del autor es obligatorio");
			}
			if (Validaciones.isEmpty(miAutor.getNacionalidad())) {
				listaErrores.add("La nacionalidad es obligatoria");
			}
			
			System.out.println(listaErrores);
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("AutoresController?op=nuevo").forward(request, response);
			} else {
				if (modelo.insertarAutor(miAutor) > 0) {
					request.getSession().setAttribute("exito", "autor registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
				} else {
					request.getSession().setAttribute("fracaso","El autor no ha sido ingresado" + "ya hay un autor con este codigo");
					response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Autor miAutor = modelo.obtenerAutor(codigo);
			if (miAutor != null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutor.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Autor miAutor = new Autor();
			miAutor.setCodigoAutor(request.getParameter("codigo"));
			miAutor.setNombreAutor(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			if (Validaciones.isEmpty(miAutor.getCodigoAutor())) {
				listaErrores.add("El codigo del autor es obligatorio");
			} else if (!Validaciones.esCodigoAutor(miAutor.getCodigoAutor())) {
				listaErrores.add("El codigo de la autor debe tener el formato correcto AUT000");
			}
			if (Validaciones.isEmpty(miAutor.getNombreAutor())) {
				listaErrores.add("El nombre del autor es obligatorio");
			}
			if (Validaciones.isEmpty(miAutor.getNacionalidad())) {
				listaErrores.add("La nacionalidad es obligatoria");
			}
			System.out.println(listaErrores);
			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/MVC/AutoresController?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarAutor(miAutor) > 0) {
					request.getSession().setAttribute("exito", "autor modificado exitosamente");
					response.sendRedirect("/MVC/AutoresController?op=listar");
				} else {
					request.getSession().setAttribute("fracaso",
							"El autor no ha sido modificado" + "ya hay un autor con este codigo");
					response.sendRedirect("/MVC/AutoresController?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response){
		try {

			String codigo = request.getParameter("id");
			if (modelo.eliminarAutor(codigo) > 0) {
				request.getSession().setAttribute("exito", "Autor eliminado exitosamente");
			} else {
				request.getSession().setAttribute("fracaso", "No se puede eliminar este autor");
			}
			response.sendRedirect("/MVC/AutoresController?op=listar");
		} catch (IOException | SQLException ex) {
			Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


}
