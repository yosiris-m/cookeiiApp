package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.DaoDeleteCook;

/**
 * Servlet implementation class SVDelete
 */
public class SVDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SVDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("SvUDelete do delete");
	    int id = Integer.parseInt(request.getParameter("id"));
	    

	    try {
	    	DaoDeleteCook dao = new DaoDeleteCook();
	        dao.deleteCook(id);
	       response.setStatus(HttpServletResponse.SC_OK);
	        response.setStatus(200);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

}
