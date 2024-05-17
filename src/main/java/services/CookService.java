package services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import dao.DaoCook;
import jakarta.servlet.http.Part;
import model.Cook;
import util.StringUtil;


/** 
 * La clase que proporciona métodos para obtener 
 * detalles de una receta, actualizar,.
 */
public class CookService {
	private DaoCook cookDao;

	public CookService() throws SQLException {
		this.cookDao = new DaoCook();
	}

	/**
     * Obtiene los detalles del cocinero con el ID especificado.
     * Además, determina si el usuario en sesión es el propietario de la receta del cocinero.
     * @param cookId el ID del cocinero cuyos detalles se desean obtener
     * @param loggedUserId el ID del usuario en sesión
     * @return el cocinero con sus detalles y un indicador de si el usuario en sesión es el propietario de su receta
     * @throws SQLException si ocurre un error de acceso a la base de datos
     */
	/** 
	 * Obtiene los detalles del cocinero con el ID especificado
	 * determina si el usuario en sesión es el propietario de la receta.
	 * @param cookId
	 * @param loggedUserId ID del usuario en sesión
	 * @return
	 * @throws SQLException
	 */
	public Cook getCookDetail(long cookId, Integer loggedUserId) throws SQLException {
		Cook cook = this.cookDao.getCookDetails(cookId);

		// Calcular si la receta es del usuario en sesión
		boolean isOwner = false;
		if (loggedUserId != null) {
			isOwner = cook.getAuthorId() == loggedUserId;
		}
		cook.setIsOwner(isOwner);

		return cook;
	}

	/**
	 * Actualiza los datos de la receta.
	 * La foto es opcional y solo la actualiza si nos envían una nueva.
	 * 
	 * @param cook
	 * @param photoPart
	 * @param applicationPath
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateCook(Cook cook, Part photoPart, String applicationPath) throws SQLException, IOException {
		// Nombre de archivo por defecto
		String fileName = "";
		if (photoPart.getSize() > 0) {
			// Si se ha enviado una nueva foto, cargarla y obtener su nombre de archivo
			fileName = this.saveCookPhoto(photoPart, applicationPath);
		} else {
			// Si no se ha enviado una nueva foto, mantener el nombre de archivo existente
			// asigna la instancia dao
			Cook currentCook = this.getCookDetail(cook.getId(), null);
			fileName = currentCook.getPhoto();
		}
		cook.setPhoto(fileName);
		
		this.cookDao.updateCookTable(cook);
	}

	public String saveCookPhoto(Part filePart, String applicationPath) throws IOException {
		// Directorio para guardar las imágenes
		String uploadPath = applicationPath + File.separator + "recipe_photo";
		File uploadDir = new File(uploadPath);

		// Guardo en modo texto el nombre del archivo para enviarlo a la bd
		Path path = Paths.get(filePart.getSubmittedFileName());
		String fileName = path.getFileName().toString();

		// Hago el camino o buffer por donde envio los datos
		InputStream input = filePart.getInputStream();

		String extension = StringUtil.getExtension(fileName);

		// Concateno el tiempo con la imagen subida y asi obtengo un nombre unico
		String newName = new Date().getTime() + "." + extension;
		File file = new File(uploadDir, newName);

		if (!uploadDir.exists()) {
			// Crea el directorio si no existe }
			uploadDir.mkdir();
		}

		Files.copy(input, file.toPath());

		return newName;
	}

	public void createCook(Cook cook, int loggedUserId) throws SQLException {
		this.cookDao.insertCookTable(cook, loggedUserId);
	}

	public void deleteCook(int id) throws SQLException {
		cookDao.deleteCook(id);
	}

	public List<Cook> getAllCooks() throws SQLException {
		return cookDao.getCookList();
	}
}
