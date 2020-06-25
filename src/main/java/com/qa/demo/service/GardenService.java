package com.qa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.demo.exceptions.GardenNotFoundException;
import com.qa.demo.persistence.domain.Garden;
import com.qa.demo.persistence.repo.GardenRepo;

@Service
public class GardenService {

	private GardenRepo repo;

	public GardenService(GardenRepo repo) {
		super();
		this.repo = repo;
	}

	// INSERT INTO garden VALUES (...);
	public Garden create(Garden garden) {
//		this.repo.save(garden);
		return this.repo.save(garden);
	}

	// SELECT * FROM garden;
	public List<Garden> read() {
		return this.repo.findAll();
	}
	
	// SELECT * FROM garden WHERE id = ;
	public Garden read(long id) {
		return this.repo.findById(id).orElseThrow(() -> new GardenNotFoundException());
	}

	// UPDATE garden set colour = ...;
	public Garden update(Garden garden, long id) {
		
		Optional<Garden> optGarden = this.repo.findById(id);
		
		Garden toUpdate = optGarden.orElseThrow(() -> new GardenNotFoundException());
		
		toUpdate.setSquareFootage(garden.getSquareFootage());
		
		return this.repo.save(toUpdate);
	}

	// DELETE FROM garden WHERE id =;
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
