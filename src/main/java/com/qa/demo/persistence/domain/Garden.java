package com.qa.demo.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Garden {

	@Id // PK
	@GeneratedValue // AI
	private long id;

	private double squareFootage;
	
	@OneToMany(mappedBy = "garden") // the name of the field in Flower
	private List<Flower> flowers;

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

	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}
	
}
