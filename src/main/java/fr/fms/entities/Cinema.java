package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Cinema {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCinema;
	@NotNull
	private String nameCinema;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	@NotNull
	private int nbRoom;
	@ManyToOne
	private City city;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "cinema")
	private Collection<Film> films;
}
