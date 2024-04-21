package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import dao.DaoCook;
import dao.DaoUser;

/**
 * Servlet implementation class SvUser
 */
public class SvUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		
		System.out.println("SvUser doGet, comprobando la sesion...");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("SvUser doGet, el usuario en sesion es nulo");
			String json = gson.toJson(null);
			out.write(json);
		} else {
			System.out.println("SvUser doGet, el usuario que ha iniciado sesión es -> " + user.getEmail());
			String json = gson.toJson(user);
			out.write(json);
			
		}
		
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Leer el JSON
		String jsonString = request.getReader().readLine();
		// Crear instancia Gson
		Gson gson = new Gson();
		try {
			// Convertir JSON a objeto java
			User user = gson.fromJson(jsonString, User.class);
			// Procesar el objeto Java
			user.insert();
			System.out.println("User inserted successfully: " + user);
			//response.sendRedirect("index.html");
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			//out.close();
			e.printStackTrace(); // Log the exception for debugging
		}

	}

}
