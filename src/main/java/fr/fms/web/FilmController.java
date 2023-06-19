package fr.fms.web;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IbusinessImpl;
import fr.fms.entities.Film;

@Controller
public class FilmController {
	@Autowired
	IbusinessImpl business;
	private final Logger logger = LoggerFactory.getLogger(FilmController.class);
	@GetMapping("/listFilms")
	public String films(Model model, @RequestParam(name="page", defaultValue = "0") int page,
									 @RequestParam(name="idCinema" , defaultValue = "0") Long idCinema) throws Exception {
		Page<Film> films;
		try {
			films = business.readAllFilmsPagesByIdCinema(idCinema, page);
			model.addAttribute("listFilms", films.getContent());
			model.addAttribute("pages", new int[films.getTotalPages()]);
			model.addAttribute("currentPage", page);
		}catch(Exception e) {
			logger.error("Impossible d'afficher les films" , e.getMessage());
		}
		return "films";
	}
}
