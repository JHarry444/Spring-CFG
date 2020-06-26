package com.qa.demo.dto;

import java.util.List;

public class GardenDTO {

	private long id;

	private double squareFootage;

	private List<FlowerDTO> flowers;

	public GardenDTO(long id, double squareFootage, List<FlowerDTO> flowers) {
		super();
		this.id = id;
		this.squareFootage = squareFootage;
		this.flowers = flowers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(double squareFootage) {
		this.squareFootage = squareFootage;
	}

	public List<FlowerDTO> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<FlowerDTO> flowers) {
		this.flowers = flowers;
	}

}
