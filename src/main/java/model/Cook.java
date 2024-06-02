package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una receta.
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

	/**
	 * Constructor por defecto que inicializa las listas de ingredientes y
	 * preparaciones.
	 */
	public Cook() {
		this.preparations = new ArrayList<>();
		this.ingredients = new ArrayList<>();
	}

	/**
	 * Constructor que inicializa todos los atributos de la receta.
	 * 
	 * @param title           Título de la receta.
	 * @param quantity        Cantidad de porciones que produce la receta.
	 * @param timePreparation Tiempo de preparación estimado.
	 * @param photo           URL o ruta de la foto de la receta.
	 * @param state           Estado actual de la enfermedad (brote, remisión,
	 *                        etc.).
	 * @param ingredient      Lista de ingredientes necesarios para la receta.
	 * @param preparation     Lista de pasos de preparación de la receta.
	 */
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

	/**
	 * Constructor que inicializa todos los atributos de la receta, incluyendo el ID
	 * y el autor.
	 * 
	 * @param id              ID único de la receta.
	 * @param title           Título de la receta
	 * @param quantity        Cantidad de porciones que produce la receta.
	 * @param timePreparation Tiempo de preparación estimado.
	 * @param author          Nombre del autor de la receta.
	 * @param photo           URL o ruta de la foto de la receta.
	 * @param state           Estado actual de la enfermedad (brote, remisión,
	 *                        etc.).
	 * @param authorId        ID del autor de la receta.
	 * @param ingredient      Lista de ingredientes necesarios para la receta.
	 * @param preparation     Lista de pasos de preparación de la receta.
	 */
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

	/**
	 * Retorna el ID de la receta.
	 *
	 * @return ID de la receta.
	 */
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

	/**
     * Retorna la lista de ingredientes de la receta.
     *
     * @return Lista de ingredientes.
     */
	public List<Ingredient> getIngredient() {
		return ingredients;
	}

	 /**
     * Establece la lista de ingredientes de la receta.
     *
     * @param ingredients Lista de ingredientes.
     */
	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredients = ingredient;
	}

	  /**
     * Establece la lista de pasos de preparación de la receta.
     *
     * @param preparations Lista de pasos de preparación.
     */
	public List<Preparation> getPreparation() {
		return preparations;
	}

	 /**
     * Establece la lista de pasos de preparación de la receta.
     *
     * @param preparations Lista de pasos de preparación.
     */
	public void setPreparation(List<Preparation> preparation) {
		this.preparations = preparation;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	 * Agrega ingredientes a la receta a partir de un array de nombres de
	 * ingredientes.
	 * 
	 * @param ingredientArray ingredientArray Array de nombres de ingredientes a
	 *                        agregar.
	 */
	public void addIngredients(String[] ingredientArray) {
		List<Ingredient> ingredients = new ArrayList<>();
		for (String ingredient : ingredientArray) {
			ingredients.add(new Ingredient(ingredient));
		}
		this.ingredients = ingredients;
	}

	/**
	 * Agrega preparaciones a la receta a partir de un array de pasos de
	 * preparación.
	 * 
	 * @param preparationArray Array de pasos de preparación a agregar.
	 */
	public void addPreparations(String[] preparationArray) {
		List<Preparation> preparations = new ArrayList<>();
		for (String preparation : preparationArray) {
			preparations.add(new Preparation(preparation));
		}
		this.preparations = preparations;
	}

	/**
	 * Retorna una representación en forma de cadena de la receta.
	 * 
	 * @return Cadena que representa la receta.
	 */
	@Override
	public String toString() {
		return "Cook [id=" + id + ", title=" + title + ", quantity=" + quantity + ", timePreparation=" + timePreparation
				+ ", author=" + author + ", photo=" + photo + ", state=" + state + ", authorId=" + authorId
				+ ", isOwner=" + isOwner + ", ingredients=" + ingredients + ", preparations=" + preparations + "]";
	}

}
