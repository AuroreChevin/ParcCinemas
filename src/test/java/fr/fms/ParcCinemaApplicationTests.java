package fr.fms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@SpringBootTest
class ParcCinemaApplicationTests {
	@Autowired
	IbusinessImpl business;
	@Test
	void contextLoads() {
	}
	
	@Test
	void testReadAllCinemas() throws Exception {
		Iterable<Cinema> cinemas = business.readAllCinemas();
		assertThat(cinemas).isNotEmpty();
	}
	@Test
	void testReadAllCities() throws Exception {
		Iterable<City> cities = business.readAllCities();
		assertThat(cities).isNotEmpty();
	}
	@Test
	void testReadCinemasByidCity() throws Exception {
		Long id =(long) 1;
		if(id>0) {
			Iterable<Cinema> cinemas = business.readAllCinemasByIdCity(id);
			assertThat(cinemas).isNotEmpty();
		}
		
	}
}
