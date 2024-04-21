package util;

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

public class UploadFile {
	
	
	public String uploadFile(Part part, Path path, File upload, HttpServletResponse response)
			throws IOException, ServletException {
		// Guardo en modo texto el nombre del archivo para enviarlo a la bd
		String fileName = path.getFileName().toString();
		// Hago el camino o buffer por donde envio los datos
		InputStream input = part.getInputStream();
		// Concateno el tiempo con la imagen subida y asi optengo un nombre unico
		
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
}
