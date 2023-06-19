package fr.fms.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IbusinessImpl;
import fr.fms.dao.CinemaRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@Controller
public class CinemaController {
	@Autowired
	IbusinessImpl business;
	private final Logger logger = LoggerFactory.getLogger(CinemaController.class);
	@GetMapping("/")
	public String home() {
		return "cinemas";
	}
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
}
