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

import com.bilgeadam.SpringBootRestHibernate.model.Ders;
import com.bilgeadam.SpringBootRestHibernate.repository.DersRepo;
import com.bilgeadam.SpringBootRestHibernate.service.DersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ders")
@RestControllerAdvice(basePackageClasses = DersRepo.class)
@AllArgsConstructor
public class DersController
{
	private DersService dersService;

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

	// localhost:8080/ders/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getAll()
	{
		return dersService.getAll();
	}

	// localhost:8080/ders/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getById(@PathVariable(value = "id") Long id)
	{
		return dersService.getById(id);
	}

	// localhost:8080/ders/getBy?id=1
	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getBy(@RequestParam(value = "id") Long id)
	{
		return dersService.getById(id);
	}

	// localhost:8080/ders/getByOgrNameAndKonuName?name=Şevval&konu=Java
	@GetMapping(value = "/getByOgrNameAndKonuName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getByOgrNameAndKonuName(@RequestParam(value = "name") String name, @RequestParam(value = "konu") String konu)
	{
		return dersService.getDerslerByOgretmenNameAndKonuName(name, konu);
	}

	// localhost:8080/ders/getByOgrName?name=Şevval
	@GetMapping(value = "/getByOgrName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getBy(@RequestParam(value = "name") String name)
	{
		return dersService.getDerslerByOgretmenName(name);
	}

	// localhost:8080/ders/getByWithHeader
	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getByWithHeader(@RequestHeader(value = "id") Long id)
	{
		return dersService.getById(id);
	}

	// localhost:8080/ders/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ders ders)
	{
		// { "ogretmen" : { "id" : "1" } , "konu" : { "id" : "1" } }
		if (dersService.save(ders))
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi\n http://localhost:8080/ders/getById/" + ders.getID() + "\n http://localhost:8080/ders/deleteById/" + ders.getID());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/ders/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (dersService.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
