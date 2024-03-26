package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import dao.DaoCook;

/**
 * Servlet implementation class SvCooks
 */

@MultipartConfig
public class SvCooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Creao la ruta donde se almacenan las fotos
	// private String pathFile =
	// "C:\\eclipse-work_space\\finalyProject\\src\\main\\webapp\\recipe_photo";
	// Crea un objeto que direciona la ruta de las fotos
	// private File upload = new File(pathFile);

	/**
	 * Default constructor.
	 */
	public SvCooks() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		try {
			// Llama al método getDataCook y le paso el objeto PrintWriter
			List<Cook> cooks = new DaoCook().getCookList();

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

		String applicationPath = request.getServletContext().getRealPath("");
		// System.out.println("*** applicationPath -> " + applicationPath);
		String uploadPath = applicationPath + File.separator + "recipe_photo"; // Directorio para guardar las imágenes

		File uploadDir = new File(uploadPath);
		/*
		 * if (!uploadDir.exists()) {
		 * System.out.println("*** El directorio de las imagenes no existe, lo creamos"
		 * ); uploadDir.mkdir(); // Crea el directorio si no existe }
		 */

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
		// Part partFilePrep = request.getPart("ingre-file");
		// Me da el nombre del archivo origen
		Path path = Paths.get(part.getSubmittedFileName());

		// Path pathPrep = Paths.get(partFilePrep.getSubmittedFileName());
		// crea un objeto de la foto para añadirla a listado
		String fileName = file.uploadFile(part, path, uploadDir, response);

		//Preparation filePre = new Preparation();
		// TODO: No llamar a esto si no nos llega imagen de preparacion: mirar esto:
		// partFilePrep.getSubmittedFileName()
		/*
		 * String filePreparation = ""; if (partFilePrep.getSubmittedFileName() != "") {
		 * filePreparation = filePre.uploadFilePrep(partFilePrep, pathPrep, uploadDir,
		 * response); }
		 */
		List<Preparation> preparations = new ArrayList<>();

		for (String preparation : preparationL) {
			preparations.add(new Preparation(preparation));
		}

		List<Ingredient> ingredients = new ArrayList<>();
		for (String ingredient : ingredientL) {
			ingredients.add(new Ingredient(ingredient));
		}
		System.out.println(preparations);
		CookList cookList = new CookList();

		System.out.println("datos guardados" + cookList);
		cookList.addCook(
				new Cook(title, quantity, timePreparation, author, fileName, state, ingredients, preparations));

		try {
			cookList.insert();
			// Redirigir a la página HTML
			response.sendRedirect("index.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in DB");
		}
	}

}
