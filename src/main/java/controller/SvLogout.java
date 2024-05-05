package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Cook;
import model.User;
import services.UserService;

/**
 * Servlet implementation class SvLogout
 */
public class SvLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public SvLogout() throws SQLException {
    	super();
		userService = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  session.invalidate();
	  
	  response.sendRedirect("index.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			userService.updateUser(email, password);
			System.out.println("actualizado"+ password );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Redirigir a la página HTML
		response.sendRedirect("login.html");
	}
}
