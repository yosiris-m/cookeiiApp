package model;

import java.sql.SQLException;
import dao.DaoUser;

import com.google.gson.Gson;

public class User {
	 private int id;
	private String userName;
	// donde alojar la contraseña no puede estar en esta clase
	private String password;
	private String email;
	// private int rool;

	public User() {
		super();
	}

	
	
	public User(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}



	public User(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public void insert() throws SQLException {
		DaoUser dao = new DaoUser();
		dao.insertUser(this);

	}

	public String controlJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;
	}

	public static User parseJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, User.class);
	}

	
	 public int getId() { return id; }
	 
	 public void setId(int id) { this.id = id; }
	 

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}
    
	
	

	/*
	 * public int getRool() { return rool; }
	 * 
	 * public void setRool(int roll) { this.rool = roll; }
	 */

	/*
	 * @Override public String toString() { return "User [id=" + id + ", userName="
	 * + userName + ", password=" + password + ", email=" + email + ", roll=" + rool
	 * + "]"; }
	 */

}
