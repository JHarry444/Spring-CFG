package com.qa.demo.services;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.persistence.repo.FlowerRepo;
import com.qa.demo.service.FlowerService;

@RunWith(MockitoJUnitRunner.class)
public class FlowerServiceUnitTest {

	private final Flower FLOWER = new Flower("orchid", 27, "white", 44.94, false);

	private Flower savedFlower;

	@Mock
	private FlowerRepo repo;

	@InjectMocks
	private FlowerService service;

	@Before
	public void init() {
		this.savedFlower = new Flower(FLOWER.getType(), FLOWER.getHeight(), FLOWER.getColour(), FLOWER.getPrice(),
				FLOWER.isPoisonous());
		this.savedFlower.setId(1L);
	}

	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(FLOWER)).thenReturn(savedFlower);

		assertEquals(savedFlower, service.create(FLOWER));

		Mockito.verify(this.repo, Mockito.times(1)).save(FLOWER);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedFlower.getId())).thenReturn(Optional.of(savedFlower));

		Flower newFlower = new Flower("pier", 5, "white", 0, true);
		Flower newFlowerWthID = new Flower("pier", 5, "white", 0, true);
		newFlowerWthID.setId(savedFlower.getId());

		Mockito.when(this.repo.save(newFlowerWthID)).thenReturn(newFlowerWthID);

		assertEquals(newFlowerWthID, this.service.update(newFlower, savedFlower.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedFlower.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newFlowerWthID);
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
