package com.qa.demo.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.demo.persistence.domain.Garden;
import com.qa.demo.persistence.repo.GardenRepo;
import com.qa.demo.service.GardenService;

@RunWith(MockitoJUnitRunner.class)
public class GardenServiceUnitTest {

	private Garden garden;

	private Garden savedGarden;

	@Mock
	private GardenRepo repo;

	@InjectMocks
	private GardenService service;

	@Before
	public void init() {
		this.garden = new Garden();
		this.garden.setSquareFootage(42);
		this.savedGarden = new Garden();
		this.savedGarden.setSquareFootage(this.garden.getSquareFootage());
		this.savedGarden.setId(1L);
	}

	@Test
	public void testCreate() {
		when(this.repo.save(garden)).thenReturn(savedGarden);

		assertEquals(savedGarden, service.create(garden));

		verify(this.repo, Mockito.times(1)).save(garden);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedGarden.getId())).thenReturn(Optional.of(savedGarden));

		Garden newGarden = new Garden();
		newGarden.setSquareFootage(4494);
		
		Garden newGardenWthID = new Garden();
		newGardenWthID.setSquareFootage(newGarden.getSquareFootage());
		newGardenWthID.setId(savedGarden.getId());

		Mockito.when(this.repo.save(newGardenWthID)).thenReturn(newGardenWthID);

		assertEquals(newGardenWthID, this.service.update(newGarden, savedGarden.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedGarden.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newGardenWthID);
	}

	@Test
	public void testDeleteFails() {
		final long ID = 1L;
		final boolean RESULT = true;
		Mockito.when(this.repo.existsById(ID)).thenReturn(RESULT);

		assertEquals(RESULT, this.service.delete(ID));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);

	}

	@Test
	public void testDeleteSucceeds() {
		final long ID = 1L;
		final boolean RESULT = false;
		Mockito.when(this.repo.existsById(ID)).thenReturn(RESULT);

		assertEquals(RESULT, this.service.delete(ID));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}

}
