package com.qa.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.dto.FlowerDTO;
import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.service.FlowerService;

@RestController
@CrossOrigin
@RequestMapping("/flower")
public class FlowerController {

	private FlowerService service;

	public FlowerController(FlowerService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<FlowerDTO> create(@RequestBody Flower flower) {
		return new ResponseEntity<FlowerDTO>(this.service.create(flower), HttpStatus.CREATED);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<FlowerDTO> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.read(id));
	}

	@GetMapping("/read")
	public ResponseEntity<List<FlowerDTO>> read() {
		return new ResponseEntity<List<FlowerDTO>>(this.service.read(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<FlowerDTO> update(@PathVariable Long id, @RequestBody Flower flower) {
		return new ResponseEntity<FlowerDTO>(this.service.update(flower, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return (this.service.delete(id) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT));
//		if (this.service.delete(id)) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
	}

}
