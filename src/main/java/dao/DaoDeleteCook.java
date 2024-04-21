package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoDeleteCook {
	public static Connection con = null;

	public DaoDeleteCook() throws SQLException {
		try {
			this.con = DbConnection.dbConnection();
			System.out.println("Connected to the database!");

		} catch (Exception e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
		}

	}
	
	// function elimina la receta selecionada
		public void deleteCook(int recipe_id) throws SQLException {
			// DELETE FROM cooks WHERE id = 1;
			String deleteCookQuery = "DELETE FROM cooks WHERE id = ?";

			try (PreparedStatement cookDeleted = con.prepareStatement(deleteCookQuery)) {
				this.deleteIngredients(recipe_id);
				this.deletePreparations(recipe_id);
				cookDeleted.setInt(1, recipe_id);

				cookDeleted.executeUpdate();

			}
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
