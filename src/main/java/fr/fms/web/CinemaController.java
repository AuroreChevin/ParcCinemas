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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;

@Controller
public class CinemaController {
	@Autowired
	IbusinessImpl business;
	private final Logger logger = LoggerFactory.getLogger(CinemaController.class);
	
	@GetMapping("/")
	public String home() {
		return "redirect:/index";
	}
	/**
	 * Méthode en GET correspondant à l'url .../index ou page d'accueil de l'application
	 * @param model sert à ajouter des éléments partagés avec la vue
	 * @param page correspond à la page active côté front, cela assure la pagination / par défaut vaut 0
	 * @param kw est un mot dont on souhaite afficher tous les articles le contenant / par défaut chaine vide
	 * @param idCity est l'identifiant de la ville dont on souhaite afficher tous les cinémas / par défaut vaut 0 
	 * @return la page cinemas.html 
	 */
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name="page", defaultValue = "0") int page,
									@RequestParam(name="keyword", defaultValue = "") String kw,
									@RequestParam(name="idCity" , defaultValue = "0") Long idCity) {
		Page<Cinema> cinemas;
		List<City> cities = business.readAllCities();
		try {
			if(idCity >0) {
				cinemas = business.readAllCinemasPagesByIdCity(idCity, page);
			}
			else cinemas= business.readAllCinemasPages(kw, page);
			model.addAttribute("listCinemas", cinemas.getContent());
			model.addAttribute("pages", new int[cinemas.getTotalPages()]);			
			model.addAttribute("currentPage", page);
			model.addAttribute("searchByKeyword", kw);
			model.addAttribute("listCities", cities);
		}catch(Exception e) {
			logger.error("Impossible d'afficher les cinémas" , e.getMessage());
		}
		return "cinemas";
	}
	/**
	 * Méthode en GET correspondant à l'url .../deleteCinema consistant à supprimer un cinéma à partir de son id
	 * @param idCinema du cinema
	 * @param page
	 * @return une redirection vers ../index avec les éléments permettant de garder le contexte actuel
	 */
	@GetMapping("/deleteCinema")
	public String deleteCinema(Long idCinema,int page) {
		try {
			business.deleteCinema(idCinema);
		}catch(Exception e) {
			logger.error("Impossible de supprimer le cinema" , e.getMessage());
		}
		return "redirect:/index";
	}
	/**
	 * Méthode en GET correspondant à l'url .../cinema permettant d'ajouter un nouveau cinema
	 * @param model
	 * @return page cinema.html
	 */
	@GetMapping("/cinema")
	public String cinema(Model model) {
		model.addAttribute("cinema", new Cinema());
		try {
		List<City> cities = business.readAllCities();
		model.addAttribute("listCities", cities);
		}catch(Exception e) {
			logger.error("Impossible d'afficher la liste des cinémas" , e.getMessage());
		}
		return "cinema";
	}
	/**
	 * Méthode en POST correspondant à l'url .../saveCinema visant à sauvegarder ou mettre à jour un cinéma
	 * @param cinema
	 * @param bindingResult sert à la gestion des problèmes de saisie ne correspondant pas à ce qui est attendu / voir validation dans l'entité Film
	 * @return redirection vers ../index
	 */
	@PostMapping("/saveCinema")
	public String saveCinema(@Valid Cinema cinema, BindingResult bindingResult, Model model) {
		try {
			if(bindingResult.hasErrors()) {
				List<City> cities = business.readAllCities();
				model.addAttribute("listCities", cities);return "cinema";
		}
		business.saveCinema(cinema);
		}catch(Exception e) {
			logger.error("Impossible d'enregistrer le cinéma" , e.getMessage());
		}
		return "redirect:/index";
	}
	/**
	 * Méthode en GET correspondant à l'url .../editCinema permettant d'afficher un cinéma en vue de sa mise à jour
	 * @param idCinema du cinema
	 * @param model
	 * @return page cinema.html pour l'édition d'un cinéma
	 */
	@GetMapping("/editCinema")
	public String editCinema(Long idCinema, Model model) {
		Cinema cinema;
		List<City> cities;
		try {
			cinema = business.readOneCinema(idCinema);
			model.addAttribute("cinema", cinema);
			cities = business.readAllCities(); 
			model.addAttribute("listCities", cities);			 
		}catch(Exception e) {
			logger.error("Impossible de mettre à jour le film" , e.getMessage());
		}
		return "editCinema";
	}
	@RequestMapping("/greating")
	public @ResponseBody String greating() {
		return business.great();
	}
}
