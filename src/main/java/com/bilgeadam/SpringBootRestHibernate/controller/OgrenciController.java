package com.bilgeadam.SpringBootRestHibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;
import com.bilgeadam.SpringBootRestHibernate.repository.OgrenciRepo;
import com.bilgeadam.SpringBootRestHibernate.service.OgrenciService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ogrenci")
@RestControllerAdvice(basePackageClasses = OgrenciRepo.class)
@AllArgsConstructor
public class OgrenciController
{
	private OgrenciService ogrenciService;

	@ExceptionHandler(value = ArithmeticException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "hata oluştu")
	public void myExceptionHandler(Exception ex)
	{
		System.err.println("hata oluştuuuuuuuuuuuu " + ex.getMessage());
		// return ResponseEntity.internalServerError().body("hata oluştu");
	}

	@ExceptionHandler(value = BadSqlGrammarException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "hata oluştu")
	public void myExceptionHandlerAll(Exception ex)
	{
		System.err.println("hata oluştu 2 " + ex.getClass());
		// return ResponseEntity.internalServerError().body("hata oluştu");
	}

	// localhost:8080/ogrenci/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> getAll()
	{
		return ogrenciService.getAll();
	}

	// localhost:8080/ogrenci/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getById(@PathVariable(value = "id") Long id)
	{
		return ogrenciService.getById(id);
	}

	// localhost:8080/ogrenci/getBy?id=1
	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getBy(@RequestParam(value = "id") Long id)
	{
		return ogrenciService.getById(id);
	}

	// localhost:8080/ogrenci/findByName?name=ma
	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findByName(@RequestParam(value = "name") String name)
	{
		return ogrenciService.findByName(name);
	}

	// localhost:8080/ogrenci/getByWithHeader
	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getByWithHeader(@RequestHeader(value = "id") Long id)
	{
		return ogrenciService.getById(id);
	}

	// localhost:8080/ogrenci/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogrenci ogrenci)
	{
		// {"name" : "SALIH", "ogr_NUMBER" : 154, "year" : 4}
		if (ogrenciService.save(ogrenci))
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi\n http://localhost:8080/ogrenci/getById/" + ogrenci.getID() + "\n http://localhost:8080/ogrenci/deleteById/" + ogrenci.getID());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/ogrenci/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (ogrenciService.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
