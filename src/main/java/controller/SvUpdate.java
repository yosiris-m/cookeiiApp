package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Cook;
import model.CookList;
import model.Ingredient;
import model.Preparation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCook;

/**
 * Servlet implementation class SvUpdate
 */
@MultipartConfig
public class SvUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("SvUpdate do post");

		String applicationPath = request.getServletContext().getRealPath("");
		// Directorio para guardar las imágenes
		String uploadPath = applicationPath + File.separator + "recipe_photo";
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			// Crea el directorio si no existe }
			uploadDir.mkdir();
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String timePreparation = request.getParameter("timePreparation");
		String author = request.getParameter("author");
		String state = request.getParameter("state");
		String[] ingredientL = request.getParameterValues("ingredient");
		String[] preparationL = request.getParameterValues("preparation");
		Cook file = new Cook();

		// Leo los datos de la foto que me envian en el formulario
		Part part = request.getPart("file");
		Path path = Paths.get(part.getSubmittedFileName());

		// Nombre de archivo por defecto
		String fileName = "";
		Cook currentCook = null;

		try {
			//crea una instancia dao
			DaoCook dao = new DaoCook();
			//asigna la instancia dao
			currentCook = dao.getCookDetails(id);

			if (part.getSize() > 0) {
				// Si se ha enviado una nueva foto, cargarla y obtener su nombre de archivo
				fileName = file.uploadFile(part, path, uploadDir, response);
				
			} else {
				// Si no se ha enviado una nueva foto, mantener el nombre de archivo existente
				fileName = currentCook.getPhoto();
			}
			List<Ingredient> ingredients = new ArrayList<>();
			for (String ingredient : ingredientL) {
				ingredients.add(new Ingredient(ingredient));
			}
             
			List<Preparation> preparations = new ArrayList<>();
			for (String preparation : preparationL) {
				preparations.add(new Preparation(preparation));
			}
			// Actualizar el nombre del archivo de la foto en el objeto Cook
			currentCook.setPhoto(fileName);
			// actualiza la BD con los nuevos datos
			dao.updateCookTable(new Cook(id, title, quantity, timePreparation, author, fileName, state,  ingredients, preparations));
			response.sendRedirect("index.html");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in DB");
		}

	}

}
