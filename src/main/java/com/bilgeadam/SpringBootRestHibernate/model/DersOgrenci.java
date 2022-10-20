package com.bilgeadam.SpringBootRestHibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Check;
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
@Check(constraints = "NOTE < 101")
public class DersOgrenci
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "DERS_FK"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ders ders;

	@ManyToOne
	// @ForeignKey ile foreign key 'lere isim verdim
	// joincolumn ile nullable gibi özellikler de set edilebilir
	@JoinColumn(foreignKey = @ForeignKey(name = "OGRENCI_FK"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ogrenci ogrenci;

	@Column(nullable = false)
	private Integer DEVAMSIZLIK;

	@Column(nullable = false)
	private Integer NOTE;

	public DersOgrenci(Ders ders, Ogrenci ogrenci, Integer dEVAMSIZLIK, Integer nOTE)
	{
		this.ders = ders;
		this.ogrenci = ogrenci;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOTE = nOTE;
	}
}
