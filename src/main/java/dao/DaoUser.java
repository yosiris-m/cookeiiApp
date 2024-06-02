package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * Clase DAO para interactuar con la tabla de usuarios en la base de datos.
 * 
 * Esta clase proporciona métodos para insertar, actualizar y recuperar usuarios
 * desde la base de datos MySQL.
 */
public class DaoUser {

	/**
	 * Constructor de la clase que inicializa la conexión a la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al establecer la conexión.
	 */
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

	/**
	 * Método para insertar un nuevo usuario en la base de datos.
	 * 
	 * @param userName     nombre del usuario.
	 * @param email        correo electrónico del usuario.
	 * @param userPassword contraseña del usuario.
	 * @throws SQLException si ocurre un error SQL durante la inserción.
	 */
	public void insertUser(String userName, String email, String userPassword) throws SQLException {
		String insertUserQuery = "INSERT INTO users (user_name, user_password, email) VALUES (?,?,?)";

		try (PreparedStatement userInsert = con.prepareStatement(insertUserQuery)) {
			userInsert.setString(1, userName);
			userInsert.setString(2, userPassword);
			userInsert.setString(3, email);
			userInsert.executeUpdate();

			System.out.println("userInsert" + userInsert);
		} catch (Exception e) {
			System.out.println("No se ha podido insertar el usuario!");
			e.printStackTrace();
		}
	}

	/**
	 * Método para actualizar la contraseña de un usuario.
	 * 
	 * @param userPassword nueva contraseña del usuario.
	 * @param userEmail correo electrónico del usuario cuya contraseña se
	 *                  actualizará.
	 * @throws SQLException si ocurre un error SQL durante la actualización.
	 */
	public void updateUser(String userPassword, String userEmail) throws SQLException {
		String updateUserQuery = "UPDATE users SET user_password = ? WHERE email = ?";

		try (PreparedStatement userUpdate = con.prepareStatement(updateUserQuery)) {
			userUpdate.setString(1, userPassword);
			userUpdate.setString(2, userEmail);
			userUpdate.executeUpdate();

			System.out.println("userInsert" + userPassword);
		} catch (Exception e) {
			System.out.println("No se ha podido insertar el usuario!");
			e.printStackTrace();
		}
	}

	/**
	 * Método para obtener todos los usuarios de la base de datos.
	 * 
	 * @return lista de usuarios recuperados de la base de datos
	 */
	public List<User> getUsers() {

		List<User> resultUser = new ArrayList<User>();

		String query = "SELECT id, user_name, email, user_password FROM users";

		try {

			// Prepara la sentencia SQL
			PreparedStatement preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(0, query);
			// Ejecuta la consulta y obtiene los resultados
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.getString("user_password");
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setEmail(resultSet.getString("email"));

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
	 * @param email correo electrónico del usuario
	 * @param password contraseña del usuario
	 * @return Devuelve el usuario si las credenciales son correctas o null en otro
	 *         caso.
	 * @throws SQLException si ocurre un error SQL durante la validación
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
