package com.qa.demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// ORM - Object Relational Mapper
@Entity(name = "flower")
public class Flower {
	
	// INSERT INTO flower  (type, colour...) VALUES ('rose', 'red');
	
	// INSERT  INTO <CLASS NAME> VALUES (flower.getType() , flower,getColour());

	@Id // PK
	@GeneratedValue // AI
	private long id;

	@Column(name = "type", length = 60, unique = false)
	private String type;

	private int height;

	@Column(nullable = false)
	private String colour;

	@Column
	private double price;

	@Column(nullable = false)
	private boolean poisonous;

	@ManyToOne // The class it relates to
	private Garden garden;
	
	public Flower() { // Entities MUST have a default constructor
	}

	public Flower(String type, int height, String colour, double price, boolean poisonous) {
		super();
		this.type = type;
		this.height = height;
		this.colour = colour;
		this.price = price;
		this.poisonous = poisonous;
	}

	public String getColour() {
		return colour;
	}

	public Garden getGarden() {
		return garden;
	}

	public int getHeight() {
		return height;
	}

	public long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public boolean isPoisonous() {
		return poisonous;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPoisonous(boolean poisonous) {
		this.poisonous = poisonous;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flower other = (Flower) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (garden == null) {
			if (other.garden != null)
				return false;
		} else if (!garden.equals(other.garden))
			return false;
		if (height != other.height)
			return false;
		if (id != other.id)
			return false;
		if (poisonous != other.poisonous)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flower [id=" + id + ", type=" + type + ", height=" + height + ", colour=" + colour + ", price=" + price
				+ ", poisonous=" + poisonous + ", garden=" + garden + "]";
	}

}
