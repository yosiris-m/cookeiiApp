package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class RecipePhoto {
	public String uploadFile(Part part, Path path, File upload, HttpServletResponse response)
			throws IOException, ServletException {
		// Guardo en modo texto el nombre del archivo para enviarlo a la bd
		String fileName = path.getFileName().toString();
		// Hago el camino o buffer por donde envio los datos
		InputStream input = part.getInputStream();
		// Copia la foto donde le voy a guardar
		File file = new File(upload, fileName);

		try {
			Files.copy(input, file.toPath());

		} catch (Exception e) {
			System.out.println("Error al copiar la foto");
			PrintWriter error = response.getWriter();
			error.print("<p>Se ha producido un erroe al copiar la foto, intentelo de nuevo</p>");
		}

		return fileName;
	}
}
