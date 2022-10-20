package com.bilgeadam.SpringBootRestHibernate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;
import com.bilgeadam.SpringBootRestHibernate.repository.OgretmenRepo;
import com.bilgeadam.SpringBootRestHibernate.service.OgretmenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ogretmen")
@RestControllerAdvice(basePackageClasses = OgretmenRepo.class)
@AllArgsConstructor
@Tag(name = "Ogretmen", description = "Öğretmen restful servis endpointleri")
public class OgretmenController
{
	private OgretmenService ogretmenService;

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

	// localhost:8080/ogretmen/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Hepsini getir", description = "ID 'ye göre descending sıralar")
	public List<Ogretmen> getAll(HttpServletRequest req)
	{
		return ogretmenService.getAll();
	}

	// localhost:8080/ogretmen/getById/1
	@GetMapping(value =
	{ "/getById/{id}", "/findById/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getById(@PathVariable(value = "id") Long id)
	{
		return ogretmenService.getById(id);
	}

	// localhost:8080/ogretmen/getBy?id=1
	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getBy(@RequestParam(value = "id") Long id)
	{
		return ogretmenService.getById(id);
	}

	// localhost:8080/ogretmen/findByName?name=Şevval
	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findByName(@RequestParam(value = "name") String name)
	{
		return ogretmenService.findByName(name);
	}

	// localhost:8080/ogretmen/getByWithHeader
	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getByWithHeader(@RequestHeader(value = "id") Long id)
	{
		return ogretmenService.getById(id);
	}

	// localhost:8080/ogretmen/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen)
	{
		// {"name":"Şevval"}
		if (ogretmenService.save(ogretmen))
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi\n http://localhost:8080/ogretmen/getById/" + ogretmen.getID() + "\n http://localhost:8080/ogretmen/deleteById/" + ogretmen.getID());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/ogretmen/deleteById/1
	@io.swagger.v3.oas.annotations.Hidden
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (ogretmenService.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
