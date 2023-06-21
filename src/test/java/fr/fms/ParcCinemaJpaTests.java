package fr.fms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.FilmRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ParcCinemaJpaTests {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	FilmRepository filmRepository;
	@Test
	void should_find_all_cities() {
		cityRepository.save(new City(null, "Soumoulou",null));
		Iterable<City> cities = cityRepository.findAll();
		assertThat(cities).isNotEmpty();
	}
	
	
	@Test
	void should_find_all_cinemas() {
		cinemaRepository.save(new Cinema(null, "Movies", "12 rue du monde", "0623025155", 2, null, null));
		Iterable<Cinema> cinemas = cinemaRepository.findAll();
		assertThat(cinemas).isNotEmpty();
	}

	@Test
	void should_find_all_films() {
		filmRepository.save(new Film(null, "toto", "kwak", 56, null));
		Iterable<Film> films = filmRepository.findAll();
		assertThat(films).isNotEmpty();
	}
	@Test
	void should_find_cinema_by_idCity() {
		cityRepository.save(new City((long)1, "Soumoulou",null));
		cinemaRepository.save(new Cinema(null, "Movies", "12 rue du monde", "0623025155", 2, null, null));
		Iterable<Cinema> cinemas = cinemaRepository.findByCityIdCity((long)1);
		assertThat(cinemas).isNotEmpty();
	}
}
