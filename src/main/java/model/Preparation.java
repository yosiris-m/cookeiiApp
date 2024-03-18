package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class Preparation {
	private int id;
	private String preparation;
	private String img;

	public Preparation() {
		super();
	}

	

	public Preparation(String preparation, String img) {
		super();
		this.preparation = preparation;
		this.img = img;
	}


	public Preparation(int id, String preparation, String img) {
		super();
		this.id = id;
		this.preparation = preparation;
		this.img = img;
	}

	// Método para crear el file
	public String uploadFilePrep(Part part, Path path, File upload, HttpServletResponse response)
			throws IOException, ServletException {
		// Guardo en modo texto el nombre del archivo para enviarlo a la bd
		String fileName = path.getFileName().toString();
		// Hago el camino o buffer por donde envio los datos
		InputStream input = part.getInputStream();
		// Concateno el tiempo con la imagen subida y asi optengo un nombre unico
		String newName = new Date().getTime() + fileName;
		// Copia la foto en el directorio creado
		File file = new File(upload, newName);

		try {
			Files.copy(input, file.toPath());
		} catch (Exception e) {
			PrintWriter error = response.getWriter();
			error.print("<p>Se ha producido un erroe al copiar la foto de preparacion, intentelo de nuevo</p>");
		}

		return fileName;
	}
   
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	@Override
	public String toString() {
		return "Preparation [preparation=" + preparation + ", img=" + img + "]";
	}

}
