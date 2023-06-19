package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@Service
public class IbusinessImpl implements Ibusiness{
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	CityRepository cityRepository;
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
	
}
