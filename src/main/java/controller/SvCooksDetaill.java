package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cook;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoCook;

/**
 * Servlet implementation class SvCooksDetaill
 */

public class SvCooksDetaill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvCooksDetaill() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parameterValue = request.getParameter("id");

		

		long cookId = Long.parseLong(parameterValue);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		try {
			DaoCook dao = new DaoCook();
			Cook cook = dao.getCookDetails(cookId);

			
			// Calcular si la receta es del usuario en sesión
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			boolean isOwner = false;
			if (user != null) {
				isOwner = cook.getAuthorId() == user.getId();
			}
			cook.setIsOwner(isOwner);

			System.out.println("detail cook" + cook);

			// Convierte el objeto Java en formato JSON usando Gson
			String json = gson.toJson(cook);

			// Escribir el JSON en el cuerpo de la respuesta
			out.write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			out.write("Error inesperado");
		} finally {
			// Cierra el PrintWriter
			out.close();
		}

	}

}
