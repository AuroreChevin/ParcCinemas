package fr.fms.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import fr.fms.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{
	Page<Cinema> findByNameCinemaContains(String keyword, Pageable pageable);
	Page<Cinema> findByCityIdCity(Long idCity, Pageable pageable);
}
