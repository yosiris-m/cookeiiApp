package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Cook {
	private int id;
	private String title;
	private int quantity;
	private String timePreparation;
	private String author;
	private String photo;
	private String state;
	private int authorId;
	private boolean isOwner;
	private List<Ingredient> ingredients;
	private List<Preparation> preparations;

	public Cook() {
		// Inicializa la lista en el constructor
		this.preparations = new ArrayList<>();
		this.ingredients = new ArrayList<>();
	}

	public Cook(String title, int quantity, String timePreparation, String photo, String state,
			List<Ingredient> ingredient, List<Preparation> preparation) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.timePreparation = timePreparation;
		this.photo = photo;
		this.state = state;
		this.ingredients = ingredient;
		this.preparations = preparation;
	}

	public Cook(int id, String title, int quantity, String timePreparation, String author, String photo, String state,
			int authorId, List<Ingredient> ingredient, List<Preparation> preparation) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.timePreparation = timePreparation;
		this.author = author;
		this.photo = photo;
		this.state = state;
		this.authorId = authorId;
		this.ingredients = ingredient;
		this.preparations = preparation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTimePreparation() {
		return timePreparation;
	}

	public void setTimePreparation(String timePreparation) {
		this.timePreparation = timePreparation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public List<Ingredient> getIngredient() {
		return ingredients;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredients = ingredient;
	}

	public List<Preparation> getPreparation() {
		return preparations;
	}

	public void setPreparation(List<Preparation> preparation) {
		this.preparations = preparation;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	@Override
	public String toString() {
		return "Cook [id=" + id + ", title=" + title + ", quantity=" + quantity + ", timePreparation=" + timePreparation
				+ ", author=" + author + ", photo=" + photo + ", state=" + state + ", authorId=" + authorId
				+ ", isOwner=" + isOwner + ", ingredients=" + ingredients + ", preparations=" + preparations + "]";
	}

}
