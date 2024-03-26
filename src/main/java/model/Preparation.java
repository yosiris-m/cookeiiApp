package model;


public class Preparation {
	private int id;
	private String preparation;

	public Preparation() {
		super();
	}

	

	public Preparation(String preparation) {
		super();
		this.preparation = preparation;
	}


	public Preparation(int id, String preparation) {
		super();
		this.id = id;
		this.preparation = preparation;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	@Override
	public String toString() {
		return "Preparation [preparation=" + preparation + "]";
	}

}
