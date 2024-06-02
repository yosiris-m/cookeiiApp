package model;

/**
 * Clase que representa un ingrediente dela receta.
 */
public class Ingredient {
	private int id;
	private String ingredient;

	/**
	 * Constructor por defecto de la clase Ingredient.
	 */
	public Ingredient() {
		super();
	}

	/**
	 * Constructor que inicializa un ingrediente con su nombre.
	 * 
	 * @param ingredient ingredient Nombre del ingrediente.
	 */
	public Ingredient(String ingredient) {
		this.ingredient = ingredient;
	}

	
	/*public int ingredientId(int id) {
		return id;
	}*/

	 /**
     * Setter para establecer el ID del ingrediente.
     * 
     * @param id ID del ingrediente.
     */
	public int getId() {
		return id;
	}

	  /**
     * Setter para establecer el ID del ingrediente.
     * 
     * @param id ID del ingrediente.
     */
	public void setId(int id) {
		this.id = id;
	}

	  /**
     * Getter para obtener el nombre del ingrediente.
     * 
     * @return Nombre del ingrediente.
     */
	public String getIngredient() {
		return ingredient;
	}
	
	/**
     * Setter para establecer el nombre del ingrediente.
     * 
     * @param ingredient Nombre del ingrediente.
     */

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Retorna una representación en forma de cadena del ingrediente.
	 *
	 * @return Cadena que representa el ingrediente
	 */
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", ingredient=" + ingredient + "]";
	}

}
