package com.bilgeadam.SpringBootRestHibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// javax persistence 'lar olacak
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ogretmen
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(length = 50, unique = true, nullable = false)
	private String NAME;

	@Column(columnDefinition = "boolean default false")
	private Boolean IS_GICIK = false;

	// mecbur değilim
	// mappedBy sayesinde boşuna relation tablosu oluşturmayacak (N-N)
	// cascade entitymanager 'dan silinebilmesi için
	// bu ilişkiyi belirtmezsem 2. sorgu ile dersleri çekmek zorunda kalırım
	// fetchtype
	@JsonBackReference
	@OneToMany(mappedBy = "ogretmen", cascade =
	{ CascadeType.REMOVE, CascadeType.REFRESH })
	private Set<Ders> dersler = new HashSet<>();

	public Ogretmen(String nAME, Boolean iS_GICIK)
	{
		NAME = nAME;
		IS_GICIK = iS_GICIK;
	}
}
