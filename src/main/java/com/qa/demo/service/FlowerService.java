package com.qa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.demo.exceptions.FlowerNotFoundException;
import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.persistence.repo.FlowerRepo;

@Service
public class FlowerService {

	private FlowerRepo repo;

	public FlowerService(FlowerRepo repo) {
		super();
		this.repo = repo;
	}

	// INSERT INTO flower VALUES (...);
	public Flower create(Flower flower) {
//		this.repo.save(flower);
		return this.repo.save(flower);
	}

	// SELECT * FROM flower;
	public List<Flower> read() {
		return this.repo.findAll();
	}
	
	// SELECT * FROM flower WHERE id = ;
	public Flower read(long id) {
		return this.repo.findById(id).orElseThrow(() -> new FlowerNotFoundException());
	}

	// UPDATE flower set colour = ...;
	public Flower update(Flower flower, long id) {
		
		Optional<Flower> optFlower = this.repo.findById(id);
		
		Flower toUpdate = optFlower
				.orElseThrow(() -> new FlowerNotFoundException());
		
		toUpdate.setType(flower.getType());
		toUpdate.setColour(flower.getColour());
		toUpdate.setHeight(flower.getHeight());
		toUpdate.setPoisonous(flower.isPoisonous());
		toUpdate.setPrice(flower.getPrice());
		
		return this.repo.save(toUpdate);
	}

	// DELETE FROM flower WHERE id =;
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
