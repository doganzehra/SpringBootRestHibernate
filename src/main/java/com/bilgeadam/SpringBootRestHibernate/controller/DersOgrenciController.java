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

import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;
import com.bilgeadam.SpringBootRestHibernate.repository.DersOgrenciRepo;
import com.bilgeadam.SpringBootRestHibernate.service.DersOgrenciService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "dersogrenci")
@RestControllerAdvice(basePackageClasses = DersOgrenciRepo.class)
@AllArgsConstructor
public class DersOgrenciController
{
	private DersOgrenciService dersOgrenciService;

	@ExceptionHandler(value = ArithmeticException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "hata oluştu")
	public void myExceptionHandler(Exception ex)
	{
		System.err.println("hata oluştuuuuuuuuuuuu " + ex.getMessage());
	}

	@ExceptionHandler(value = BadSqlGrammarException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "hata oluştu")
	public void myExceptionHandlerAll(Exception ex)
	{
		System.err.println("hata oluştu 2 " + ex.getClass());
	}

	// localhost:8080/dersogrenci/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> getAll()
	{
		return dersOgrenciService.getAll();
	}

	// localhost:8080/dersogrenci/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getById(@PathVariable(value = "id") Long id)
	{
		return dersOgrenciService.getById(id);
	}

	// localhost:8080/dersogrenci/getBy?id=1
	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getBy(@RequestParam(value = "id") Long id)
	{
		return dersOgrenciService.getById(id);
	}

	// localhost:8080/dersogrenci/getByWithHeader
	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getByWithHeader(@RequestHeader(value = "id") Long id)
	{
		return dersOgrenciService.getById(id);
	}

	// localhost:8080/dersogrenci/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody DersOgrenci dersOgrenci)
	{
		// { "ders" : { "id": 1 }, "ogrenci" : { "id": 1 }, "devamsizlik" : "1", "note" : "100" }
		if (dersOgrenciService.save(dersOgrenci))
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi\n http://localhost:8080/dersOgrenci/getById/" + dersOgrenci.getID() + "\n http://localhost:8080/dersOgrenci/deleteById/" + dersOgrenci.getID());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/dersogrenci/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (dersOgrenciService.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
