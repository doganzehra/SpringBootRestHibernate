package com.bilgeadam.SpringBootRestHibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ders
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	// bir ogretmen birden fazla ders
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ogretmen ogretmen;

	// bir konu birden fazla ders
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Konu konu;

	public Ders(Ogretmen ogretmen, Konu konu)
	{
		this.ogretmen = ogretmen;
		this.konu = konu;
	}
}
