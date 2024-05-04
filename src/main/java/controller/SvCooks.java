package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Cook;
import model.User;
import services.CookService;

/**
 * Servlet implementation class SvCooks
 */

@MultipartConfig
public class SvCooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookService cookService;

	/**
	 * Default constructor.
	 * 
	 * @throws SQLException
	 */
	public SvCooks() throws SQLException {
		super();
		this.cookService = new CookService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		try {
			// Llama al método getDataCook y le paso el objeto PrintWriter
			List<Cook> cooks = cookService.getAllCooks();
			// Convierte el objeto Java en formato JSON usando Gson
			String json = gson.toJson(cooks);

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("SvCooks doPost, comprobando la sesion...");
		// Obtiene el usuario en sesión
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("SvCooks doPost, el usuario en sesion es nulo");
			response.sendRedirect("login.html");
		} else {
			System.out.println("SvCooks doPost, el usuario que ha iniciado sesión es -> " + user.getEmail());
		}

		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String timePreparation = request.getParameter("timePreparation");
		String state = request.getParameter("state");
		String[] ingredientL = request.getParameterValues("ingredient");
		String[] preparationL = request.getParameterValues("preparation");

		// Leo los datos de la foto que me envian en el formulario
		Part part = request.getPart("file");

		// Obtiene el directorio donde se ejecuta la aplicación
		String applicationPath = request.getServletContext().getRealPath("");

		try {
			String fileName = cookService.saveCookPhoto(part, applicationPath);

			Cook cook = new Cook();
			cook.setTitle(title);
			cook.setQuantity(quantity);
			cook.setTimePreparation(timePreparation);
			cook.setPhoto(fileName);
			cook.setState(state);
			cook.addIngredients(ingredientL);
			cook.addPreparations(preparationL);
			System.out.println("Nueva receta==>" + cook);

			cookService.createCook(cook, user.getId());

			// Redirigir a la página HTML
			response.sendRedirect("index.html");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in DB");
		}
	}

}
