package com.qa.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.persistence.domain.Garden;
import com.qa.demo.service.GardenService;

@RestController
@RequestMapping("/garden")
public class GardenController {

	private GardenService service;

	public GardenController(GardenService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Garden> create(@RequestBody Garden flower) {
		return new ResponseEntity<Garden>(this.service.create(flower), HttpStatus.CREATED);
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<Garden> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.read(id));
	}

	@GetMapping("/read")
	public ResponseEntity<List<Garden>> read() {
		return new ResponseEntity<List<Garden>>(this.service.read(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Garden> update(@PathVariable Long id, @RequestBody Garden flower) {
		return new ResponseEntity<Garden>(this.service.update(flower, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return (this.service.delete(id) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.NO_CONTENT));
//		if (this.service.delete(id)) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
	}

}
