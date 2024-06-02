package model;

/**
 * Clase que representa la preparación de la receta.
 */
public class Preparation {
	private int id;
	private String preparation;

	/**
	 * Constructor por defecto de la clase Preparation.
	 */
	public Preparation() {
		super();
	}

	/**
	 * Constructor que inicializa un objeto Preparation con una descripción de preparación específica.
	 * @param preparation Descripción del paso de preparación.
	 */
	public Preparation(String preparation) {
		super();
		this.preparation = preparation;
	}
	
	 /**
     * Obtiene el ID del paso de preparación.
     * 
     * @return ID del paso de preparación.
     */
	public int getId() {
		return id;
	}

	/**
     * Establece el ID del paso de preparación.
     * 
     * @param id ID del paso de preparación.
     */

	public void setId(int id) {
		this.id = id;
	}


	/**
     * Obtiene la descripción del paso de preparación.
     * 
     * @return Descripción del paso de preparación.
     */
	public String getPreparation() {
		return preparation;
	}
	

    /**
     * Retorna una representación en forma de cadena del objeto Preparation.
     *
     * @return Cadena que representa el objeto Preparation.
     */

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	@Override
	public String toString() {
		return "Preparation [preparation=" + preparation + "]";
	}

}
