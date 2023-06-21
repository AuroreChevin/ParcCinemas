package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IbusinessImpl;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.FilmRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@SpringBootTest
class ParcCinemaApplicationTests {
	@Autowired
	IbusinessImpl business;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	FilmRepository filmRepository;
	@Test
	void contextLoads() {
	}
	/*
	 * @Test void testreadAllCinemasByIdCity() throws Exception { City city = new
	 * City((long)88, "Soumoulou",null); Cinema cinema = new Cinema(null, "Movies",
	 * "12 rue du monde", "0623025155", 2, city, null);
	 * assertEquals(business.readAllCinemasByIdCity((long)88), cinema);
	 * 
	 * }
	 */

}
