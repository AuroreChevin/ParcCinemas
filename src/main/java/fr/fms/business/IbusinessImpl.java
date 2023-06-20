package fr.fms.business;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.FilmRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;

@Service
public class IbusinessImpl implements Ibusiness{
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	FilmRepository filmRepository;
	@Override
	public Page<Cinema> readAllCinemasPages(String kw, int page) throws Exception {
		return cinemaRepository.findByNameCinemaContains(kw, PageRequest.of(page, 4));
	}
	@Override
	public Page<Cinema> readAllCinemasPagesByIdCity(Long idCity, int page) throws Exception {
		return cinemaRepository.findByCityIdCity(idCity, PageRequest.of(page, 4));
	}
	@Override
	public List<City> readAllCities() {
		return cityRepository.findAll();
	}
	@Override
	public Page<Film> readAllFilmsPagesByIdCinema(Long idCinema, int page) throws Exception {
		return filmRepository.findByCinemaIdCinema(idCinema, PageRequest.of(page, 4));
	}
	@Override
	public void deleteFilm(Long idFilm) throws Exception {
		filmRepository.deleteById(idFilm);
	}
	@Override
	public List<Cinema> readAllCinemas() throws Exception {
		return cinemaRepository.findAll();
	}
	@Override
	public void saveFilm(Film film) throws Exception {
		filmRepository.save(film);
		
	}
	@Override
	public Film readOneFilm(Long idFilm) throws Exception{
		Optional<Film> optional = filmRepository.findById(idFilm);
		return optional.isPresent() ? optional.get() : null;
	}
	@Override
	public void deleteCinema(Long idCinema) throws Exception {
		cinemaRepository.deleteById(idCinema);
		
	}
	@Override
	public void saveCinema(Cinema cinema) throws Exception{
		cinemaRepository.save(cinema);
		
	}
	@Override
	public Cinema readOneCinema(Long idCinema) throws Exception{
		Optional<Cinema> optional = cinemaRepository.findById(idCinema);
		return optional.isPresent() ? optional.get() : null; 
	}
	
	
}
