package fr.fms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ParcCinemaJpaTests {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CinemaRepository cinemaRepository;
	@Test
	void should_find_all_cities() {
		cityRepository.save(new City(null, "Soumoulou",null));
		Iterable<City> cities = cityRepository.findAll();
		assertThat(cities).isNotEmpty();
	}
	@Test
	void should_find_all_cinemas() {
		cinemaRepository.save(new Cinema(null, "Movies", "12 rue du monde", "0623025155",2, null, null));
		Iterable<Cinema> cinemas = cinemaRepository.findAll();
		assertThat(cinemas).isNotEmpty();
	}
}
