package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CookService;

/**
 * Servlet implementation class SVDelete
 */
public class SVDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookService cookService;

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public SVDelete() throws SQLException {
		super();
		this.cookService = new CookService();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
