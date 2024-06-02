package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Método responsable de establecer la conexión con la base de datos.
 * Devuelve un objeto de tipo Connection que representa la conexión
 *  a la base de datos.
 */
public class DbConnection {
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/dbCooks";
	private static final String user = "root";
	private static final String password = "123456";

	private static Connection conn = null;

	public static Connection dbConnection() throws SQLException {

		if (conn == null) {
			conn = DriverManager.getConnection(jdbcUrl, user, password);
		}

		return conn;
	}
}
