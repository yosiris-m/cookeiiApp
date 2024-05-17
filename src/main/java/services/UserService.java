package services;

import java.sql.SQLException;
import dao.DaoUser;
import model.User;
/** 
 * La clase con sus métodos para interactuar con 
 * los datos de usuario, incluyendo la creación, 
 * validación y actualización de usuarios.
 */
public class UserService {
	private DaoUser userDao;
    
	/** 
	 * Constructor para inicializar UserDao.
	 * @throws SQLException
	 */
	public UserService() throws SQLException {
		userDao = new DaoUser();
	}

	/** 
	 * Método para crear un usuario con los paramétros proporcionados.
	 * @param userNamenombre del nuevo usuario
	 * @param email correo electrónico del nuevo usuario
	 * @param userPassword contraseña del nuevo usuario
	 * @throws SQLException lanza un error si no hay acceso en la base de datos
	 */
	public void createUser(String userName,String email, String userPassword) throws SQLException {
		userDao.insertUser(userName,email,  userPassword);
	}
	
	/** 
	 * Método para validar las credenciales 
	 * del usuario con los datos almacenanados en la BD.
	 * @param email dirección de correo electrónico del usuario 
	 * @param password contraseña del usuario
	 * @param userName nombre de usuario del usuario
	 * @return
	 * @throws SQLException
	 */
	public User validateUser(String email, String password, String userName) throws SQLException {
		return userDao.validateUser(email, password);
	}
    
	/** 
	 * Actualiza la contraseña del usuario con la dirección de
	 *  correo electrónico dada
	 * @param email
	 * @param password
	 * @throws SQLException
	 */
	public void updateUser(String email, String password) throws SQLException {
		 userDao.updateUser(password, email);
		
	}
}
