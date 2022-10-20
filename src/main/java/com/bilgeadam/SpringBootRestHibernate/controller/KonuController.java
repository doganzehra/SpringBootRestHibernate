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

import com.bilgeadam.SpringBootRestHibernate.model.Konu;
import com.bilgeadam.SpringBootRestHibernate.repository.KonuRepo;
import com.bilgeadam.SpringBootRestHibernate.service.KonuService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "konu")
@RestControllerAdvice(basePackageClasses = KonuRepo.class)
@AllArgsConstructor
public class KonuController
{
	private KonuService konuService;

	private org.springframework.context.MessageSource messageSource;

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

	// localhost:8080/konu/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> getAll()
	{
		return konuService.getAll();
	}

	// localhost:8080/konu/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getById(@PathVariable(value = "id") Long id)
	{
		return konuService.getById(id);
	}

	// localhost:8080/konu/getBy?id=1
	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getBy(@RequestParam(value = "id") Long id)
	{
		return konuService.getById(id);
	}

	// localhost:8080/konu/findByName?name=ma
	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findByName(@RequestParam(value = "name") String name)
	{
		return konuService.findByName(name);
	}

	// localhost:8080/konu/getByWithHeader
	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getByWithHeader(@RequestHeader(value = "id") Long id)
	{
		return konuService.getById(id);
	}

	// localhost:8080/konu/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Konu konu, java.util.Locale locale)
	{
		// {"name":"Java"}
		if (konuService.save(konu))
		{
			String message = messageSource.getMessage("konu.save.success", null, locale);
			return ResponseEntity.status(HttpStatus.CREATED).body(message + "\n http://localhost:8080/konu/getById/" + konu.getID() + "\n http://localhost:8080/konu/deleteById/" + konu.getID());
		}
		else
		{
			String message = messageSource.getMessage("konu.save.fail", null, locale);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
		}
	}

	// localhost:8080/konu/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (konuService.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
