package com.bilgeadam.SpringBootRestHibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
// test case 'lerinde assert equals yapabilmek için gerekli
@EqualsAndHashCode
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "OGR_NUMBER", name = "unique_ogr_number"))
public class Ogrenci
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(length = 50)
	private String NAME;

	@Column(nullable = false)
	private Long OGR_NUMBER;

	@Column(nullable = false, columnDefinition = "bigint default 1")
	private Long YEAR;

	public Ogrenci(String nAME, Long oGR_NUMBER, Long yEAR)
	{
		NAME = nAME;
		OGR_NUMBER = oGR_NUMBER;
		YEAR = yEAR;
	}
}
