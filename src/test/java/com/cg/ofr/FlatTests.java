package com.cg.ofr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.ofr.entities.Flat;
import com.cg.ofr.entities.FlatAddress;
import com.cg.ofr.exception.FlatNotFoundException;
import com.cg.ofr.service.IFlatService;

@SpringBootTest
public class FlatTests extends OnlineFlatRentalApplicationTests {

	@Autowired
	private IFlatService flatService;

	Flat flat1 = new Flat(199, 8888,
			new FlatAddress(1000, "gandhi-nagar", "Srikakulam", "Andhra Pradesh", 532284, "India"), "Yes");
	Flat flat2 = new Flat(299, 7777,
			new FlatAddress(1020, "gandhi-nagar", "Srikakulam", "Andhra Pradesh", 532284, "India"), "Yes");
	Flat flat3 = new Flat(399, 6666,
			new FlatAddress(1030, "gandhi-nagar", "Srikakulam", "Andhra Pradesh", 532284, "India"), "Yes");

	@Test
	public void addFlatTest() throws FlatNotFoundException {
		Flat flat = flatService.addFlat(flat1);
		assertEquals(flat1.getFlatId(), flat.getFlatId());
		flatService.deleteFlat(flat1);

	}

	@Test
	public void updateFlatTest() throws FlatNotFoundException {
		flatService.addFlat(flat1);
		Flat newFlat = new Flat(199, 8888,
				new FlatAddress(100, "gandhi-nagar", "Srikakulam", "Andhra Pradesh", 532284, "India"), "No");
		Flat updatesFlat = flatService.updateFlat(newFlat);
		assertEquals(updatesFlat.getAvailibilty(), newFlat.getAvailibilty());
		flatService.deleteFlat(flat2);
	}

	@Test
	public void viewFlatTest() throws FlatNotFoundException {
		flatService.addFlat(flat3);
		Flat flat = flatService.viewFlat(399);
		assertEquals(flat.toString(), flat3.toString());
		flatService.deleteFlat(flat3);
	}

	@Test
	public void viewByCostAndAvailibality() {
		flatService.addFlat(flat1);
		flatService.addFlat(flat2);
		flatService.addFlat(flat3);
		List<Flat> flatList = flatService.viewAllFlatByCost(6666, "Yes");
		assertEquals(flatList.size(), 1);
		flatService.deleteFlat(flat1);
		flatService.deleteFlat(flat2);
		flatService.deleteFlat(flat3);

	}

	@Test
	public void deleteFlatTest() {
		flatService.addFlat(flat1);
		Flat deletedFlat = flatService.deleteFlat(flat1);
		assertNotNull(deletedFlat);

	}

}