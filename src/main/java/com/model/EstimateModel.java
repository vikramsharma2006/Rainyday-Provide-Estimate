package com.model;




public class EstimateModel {

	
	private int id;
	private String name;
	private String CompanyName;
	private int currentPrice;
	private String category;
	
	public EstimateModel(int id, String name, String companyName, int currentPrice, String category) {
		super();
		this.id = id;
		this.name = name;
		CompanyName = companyName;
		this.currentPrice = currentPrice;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "EstimateModel [id=" + id + ", name=" + name + ", CompanyName=" + CompanyName + ", currentPrice="
				+ currentPrice + ", category=" + category + "]";
	}

	
	
	

}
