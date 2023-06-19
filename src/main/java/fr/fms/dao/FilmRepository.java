package fr.fms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	Page<Film> findByCinemaIdCinema(Long idCinema, Pageable pageable);
}
