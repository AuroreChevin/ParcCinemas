package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Film {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFilm;
	@NotNull
	private String title;
	@NotNull
	private String director;
	@NotNull
	private int duration;
	@ManyToOne
	private Cinema cinema;
}
