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

	// select * from flower where flower.id = this.id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flowers == null) ? 0 : flowers.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(squareFootage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Garden other = (Garden) obj;
		if (flowers == null) {
			if (other.flowers != null)
				return false;
		} else if (!flowers.equals(other.flowers))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(squareFootage) != Double.doubleToLongBits(other.squareFootage))
			return false;
		return true;
	}

}
