package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Size(min=1,max=50)
	private String nameCinema;
	@NotNull
	@Size(min=1,max=50)
	private String address;
	@NotNull
	@Digits(fraction = 10, integer = 10)
	private String phone;
	@NotNull
	@DecimalMin("1")
	private int nbRoom;
	@ManyToOne
	private City city;
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "cinema")
	@Cascade(CascadeType.ALL)
	private Collection<Film> films;
}
