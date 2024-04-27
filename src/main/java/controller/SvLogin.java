package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import dao.DaoUser;

/**
 * Servlet implementation class SvLogin
 */
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DaoUser dao = new DaoUser();
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			User user = dao.validateUser(email, password);

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
