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
 * Servlet implementation class SvLogin
 */
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * @throws SQLException 
	 * @see HttpServlet#HttpServlet()
	 */
	public SvLogin() throws SQLException {
		super();
		userService = new UserService();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			User user = userService.validateUser(email, password);

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
