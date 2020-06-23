package com.qa.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.service.FlowerService;

@RestController
public class FlowerController {
	
	private FlowerService service;

	public FlowerController(FlowerService service) {
		super();
		this.service = service;
	}


	@PostMapping("/create")
	public Flower create(@RequestBody  Flower flower) {
		return this.service.create(flower);
	}
	
	@GetMapping("/read")
	public List<Flower> read() {
		return this.service.read();
	}
	
	@PutMapping("/update/{id}")
	public Flower update(@PathVariable Long id, @RequestBody Flower flower) {
		return this.service.update(flower, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}
	
	
	
	
}
