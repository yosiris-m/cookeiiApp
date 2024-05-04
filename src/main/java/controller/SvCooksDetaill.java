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
import model.Cook;
import model.User;
import services.CookService;

/**
 * Servlet implementation class SvCooksDetaill
 */

public class SvCooksDetaill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookService cookService;

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public SvCooksDetaill() throws SQLException {
		super();
		cookService = new CookService();
	}

	/**
	 * Obtiene el id de la receta de los parámetros de la petición. Si hay un
	 * usuario en sesión obtiene su identificador. Usa el servicio de recetas para
	 * obtener los detalles de la receta. Elabora la respuesta en formato JSON.
	 * 
	 * @return Un JSON con los detalles de la receta solicitada.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		// Leer el id de la receta de los parámetros de entrada
		String parameterValue = request.getParameter("id");
		long cookId = Long.parseLong(parameterValue);

		// Establecer el tipo de respuesta a JSON
		response.setContentType("application/json");

		try {

			// Obtener el id del usuario en sesión, si existe
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			Integer loggedUserId = null;
			if (user != null) {
				loggedUserId = user.getId();
			}

			// Obtener los detalles de la receta usando el id de receta
			Cook cook = cookService.getCookDetail(cookId, loggedUserId);

			// Convierte el objeto Java en formato JSON usando Gson
			String json = gson.toJson(cook);

			// Escribir el JSON en el cuerpo de la respuesta
			out.write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			out.write("Error inesperado con la base de datos");
		} finally {
			// Cierra el PrintWriter
			out.close();
		}
	}

}
