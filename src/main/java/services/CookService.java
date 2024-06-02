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
 * La clase que proporciona métodos para obtener detalles de una receta,
 * actualizar.
 */
public class CookService {
	private DaoCook cookDao;

	/**
	 * Constructor que inicializa la instancia de DaoCook para interactuar con la
	 * base de datos.
	 *
	 * @throws SQLException Si ocurre un error al establecer la conexión con la base
	 *                      de datos.
	 */
	public CookService() throws SQLException {
		this.cookDao = new DaoCook();
	}

	/**
	 * Obtiene los detalles del cocinero con el ID especificado determina si el
	 * usuario en sesión es el propietario de la receta.
	 * 
	 * @param cookId       ID de la receta.
	 * @param loggedUserId ID del usuario en sesión
	 * @return La receta con los detalles obtenidos.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
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
	 * Actualiza los datos de la receta. La foto es opcional y solo la actualiza si
	 * nos envían una nueva.
	 * 
	 * @param cook            La receta a actualizar.
	 * @param photoPart       Foto de la receta, puede ser nula si no se desea
	 *                        actualizar.
	 * @param applicationPath Ruta de la aplicación.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 * @throws IOException  Si ocurre un error de entrada o salida al procesar la
	 *                      foto.
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

	/**
	 * Guarda la foto de una receta en el sistema de archivos.
	 * 
	 * @param filePart        Parte del archivo de la foto
	 * @param applicationPath Ruta de la aplicación.
	 * @return El nombre único del archivo guardado.
	 * @throws IOException
	 */
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

	/**
	 * Crea una nueva receta en la base de datos.
	 * 
	 * @param cook         La receta a crear.
	 * @param loggedUserId ID del usuario que está creando la receta.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public void createCook(Cook cook, int loggedUserId) throws SQLException {
		this.cookDao.insertCookTable(cook, loggedUserId);
	}

	/**
	 * Elimina una receta de la base de datos.
	 * 
	 * @param id ID de la receta a eliminar.
	 * @throws SQLException
	 */
	public void deleteCook(int id) throws SQLException {
		cookDao.deleteCook(id);
	}

	/**
	 * Obtiene una lista de todas las recetas disponibles.
	 * 
	 * @return Lista de todas las recetas disponibles.
	 * @throws SQLException
	 */
	public List<Cook> getAllCooks() throws SQLException {
		return cookDao.getCookList();
	}
}
