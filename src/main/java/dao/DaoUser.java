package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cook;
import model.User;

public class DaoUser {

	public static Connection con = null;

	public DaoUser() throws SQLException {
		try {
			this.con = DbConnection.dbConnection();
			System.out.println("Connected to the database!");

		} catch (Exception e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
		}

	}

	public void insertUser(User user) throws SQLException {
		String insertUserQuery = "INSERT INTO users (user_name, user_password, email) VALUES (?,?,?)";

		try (PreparedStatement userInsert = con.prepareStatement(insertUserQuery)) {
			userInsert.setString(1, user.getUserName());
			userInsert.setString(2, user.getPassword());
			userInsert.setString(3, user.getEmail());
			userInsert.executeUpdate();

		} catch (Exception e) {
			System.out.println("No se ha podido insertar el usuario!");
			e.printStackTrace();
		}
	}

	public List<User> getUsers() {

		List<User> resultUser = new ArrayList<User>();

		String query = "SELECT id, user_name, email, user_password FROM users";

		try {

			// Prepara la sentencia SQL
			PreparedStatement preparedStatement = con.prepareStatement(query);

			// Ejecuta la consulta y obtiene los resultados
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("user_password"));

				resultUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultUser;
	}

	/**
	 * Método para validar las credenciales de un usuario
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve el usuario si las credenciales son correctas o null en otro
	 *         caso.
	 * @throws SQLException
	 */
	public User validateUser(String email, String password) throws SQLException {
		User user = null;

		String query = "SELECT * FROM users WHERE email = ? and user_password = ? ";

		// Prepara la sentencia SQL
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);

		// Ejecuta la consulta y obtiene los resultados
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			user = new User();
			user.setId(Integer.parseInt(resultSet.getString("id")));
			user.setUserName(resultSet.getString("user_name"));
			user.setEmail(resultSet.getString("email"));
		}

		return user;
	}

}
