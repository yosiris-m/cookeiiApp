package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import services.UserService;

/**
 * Servlet para manejar las operaciones relacionadas con los usuarios.
 */
public class SvUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Constructor que inicializa el servlet y crea una instancia del servicio
	 * `UserService`.
	 * 
	 * @throws SQLException si ocurre un error al conectar con la base de datos
	 */
	public SvUser() throws SQLException {
		super();
		userService = new UserService();
	}

	/**
	 * Este método es llamado mediante solicitudes HTTP GET. Obtiene el usuario
	 * actual desde la sesión (`HttpSession`) y convierte el objeto `User` en
	 * formato JSON utilizando la biblioteca Gson. Si no hay usuario en sesión,
	 * retorna un JSON nulo.
	 * 
	 * @param request  contiene la solicitud que el cliente ha hecho al servlet
	 * @param response contiene la respuesta que el servlet envía al cliente.
	 * @throws ServletException si la solicitud no puede ser manejada.
	 * @throws IOException      si ocurre un error de entrada o salida.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson gson = new Gson();

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			String json = gson.toJson(null);
			out.write(json);
		} else {
			String json = gson.toJson(user);
			out.write(json);

		}

		out.close();

	}

	/**
	 * 
	 * Este método es llamado mediante solicitudes HTTP POST. Obtiene los parámetros
	 * `userName`, `email` y `password` del formulario y utiliza el servicio
	 * `UserService` para crear un nuevo usuario en la base de datos utilizando 
	 *  los datos recibidos del formulario HTML.
	 * 
	 * Si la creación es exitosa, redirige al usuario a la página de inicio de
	 * sesión (`login.html`). En caso de error durante la creación (capturado
	 * mediante `SQLException`), establece el código de estado de respuesta como 500
	 * (Internal Server Error) y muestra el stack trace del error.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Procesar el objeto Java
			userService.createUser(userName, email, password);
			// Redirigir a la página HTML
			response.sendRedirect("login.html");

		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

	}

}
