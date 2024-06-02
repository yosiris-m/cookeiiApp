package model;

/**
 * Clase que representa a un usuario.
 */
public class User {
	private int id;
	private String userName;
	private String email;
	
	 /**
     * Constructor por defecto de la clase User.
     */
	public User() {}

	/**
	 * Constructor que inicializa un usuario con su ID,
	 *  nombre de usuario y correo electrónico.
	 * @param id ID único del usuario.
	 * @param userName Nombre de usuario.
	 * @param email Correo electrónico del usuario.
	 */
	public User(int id, String userName, String email) {
		this.id = id;
		this.userName = userName;
		this.email = email;
	}

	/**
	 * Constructor que inicializa un usuario con su nombre de usuario
	 * y correo electrónico.
	 * @param userName Nombre de usuario.
	 * @param email  Correo electrónico del usuario.
	 */
	public User(String userName, String email) {
		this.userName = userName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + "]";
	}

}
