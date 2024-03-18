package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCook;

public class CookList {
	private List<Cook> cooks;

	public CookList() {
		this.cooks = new ArrayList<>();
	}

	public List<Cook> getAllCooks() {
		return cooks;
	}

	public void addCook(Cook cookData) {
		List<Ingredient> ingredients = cookData.getIngredient();
		List<Preparation> preparations = cookData.getPreparation();

		if (ingredients != null && preparations != null) {
			Cook cook = new Cook(cookData.getTitle(), cookData.getQuantity(), cookData.getTimePreparation(),
					cookData.getAuthor(), cookData.getPhoto(), cookData.getState(), ingredients, preparations);
			cooks.add(cook);
			System.out.println("Se ha añadido la siguiente receta: " + cook);
		} else {
			System.out.println("Error: Los ingredientes o las preparaciones son nulos.");
		}
	}

	public void insert() throws SQLException {
		DaoCook dao = new DaoCook();
		for (Cook cook : cooks) {
			dao.insertCookTable(cook);
		}
	}
    
	public List<Cook> getCooks() {
		return cooks;
	}

	public void setCooks(List<Cook> cooks) {
		this.cooks = cooks;
	}

	@Override
	public String toString() {
		return "CookList [cooks=" + cooks + "]";
	}
}
