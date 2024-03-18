package model;

public class Ingredient {
 private int id;
 private  String ingredient;

  
  public Ingredient() {
	super();
}
  
  public Ingredient(String ingredient) {
      this.ingredient = ingredient;
  }
  
  public Ingredient(int id, String ingredient) {
	super();
	//this.id = id;
	this.ingredient = ingredient;
}

  public int ingredientId(int id) {
	  return id;
  }
  
  
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getIngredient() {
	return ingredient;
}

public void setIngredient(String ingredient) {
	this.ingredient = ingredient;
}

@Override
public String toString() {
	return "Ingredient [id=" + id + ", ingredient=" + ingredient + "]";
}



  
  
}
