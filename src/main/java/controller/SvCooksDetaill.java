package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Cook;
import model.CookList;
import model.Ingredient;
import model.Preparation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dao.DaoCook;

/**
 * Servlet implementation class SvCooksDetaill
 */

public class SvCooksDetaill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvCooksDetaill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String parameterValue = request.getParameter("id");
		//System.out.println("parametroValor: " + parametroValor);
		 
		long cookId = Long.parseLong(parameterValue);
		//System.out.println("cookId "+ cookId );
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		try {
			// Llama al método getDataCook y le paso el objeto PrintWriter
			Cook cook = new DaoCook().getCookDetails(cookId);

			// Convierte el objeto Java en formato JSON usando Gson
			String json = gson.toJson(cook);

			// Escribir el JSON en el cuerpo de la respuesta
			out.write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			out.write("Error inesperado");
		} finally {
			// Cierra el PrintWriter
			out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


}
