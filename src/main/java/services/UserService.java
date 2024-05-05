package services;

import java.sql.SQLException;

import dao.DaoUser;
import model.User;

public class UserService {
	private DaoUser userDao;

	public UserService() throws SQLException {
		userDao = new DaoUser();
	}

	public void createUser(String userName,String email, String userPassword) throws SQLException {
		userDao.insertUser(userName,email,  userPassword);
	}

	public User validateUser(String email, String password, String userName) throws SQLException {
		return userDao.validateUser(email, password);
	}
}
