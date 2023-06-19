package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.fms.entities.Cinema;
import fr.fms.entities.City;


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
}
