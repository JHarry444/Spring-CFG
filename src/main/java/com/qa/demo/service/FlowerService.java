package com.qa.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.FlowerDTO;
import com.qa.demo.exceptions.FlowerNotFoundException;
import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.persistence.repo.FlowerRepo;

@Service
public class FlowerService {

	private FlowerRepo repo;
	private ModelMapper mapper;

	public FlowerService(FlowerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private FlowerDTO mapToDTO(Flower flower) {
		return this.mapper.map(flower, FlowerDTO.class);
	}

	// INSERT INTO flower VALUES (...);
	public FlowerDTO create(Flower flower) {
//		this.repo.save(flower);
		Flower saved =  this.repo.save(flower);
		return this.mapToDTO(saved);
	}

	// SELECT * FROM flower;
	public List<FlowerDTO> read() {
		List<FlowerDTO> dtos = new ArrayList<>();
		for (Flower flower : this.repo.findAll()) {
			dtos.add(this.mapToDTO(flower));
		}
		return dtos;
//		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// SELECT * FROM flower WHERE id = ;
	public FlowerDTO read(long id) {
		Flower found = this.repo.findById(id).orElseThrow(() -> new FlowerNotFoundException());
		return this.mapToDTO(found);
	}

	// UPDATE flower set colour = ...;
	public FlowerDTO update(Flower flower, long id) {
		
		Optional<Flower> optFlower = this.repo.findById(id);
		
		Flower toUpdate = optFlower
				.orElseThrow(() -> new FlowerNotFoundException());
		
		toUpdate.setType(flower.getType());
		toUpdate.setColour(flower.getColour());
		toUpdate.setHeight(flower.getHeight());
		toUpdate.setPoisonous(flower.isPoisonous());
		toUpdate.setPrice(flower.getPrice());
		
		Flower updated =  this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	// DELETE FROM flower WHERE id =;
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
