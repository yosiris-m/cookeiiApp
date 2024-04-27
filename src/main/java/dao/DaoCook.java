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

	public Connection con = null;

	public DaoCook() throws SQLException {
		try {
			this.con = DbConnection.dbConnection();
			System.out.println("Connected to the database!");

		} catch (Exception e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
		}

	}

	// function insertar
	public void insertCookTable(Cook cook, int userId) throws SQLException {
		if (cook.getIngredient() == null) {
			throw new IllegalArgumentException("La lista de ingredientes del Cook es null");
		}
		String insertCookQuery = "INSERT INTO cooks (title, quantity, timePreparation, fk_user_id, photo, state) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement cookInsert = con.prepareStatement(insertCookQuery, Statement.RETURN_GENERATED_KEYS)) {
			cookInsert.setString(1, cook.getTitle());
			cookInsert.setInt(2, cook.getQuantity());
			cookInsert.setString(3, cook.getTimePreparation());
			cookInsert.setInt(4, userId);
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

	// function insertar ingredientes
	private void insertIngredient(long recipe_id, Ingredient ingredient) throws SQLException {
		String insertIngredientQuery = "INSERT INTO ingredients (fk_recipe_id, ingredient) VALUES (?, ?)";
		try (PreparedStatement prepStatement = con.prepareStatement(insertIngredientQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setLong(1, recipe_id);
			prepStatement.setString(2, ingredient.getIngredient());
			prepStatement.execute();

		}
	}

	// function insertar preparaciones
	private void insertPreparation(long recipe_id, Preparation preparation) throws SQLException {
		String insertPreparationQuery = "INSERT INTO preparations (fk_recipe_id, preparation) VALUES (?,?)";
		try (PreparedStatement prepStatement = con.prepareStatement(insertPreparationQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setLong(1, recipe_id);
			prepStatement.setString(2, preparation.getPreparation());
			// prepStatement.setString(3, preparation.getImg());
			prepStatement.execute();

		}
	}

	// function listar ingredientes
	public List<Cook> getCookList() throws SQLException {
		List<Cook> result = new ArrayList<Cook>();
		//int userId;

		// Consulta SQL para seleccionar los datos que deseas recuperar

		String query = "SELECT c.id, title, quantity, timePreparation, u.user_name AS author, photo, state FROM cooks c INNER JOIN users u ON c.fk_user_id = u.id";
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
			System.out.println("result"+ result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// function actualizar receta
	public void updateCookTable(Cook cook) throws SQLException {

		String updateCookQuery = "UPDATE cooks SET title=?, quantity=?, timePreparation=?, photo=?, state=? WHERE id=?";
		try (PreparedStatement cookUpdate = con.prepareStatement(updateCookQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Establecer los valores de los parametros de la query o consulta
			cookUpdate.setString(1, cook.getTitle());
			cookUpdate.setInt(2, cook.getQuantity());
			cookUpdate.setString(3, cook.getTimePreparation());
			cookUpdate.setString(4, cook.getPhoto());
			cookUpdate.setString(5, cook.getState());

			cookUpdate.setInt(6, cook.getId());

			int cookIsUpdate = cookUpdate.executeUpdate();
			if (cookIsUpdate > 0) {
				// Elimina los ingredientes que existen en la BD
				this.deleteIngredients(cook.getId());
				// Insertar nuevos ingredientes
				for (Ingredient ingredient : cook.getIngredient()) {
					insertIngredient(cook.getId(), ingredient);
				}

				// Elimina las preparaciones que existen en la BD
				this.deletePreparations(cook.getId());
				// Inserta las nuevas preparaciones
				for (Preparation preparation : cook.getPreparation()) {
					insertPreparation(cook.getId(), preparation);
				}
				System.out.println("Se ha actualizado la receta con exito." + cook.getId());

			} else {
				System.out.println("No se pudo actualizar la receta." + cook.getId());
			}

		}

	}

	// function trae los detalles de una receta
	public Cook getCookDetails(long recipeId) throws SQLException {
		Cook cook = new Cook();

		// Consulta SQL para seleccionar los datos que deseas recuperar
		String query = "SELECT c.id, title, quantity, timePreparation,u.user_name AS author,u.id AS authorId, photo, state FROM cooks c INNER JOIN users u ON c.fk_user_id = u.id  WHERE c.id = ? ";
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
					cook.setAuthorId(resultSetCook.getInt("authorId"));
					cook.setPhoto(resultSetCook.getString("photo"));
					cook.setState(resultSetCook.getString("state"));
				}
			}
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
					// Agregar el objeto Ingredient a la lista en Cook
					cook.getIngredient().add(ingredient);
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
					}

					cook.getPreparation().add(preparation);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cook;

	}

	
	// function elimina los ingredientes para luego ser insertados
	public void deleteIngredients(int recipe_id) throws SQLException {
		String deleteIngredientQuery = "DELETE FROM ingredients WHERE fk_recipe_id = ?";

		try (PreparedStatement ingredienDelete = con.prepareStatement(deleteIngredientQuery)) {

			ingredienDelete.setInt(1, recipe_id);

			ingredienDelete.executeUpdate();
		}
	}

	// function elimina las preparaciones para luegoser insertadas
	public void deletePreparations(int recipe_id) throws SQLException {
		String deletePreparationQuery = "DELETE FROM preparations WHERE fk_recipe_id = ?";

		try (PreparedStatement preparationDelete = con.prepareStatement(deletePreparationQuery)) {

			preparationDelete.setInt(1, recipe_id);

			preparationDelete.executeUpdate();

		}
	}

}