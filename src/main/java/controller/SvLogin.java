package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import services.UserService;

/**
 * * Servlet para manejar el proceso de inicio de sesión de usuarios.
 */
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Constructor que inicializa el servlet y crea una instancia del servicio
	 * `UserService`.
	 * 
	 * @throws SQLException si ocurre un error al conectar con la base de datos
	 */
	public SvLogin() throws SQLException {
		super();
		userService = new UserService();
	}

	/**
	 * Manejo de las solicitudes HTTP POST enviadas al servlet.
	 * Este método valida las credenciales de inicio de sesión enviadas desde el
	 * formulario HTML. Verifica el email y contraseña proporcionados utilizando el
	 * servicio `UserService`. Si las credenciales son válidas, crea una sesión para
	 * el usuario y lo redirige a la página principal. Si las credenciales son
	 * inválidas, redirige de vuelta a la página de inicio de sesión con un mensaje
	 * de error de sesión.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			User user = userService.validateUser(email, password, null);

			// si resultado de validación es correcto
			if (user != null) {
				// Crear la sesión
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				// Redireccionar a la página principal
				response.sendRedirect("index.html");
			} else {
				// En el caso contrario que nos mantenga en login mostrando un error
				response.sendRedirect("login.html?error=InvalidCredentials");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
