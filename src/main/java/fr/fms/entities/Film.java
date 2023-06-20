package fr.fms.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(min=1,max=50)
	private String title;
	@NotNull
	@Size(min=1,max=50)
	private String director;
	@NotNull
	@Digits(fraction = 0, integer = 3, message = "Durée invalide")
	private int duration;
	@ManyToOne
	private Cinema cinema;
}
