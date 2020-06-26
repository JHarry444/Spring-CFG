package com.qa.demo.dto;

public class FlowerDTO {

	private long id;

	private String type;

	private int height;

	private String colour;

	private double price;

	private boolean poisonous;

	public FlowerDTO(long id, String type, int height, String colour, double price, boolean poisonous) {
		super();
		this.id = id;
		this.type = type;
		this.height = height;
		this.colour = colour;
		this.price = price;
		this.poisonous = poisonous;
	}

	public FlowerDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isPoisonous() {
		return poisonous;
	}

	public void setPoisonous(boolean poisonous) {
		this.poisonous = poisonous;
	}

}
