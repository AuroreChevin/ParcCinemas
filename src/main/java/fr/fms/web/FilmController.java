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
import fr.fms.entities.Film;

@Controller
public class FilmController {
	@Autowired
	IbusinessImpl business;
	private final Logger logger = LoggerFactory.getLogger(FilmController.class);
	/**
	 * Méthode en GET correspondant à l'url .../listFilms donnant la liste des des films par cinema
	 * @param model sert à ajouter des éléments partagés avec la vue
	 * @param page correspond à la page active côté front, cela assure la pagination / par défaut vaut 0
	 * @param idCinema est l'identifiant du cinema dont on souhaite afficher tous les films / par défaut vaut 0 
	 * @return la page films.html 
	 */
	@GetMapping("/listFilms")
	public String films(Model model, @RequestParam(name="page", defaultValue = "0") int page,
									 @RequestParam(name="idCinema" , defaultValue = "0") Long idCinema) throws Exception {
		Page<Film> films;
		try {
			films = business.readAllFilmsPagesByIdCinema(idCinema, page);
			model.addAttribute("listFilms", films.getContent());
			model.addAttribute("pages", new int[films.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("idCinema", idCinema);
			
		}catch(Exception e) {
			logger.error("Impossible d'afficher les films" , e.getMessage());
		}
		return "films";
	}
	/**
	 * Méthode en GET correspondant à l'url .../deleteFilm consistant à supprimer un film à partir de son id
	 * @param idFilm du film
	 * @param page
	 * @param idCinema
	 * @return une redirection vers ../listFilms avec les éléments permettant de garder le contexte actuel
	 */
	@GetMapping("/deleteFilm")
	public String deleteFilm(Long idFilm,Long idCinema,int page) {
		try {
			business.deleteFilm(idFilm);
		}catch(Exception e) {
			logger.error("Impossible de supprimer le film" , e.getMessage());
		}
		return "redirect:/listFilms?page="+page+"&idCinema="+idCinema;
	}
	/**
	 * Méthode en GET correspondant à l'url .../film permettant d'ajouter un nouveau film
	 * @param model
	 * @return page film.html
	 */
	@GetMapping("/film")
	public String film(Model model) {
		model.addAttribute("film", new Film());
		try {
		List<Cinema> cinemas = business.readAllCinemas();
		model.addAttribute("listCinemas", cinemas);
		}catch(Exception e) {
			logger.error("Impossible d'afficher la liste des cinémas" , e.getMessage());
		}
		return "film";
	}
	/**
	 * Méthode en POST correspondant à l'url .../saveFilm visant à sauvegarder ou mettre à jour un film
	 * @param film
	 * @param bindingResult sert à la gestion des problèmes de saisie ne correspondant pas à ce qui est attendu / voir validation dans l'entité Film
	 * @return redirection vers ../index
	 */
	@PostMapping("/saveFilm")
	public String saveFilm(@Valid Film film, BindingResult bindingResult, Model model) {
		try {
			if(bindingResult.hasErrors()) {
				List<Cinema> cinemas = business.readAllCinemas();
				model.addAttribute("listCinemas", cinemas);return "film";
		}
		business.saveFilm(film);
		}catch(Exception e) {
			logger.error("Impossible d'enregister le film" , e.getMessage());
		}
		return "redirect:/index";
	}
	/**
	 * Méthode en GET correspondant à l'url .../editFilm permettant d'afficher un film en vue de sa mise à jour
	 * @param idFilm du film
	 * @param model
	 * @return page film.html pour l'édition d'un article
	 */
	@GetMapping("/editFilm")
	public String editFilm(Long idFilm, Model model) {
		Film film;
		List<Cinema> cinemas;
		try {
			film = business.readOneFilm(idFilm);
			model.addAttribute("film", film);
			cinemas = business.readAllCinemas(); model.addAttribute("listCinemas", cinemas);
			 
		}catch(Exception e) {
			logger.error("Impossible de mettre à jour le film" , e.getMessage());
		}
		return "editFilm";
	}
}
