package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.FilmRepository;
import fr.fms.entities.Film;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@SpringBootApplication
public class ParcCinemaApplication implements CommandLineRunner {
	
	  @Autowired CityRepository cityRepository;
	  
	  @Autowired CinemaRepository cinemaRepository;
	  
	  @Autowired FilmRepository filmRepository;
	 
	public static void main(String[] args) {
		SpringApplication.run(ParcCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		generateData();
	}
	
	public void generateData() {
		City toulouse = cityRepository.save(new City(null, "Toulouse", null));
		City saintAmandLesEaux = cityRepository.save(new City(null, "Saint-Amand-Les-Eaux", null));
		City nice = cityRepository.save(new City(null, "Nice", null));
		City rennes = cityRepository.save(new City(null, "Rennes", null));

		Cinema americanCosmo = cinemaRepository
				.save(new Cinema(null, "American Cosmograph", "24 Rue Montardy", "0561236620", 3, toulouse, null));
		Cinema pathWilson = cinemaRepository.save(new Cinema(null, "Pathé Wilson", " 3 Pl. du Président Thomas Wilson",
				"0892696696", 10, toulouse, null));
		Cinema cinamand = cinemaRepository.save(
				new Cinema(null, "Cin'amand", "E.Leclerc, Rocade du N,", "0369266620", 8, saintAmandLesEaux, null));
		Cinema pathMass = cinemaRepository
				.save(new Cinema(null, "Pathé Masséna", "31 Av. Jean Médecin", "0892696696", 5, nice, null));
		Cinema rialto = cinemaRepository
				.save(new Cinema(null, "Rialto", "4 rue de Rivoli", "0568120452", 3, nice, null));
		Cinema pathRennes = cinemaRepository
				.save(new Cinema(null, "Pathé Rennes", "12 rue Yvonne Jean-Haffen", "0892696696", 11, rennes, null));
		Cinema arvor = cinemaRepository
				.save(new Cinema(null, "Cinema Arvor", "11 rue de Châtillon", "0299387804", 3, rennes, null));

		filmRepository.save(new Film(null, "GodFather", "Francis Ford Coppola", 175, americanCosmo));
		filmRepository.save(new Film(null, "Trainspotting", "Danny Boyle", 75, americanCosmo));
		filmRepository.save(new Film(null, "Delivrance", "John Boorman", 110, cinamand));
		filmRepository.save(new Film(null, "Akira", "Katsuhiro Otomo", 124, pathWilson));
		filmRepository.save(new Film(null, "Lost highway", "David Lynch", 71, pathWilson));
		filmRepository.save(new Film(null, "The Father", "Florian Zeller", 97, arvor));
		filmRepository.save(new Film(null, "GodFather 2", "Francis Ford Coppola", 202, pathRennes));
		filmRepository.save(new Film(null, "Le royaume des chats", "Hiroyuki Morita", 75, rialto));
		filmRepository.save(new Film(null, "Les Goonies", "Richard Donner", 114, pathMass));
		filmRepository.save(new Film(null, "Vice-versa", "Pete Docter", 95, pathMass));
		filmRepository.save(new Film(null, "L'arnaque", "George Roy Hill", 129, americanCosmo));
		filmRepository.save(new Film(null, "The crow", "Alex Proyas", 102, arvor));
		cityRepository.findAll().forEach(c -> System.out.println(c));
		cinemaRepository.findAll().forEach(a -> System.out.println(a));
		filmRepository.findAll().forEach(f -> System.out.println(f));

	}
	 
}
