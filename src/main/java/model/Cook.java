package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.StringUtil;

public class Cook {
	private int id;
	private String title;
	private int quantity;
	private String timePreparation;
	private String author;
	private String photo;
	private String state;
	private List<Ingredient> ingredients;
	private List<Preparation> preparations;

	// public Cook() {};

	public Cook() {
		this.preparations = new ArrayList<>();
		this.ingredients = new ArrayList<>();
		// Inicializa la lista en el constructor
	}

	public Cook(String title, int quantity, String timePreparation, String author, String photo, String state,
			List<Ingredient> ingredient, List<Preparation> preparation) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.timePreparation = timePreparation;
		this.author = author;
		this.photo = photo;
		this.state = state;
		this.ingredients = ingredient;
		this.preparations = preparation;
	}

	public Cook(int id, String title, int quantity, String timePreparation, String author, String photo, String state,
			List<Ingredient> ingredient, List<Preparation> preparation) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.timePreparation = timePreparation;
		this.author = author;
		this.photo = photo;
		this.state = state;
		this.ingredients = ingredient;
		this.preparations = preparation;
	}

	// Método para crear el file
	public String uploadFile(Part part, Path path, File upload, HttpServletResponse response)
			throws IOException, ServletException {
		// Guardo en modo texto el nombre del archivo para enviarlo a la bd
		String fileName = path.getFileName().toString();
		// Hago el camino o buffer por donde envio los datos
		InputStream input = part.getInputStream();
		// Concateno el tiempo con la imagen subida y asi optengo un nombre unico
		
		
		// fileName = "dasdsds.png"
		
		String extension = StringUtil.obtenerExtension(fileName);
		
		String newName = new Date().getTime()+ "." + extension;
		File file = new File(upload, newName);

		try {
			Files.copy(input, file.toPath());

		} catch (Exception e) {
			PrintWriter error = response.getWriter();
			error.print("<p>Se ha producido un error al copiar la foto, intentelo de nuevo</p>");
		}

		return newName;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTimePreparation() {
		return timePreparation;
	}

	public void setTimePreparation(String timePreparation) {
		this.timePreparation = timePreparation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Ingredient> getIngredient() {
		return ingredients;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredients = ingredient;
	}

	public List<Preparation> getPreparation() {
		return preparations;
	}

	public void setPreparation(List<Preparation> preparation) {
		this.preparations = preparation;
	}

	@Override
	public String toString() {
		return "Cook [id=" + id + ", title=" + title + ", quantity=" + quantity + ", timePreparation=" + timePreparation
				+ ", author=" + author + ", photo=" + photo + ", state=" + state + ", ingredient=" + ingredients
				+ ", preparation=" + preparations + "]";
	}


}
