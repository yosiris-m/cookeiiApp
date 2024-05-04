package services;

import java.sql.SQLException;

import dao.DaoUser;
import model.User;

public class UserService {
	private DaoUser userDao;

	public UserService() throws SQLException {
		userDao = new DaoUser();
	}

	public void createUser(User userData) throws SQLException {
		userDao.insertUser(userData);
	}

	public User validateUser(String email, String password) throws SQLException {
		return userDao.validateUser(email, password);
	}
}
