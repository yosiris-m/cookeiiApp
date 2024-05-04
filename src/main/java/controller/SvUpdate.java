package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Cook;
import services.CookService;

/**
 * Servlet implementation class SvUpdate
 */
@MultipartConfig
public class SvUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookService cookService;

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public SvUpdate() throws SQLException {
		super();
		this.cookService = new CookService();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String timePreparation = request.getParameter("timePreparation");
		String author = request.getParameter("author");
		String state = request.getParameter("state");
		String[] ingredientL = request.getParameterValues("ingredient");
		String[] preparationL = request.getParameterValues("preparation");

		// Obtiene el directorio donde se ejecuta la aplicación
		String applicationPath = request.getServletContext().getRealPath("");

		// Leo los datos de la foto que me envian en el formulario
		Part photoPart = request.getPart("file");

		try {
			Cook cook = new Cook();
			cook.setId(id);
			cook.setTitle(title);
			cook.setQuantity(quantity);
			cook.setTimePreparation(timePreparation);
			cook.setAuthor(author);
			cook.setState(state);
			cook.addIngredients(ingredientL);
			cook.addPreparations(preparationL);

			this.cookService.updateCook(cook, photoPart, applicationPath);

			// Redireccionar el cliente al index después de realizar la actualización
			response.sendRedirect("index.html");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in DB");
		}
	}
}
