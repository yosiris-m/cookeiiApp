package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CookService;

/**
 * Servlet para manejar la eliminación de una receta.
 *
 */
public class SVDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookService cookService;

	/**
	 * Constructor que inicializa el servlet y crea una instancia del servicio
	 * `CookService`.
	 * 
	 * @throws SQLException si ocurre un error al conectarse con la base de datos
	 */
	public SVDelete() throws SQLException {
		super();
		this.cookService = new CookService();
	}

	/**
	 * Este método recibe una solicitud HTTP DELETE para eliminar una receta de cocina. Obtiene
	 * el ID de la receta a eliminar desde los parámetros de la solicitud y utiliza
	 * el servicio `CookService` para realizar la eliminación en la base de datos.
	 * Si la eliminación se realiza correctamente, responde con un código de estado
	 * HTTP 200 (OK). En caso de que ocurra un error durante la eliminación, se
	 * imprime el stack trace del error.
	 * @param request contiene la solicitud que el cliente ha hecho al servlet
	 * @param response  contiene la respuesta que el servlet envía al cliente
	 * @throws ServletException si la solicitud no puede ser manejada
	 * @throws IOException      si ocurre un error de entrada o salida 
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SvUDelete do delete");
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			cookService.deleteCook(id);

			response.setStatus(HttpServletResponse.SC_OK);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
