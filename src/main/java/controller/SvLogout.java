package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Cook;
import model.User;
import services.UserService;

/**
 * Servlet para manejar el proceso de cierre de sesión de usuarios.
 */
public class SvLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Constructor que inicializa el servlet y crea una instancia del servicio
	 * `UserService`.
	 * 
	 * @throws SQLException si ocurre un error al conectar con la base de datos
	 **/
	public SvLogout() throws SQLException {
		super();
		userService = new UserService();
	}

	/**
	 * Este método es llamado mediante solicitudes HTTP GET. Invalida la sesión
	 * actual del usuario (`HttpSession.invalidate()`) y redirige al usuario a la
	 * página principal (`index.html`).
	 * 
	 * @param request  el objeto HttpServletRequest que contiene la solicitud
	 *  que el cliente ha hecho al servlet.
	 * @param response el objeto HttpServletResponse que contiene 
	 * la respuesta que el servlet envía al cliente.
	 * @throws ServletException si la solicitud no puede ser manejada.
	 * @throws IOException      si ocurre un error de entrada o salida. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		response.sendRedirect("index.html");
	}
	
	
	/** 
     * Este método es llamado mediante solicitudes HTTP POST. Obtiene los parámetros `email` y `password`
     * desde el formulario HTML y utiliza el servicio `UserService` para actualizar la contraseña.
     * del usuario en la base de datos y redirige a la página de inicio de sesión.
     * 
     * @param request contiene la solicitud que el cliente ha hecho al servlet.
     * @param response contiene la respuesta que el servlet envía al cliente.
     * @throws ServletException si la solicitud no puede ser manejada.
     * @throws IOException si ocurre un error de entrada o salida.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			userService.updateUser(email, password);
			System.out.println("actualizado" + password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Redirigir a la página HTML
		response.sendRedirect("login.html");
	}
}
