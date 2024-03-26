package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cook;
import model.Ingredient;
import model.Preparation;

public class DaoCook {

	public static Connection con = null;

	public DaoCook() throws SQLException {
		try {
			this.con = DbConnection.dbConnection();
			System.out.println("Connected to the database!");

		} catch (Exception e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
		}

	}

	public void insertCookTable(Cook cook) throws SQLException {
		if (cook.getIngredient() == null) {
			throw new IllegalArgumentException("La lista de ingredientes del Cook es null");
		}
		String insertCookQuery = "INSERT INTO cooks (title, quantity, timePreparation, author, photo, state) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement cookInsert = con.prepareStatement(insertCookQuery, Statement.RETURN_GENERATED_KEYS)) {
			cookInsert.setString(1, cook.getTitle());
			cookInsert.setInt(2, cook.getQuantity());
			cookInsert.setString(3, cook.getTimePreparation());
			cookInsert.setString(4, cook.getAuthor());
			cookInsert.setString(5, cook.getPhoto());
			cookInsert.setString(6, cook.getState());

			cookInsert.executeUpdate();

			// Obtener el ID del cocinero insertado
			ResultSet generatedKeys = cookInsert.getGeneratedKeys();
			long cookId;
			if (generatedKeys.next()) {
				cookId = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Failed to retrieve generated cook_id");
			}

			// Insertar las relaciones con los ingredientes
			for (Ingredient ingredient : cook.getIngredient()) {
				insertIngredient(cookId, ingredient);
			}

			// Insertar las relaciones con las preparaciones
			for (Preparation preparation : cook.getPreparation()) {
				insertPreparation(cookId, preparation);
			}
		}
	}

	private void insertIngredient(long recipe_id, Ingredient ingredient) throws SQLException {
		String insertIngredientQuery = "INSERT INTO ingredients (fk_recipe_id, ingredient) VALUES (?, ?)";
		try (PreparedStatement prepStatement = con.prepareStatement(insertIngredientQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setLong(1, recipe_id);
			prepStatement.setString(2, ingredient.getIngredient());
			prepStatement.execute();

			/*
			 * ResultSet generatedKeys = prepStatement.getGeneratedKeys(); long
			 * ingredientId; if (generatedKeys.next()) { ingredientId =
			 * generatedKeys.getLong(1); } else { throw new
			 * SQLException("Failed to retrieve generated ingredient_id"); }
			 * 
			 * // Insertar en la tabla de relación cook_ingredient String
			 * insertRelationQuery =
			 * "INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (?, ?)"; try
			 * (PreparedStatement relationStatement =
			 * con.prepareStatement(insertRelationQuery)) { relationStatement.setLong(1,
			 * recipe_id); relationStatement.setLong(2, ingredientId);
			 * relationStatement.executeUpdate(); }
			 */
		}
	}

	private void insertPreparation(long recipe_id, Preparation preparation) throws SQLException {
		String insertPreparationQuery = "INSERT INTO preparations (fk_recipe_id, preparation) VALUES (?,?)";
		try (PreparedStatement prepStatement = con.prepareStatement(insertPreparationQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setLong(1, recipe_id);
			prepStatement.setString(2, preparation.getPreparation());
			//prepStatement.setString(3, preparation.getImg());
			prepStatement.execute();

		}
	}

	public List<Cook> getCookList() throws SQLException {
		List<Cook> result = new ArrayList<Cook>();

		// Consulta SQL para seleccionar los datos que deseas recuperar

		String query = "SELECT id, title, quantity, timePreparation, author, photo, state FROM cooks ";
		try {

			// Prepara la sentencia SQL
			PreparedStatement preparedStatement = con.prepareStatement(query);

			// Ejecuta la consulta y obtiene los resultados
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Cook cook = new Cook();
				cook.setId(resultSet.getInt("id"));
				cook.setTitle(resultSet.getString("title"));
				cook.setQuantity(resultSet.getInt("quantity"));
				cook.setTimePreparation(resultSet.getString("timePreparation"));
				cook.setAuthor(resultSet.getString("author"));
				cook.setPhoto(resultSet.getString("photo"));
				cook.setState(resultSet.getString("state"));

				result.add(cook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Cook getCookDetails(long recipeId) throws SQLException {
		Cook cook = new Cook();

		// Consulta SQL para seleccionar los datos que deseas recuperar

		String query = "SELECT id, title, quantity, timePreparation, author, photo, state FROM cooks WHERE id = ? ";
		String queryIngredients = "SELECT * FROM ingredients WHERE fk_recipe_id = ? ";
		String queryPreparations = "SELECT * FROM preparations WHERE fk_recipe_id = ? ";
		try {

			// Prepara la sentencia SQL
			PreparedStatement preparedStatement = con.prepareStatement(query);
			PreparedStatement preparedStatementIngrd = con.prepareStatement(queryIngredients);
			PreparedStatement preparedStatementPrep = con.prepareStatement(queryPreparations);

			// Consulta para los detalles del cocinero
			preparedStatement.setLong(1, recipeId);
			try (ResultSet resultSetCook = preparedStatement.executeQuery()) {

				while (resultSetCook.next()) {
					cook.setId(resultSetCook.getInt("id"));
					cook.setTitle(resultSetCook.getString("title"));
					cook.setQuantity(resultSetCook.getInt("quantity"));
					cook.setTimePreparation(resultSetCook.getString("timePreparation"));
					cook.setAuthor(resultSetCook.getString("author"));
					cook.setPhoto(resultSetCook.getString("photo"));
					cook.setState(resultSetCook.getString("state"));

				}
			}
             //http://localhost:8080/finalyProject/SvCookDetail?id=14
			// Consulta para los ingredientes
			preparedStatementIngrd.setLong(1, recipeId);
			try (ResultSet resultSetIngredients = preparedStatementIngrd.executeQuery()) {
				while (resultSetIngredients.next()) {
					// Crear un nuevo objeto Ingredient para cada fila
					Ingredient ingredient = new Ingredient();
					if (ingredient != null) {
						ingredient.setId(resultSetIngredients.getInt("ingredient_id"));
						ingredient.setIngredient(resultSetIngredients.getString("ingredient"));
					}
					cook.getIngredient().add(ingredient); // Agregar el objeto Ingredient a la lista en Cook
				}
			}

			// Consulta para las preparaciones
			preparedStatementPrep.setLong(1, recipeId);
			try (ResultSet resultSetPreparation = preparedStatementPrep.executeQuery()) {
				while (resultSetPreparation.next()) {
					Preparation preparation = new Preparation();
					
					if (preparation != null) {
						preparation.setId(resultSetPreparation.getInt("preparation_id"));
						preparation.setPreparation(resultSetPreparation.getString("preparation"));
						//preparation.setImg(resultSetPreparation.getString("img"));
					}
					
					System.out.println(preparation);
					cook.getPreparation().add(preparation);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cook;

	}
}