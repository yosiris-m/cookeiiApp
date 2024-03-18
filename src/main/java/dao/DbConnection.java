package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static String JdbcUrl = "jdbc:mysql://localhost:3306/dbCooks";
	public static String user = "root";
	static String password = "123456";

	public static Connection conn = null;

	public static Connection dbConnection() throws SQLException {

		if (conn == null) {

			conn = DriverManager.getConnection(JdbcUrl, user, password);
		}
		return conn;

	}

}
