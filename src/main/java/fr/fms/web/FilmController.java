package fr.fms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.fms.dao.FilmRepository;
import fr.fms.entities.Film;

@Controller
public class FilmController {
	@Autowired
	FilmRepository filmRepository;
	@GetMapping("/listFilms")
	public String films(Model model) {
		List<Film> films = filmRepository.findAll();
		model.addAttribute("listFilms", films);
		return "films";
	}
}
