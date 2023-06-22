package fr.fms.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;


public interface Ibusiness {
	/**
	 * Méthode qui renvoi la liste des cinémas en base paginée
	 * @param kw est un mot dont on souhaite afficher tous les cinémas le contenant 
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Cinema>
	 * @throws Exception
	 */
	public Page<Cinema> readAllCinemasPages(String kw, int page) throws Exception;
	/**
	 * Méthode qui renvoi la liste des cinémas en base paginée
	 * @param idCity est l'id de la ville dont on souhaite afficher tous les cinemas d'une ville
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Cinema>
	 * @throws Exception
	 */
	public Page<Cinema> readAllCinemasPagesByIdCity(Long idCity, int page) throws Exception;
	/**
	 * Méthode qui renvoi la liste des cities en base
	 * @return List<City>
	 */
	public List<City> readAllCities();
	/**
	 * Méthode qui renvoi la liste des cinémas en base paginée
	 * @param idCity est l'id du cinéma dont on souhaite afficher tous les films du cinema 
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Film>
	 * @throws Exception
	 */
	public Page<Film> readAllFilmsPagesByIdCinema(Long idCinema, int page) throws Exception;
	/**
	 * Méthode qui supprime un film à partir de son id
	 * @param idFilm
	 * @throws Exception
	 */
	public void deleteFilm(Long idFilm) throws Exception;
	/**
	 * Méthode qui renvoi la liste des cinémas 
	 * @return list<Cinema>
	 * @throws Exception
	 */
	public List<Cinema> readAllCinemas() throws Exception;
	/**
	 * Méthode qui ajoute un film
	 * @param film
	 * @throws Exception
	 */
	public void saveFilm (Film film) throws Exception;
	/**
	 * Méthode qui lit un film
	 * @param idFilm
	 * @throws Exception
	 */
	public Film readOneFilm(Long idFilm) throws Exception;
	/**
	 * Méthode qui supprime un cinéma à partir de son id
	 * @param idCinema
	 * @throws Exception
	 */
	public void deleteCinema(Long idCinema) throws Exception;
	/**
	 * Méthode qui ajoute un cinema
	 * @param cinema
	 * @throws Exception
	 */
	public void saveCinema(Cinema cinema) throws Exception;
	/**
	 * Méthode qui lit un cinema
	 * @param idCinema
	 * @throws Exception
	 */
	public Cinema readOneCinema(Long idCinema) throws Exception;
	/**
	 * Méthode qui renvoi la liste des villes en base paginée
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<City>
	 * @throws Exception
	 */
	public Page<City> readAllCitiesPages(int page) throws Exception;
	/**
	 * Méthode qui supprime une ville à partir de son id
	 * @param idCity
	 * @throws Exception
	 */
	public void deleteCity(Long idCity) throws Exception;
	/**
	 * Méthode qui ajoute une city
	 * @param city
	 * @throws Exception
	 */
	public void saveCity(City city) throws Exception;
	/**
	 * Méthode qui renvoi la liste des cinémas en base
	 * @param idCity est l'id de la ville dont on souhaite afficher tous les cinemas d'une ville
	 * @return City<Cinema>
	 * @throws Exception
	 */
	public List<Cinema> readAllCinemasByIdCity(Long idCity) throws Exception;
}
