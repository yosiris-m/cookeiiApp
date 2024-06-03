package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class SvChangePassword
 */
public class SvChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Constructor que inicializa el servlet y crea una instancia del servicio
	 * `UserService`.
	 * 
	 * @throws SQLException si ocurre un error al conectar con la base de datos
	 **/
	public SvChangePassword() throws SQLException {
		super();
		userService = new UserService();
	}

	/**
	 * Este método es llamado mediante solicitudes HTTP POST. Obtiene el parámetro
	 * `password` desde el formulario HTML y utiliza el servicio `UserService` para
	 * actualizar la contraseña del usuario en la base de datos y redirige a la
	 * página de inicio.
	 * 
	 * @param request  contiene la solicitud que el cliente ha hecho al servlet.
	 * @param response contiene la respuesta que el servlet envía al cliente.
	 * @throws ServletException si la solicitud no puede ser manejada.
	 * @throws IOException      si ocurre un error de entrada o salida.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtiene el email del usuario en sesión, si existe
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return;

		}
		
		String loggedUserEmail = user.getEmail();
		String password = request.getParameter("password");

		try {
			userService.updateUser(loggedUserEmail, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Redirigir a la página HTML
		response.sendRedirect("index.html");
	}

}
