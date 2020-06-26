package com.qa.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.GardenDTO;
import com.qa.demo.exceptions.GardenNotFoundException;
import com.qa.demo.persistence.domain.Garden;
import com.qa.demo.persistence.repo.GardenRepo;

@Service
public class GardenService {

	private GardenRepo repo;
	private ModelMapper mapper;

	public GardenService(GardenRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private GardenDTO mapToDTO(Garden garden) {
		return this.mapper.map(garden, GardenDTO.class);
	}

	// INSERT INTO garden VALUES (...);
	public GardenDTO create(Garden garden) {
//		this.repo.save(garden);
		Garden saved = this.repo.save(garden);
		return this.mapToDTO(saved);
	}

	// SELECT * FROM garden;
	public List<GardenDTO> read() {
		List<GardenDTO> dtos = new ArrayList<>();
		for (Garden garden : this.repo.findAll()) {
			dtos.add(this.mapToDTO(garden));
		}
		return dtos;
	}

	// SELECT * FROM garden WHERE id = ;
	public GardenDTO read(long id) {
		Garden found = this.repo.findById(id).orElseThrow(() -> new GardenNotFoundException());
		return this.mapToDTO(found);
	}

	// UPDATE garden set colour = ...;
	public GardenDTO update(Garden garden, long id) {

		Optional<Garden> optGarden = this.repo.findById(id);

		Garden toUpdate = optGarden.orElseThrow(() -> new GardenNotFoundException());

		toUpdate.setSquareFootage(garden.getSquareFootage());

		Garden updated =  this.repo.save(toUpdate);
		
		return this.mapToDTO(updated);
	}

	// DELETE FROM garden WHERE id =;
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
