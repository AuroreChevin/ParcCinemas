package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

}
