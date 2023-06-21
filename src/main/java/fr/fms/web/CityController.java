package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;

@Controller
public class CityController {
	@Autowired
	IbusinessImpl business;
	private final Logger logger = LoggerFactory.getLogger(CityController.class);
	/**
	 * Méthode en GET correspondant à l'url .../listCities donnant la liste des villes
	 * @param model sert à ajouter des éléments partagés avec la vue
	 * @param page correspond à la page active côté front, cela assure la pagination / par défaut vaut 0 
	 * @return la page cities.html 
	 */
	@GetMapping("/listCities")
	public String cities(Model model, @RequestParam(name="page", defaultValue = "0") int page, Long idCity) throws Exception {
		Page<City> cities;
		List<Cinema> cinemas;
		try {
			cities = business.readAllCitiesPages(page);
			cinemas = business.readAllCinemasByIdCity(idCity);
			model.addAttribute("listCities", cities.getContent());
			model.addAttribute("pages", new int[cities.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("listCinemas", cinemas);
		}catch(Exception e) {
			logger.error("Impossible d'afficher les villes" , e.getMessage());
		}
		return "cities";
	}
	/**
	 * Méthode en GET correspondant à l'url .../deleteCity consistant à supprimer une ville à partir de son id
	 * @param idCity
	 * @param page
	 * @return une redirection vers ../listCities avec les éléments permettant de garder le contexte actuel
	 */
	@GetMapping("/deleteCity")
	public String deleteCity(Long idCity) {
		try {
			business.deleteCity(idCity);
		}catch(Exception e) {
			logger.error("Impossible de supprimer la ville" , e.getMessage());
		}
		return "redirect:/listCities";
	}
	/**
	 * Méthode en POST correspondant à l'url .../saveCity visant à sauvegarder ou mettre à jour une ville
	 * @param city
	 * @param bindingResult sert à la gestion des problèmes de saisie ne correspondant pas à ce qui est attendu / voir validation dans l'entité Film
	 * @return redirection vers ../index
	 */
	@PostMapping("/saveCity")
	public String saveCiTy(@Valid City city, BindingResult bindingResult, Model model) {
		model.addAttribute("city", new City());
		try {
			if(bindingResult.hasErrors()) {return "cities";
			}
		business.saveCity(city);
		}catch(Exception e) {
			logger.error("Impossible d'enregistrer la ville" , e.getMessage());
		}
		return "redirect:/index";
	}
	
}
