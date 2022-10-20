package com.bilgeadam.SpringBootRestHibernate;

// DEFINED_PORT application properties den geliyor
// @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
// testleri sıralı çalıştır ve annotation 'ları dikkate al
// @org.junit.jupiter.api.TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
// @DisplayName(value = "Öğrenci web servis testleri")
public class OgrenciControllerTest
{
//	@Autowired
//	// autowired edilmesi için webEnvironment = WebEnvironment.DEFINED_PORT olacak
//	public TestRestTemplate restTemplate;
//
//	@Test
//	@org.junit.jupiter.api.Order(value = 3)
//	public void findByName()
//	{
//		Ogrenci[] ogrenci = restTemplate.getForEntity("http://localhost:8080/ogrenci/findByName?name=SALIM", Ogrenci[].class).getBody();
//		Ogrenci expected = new Ogrenci(1L, "SALIM", 154L, 4L);
//		org.junit.jupiter.api.Assertions.assertEquals(expected, ogrenci[0]);
//	}
//
//	@Test
//	@org.junit.jupiter.api.Order(value = 2)
//	@DisplayName(value = "getByID testi")
//	public void testGetById()
//	{
//		Ogrenci ogrenci = restTemplate.getForEntity("http://localhost:8080/ogrenci/getById/1", Ogrenci.class).getBody();
//		Ogrenci expected = new Ogrenci(1L, "SALIM", 154L, 4L);
//		org.junit.jupiter.api.Assertions.assertEquals(expected, ogrenci);
//	}
//
//	@Test
//	public void save()
//	{
//		HttpEntity<Ogrenci> body = new HttpEntity<Ogrenci>(new Ogrenci("Hasan", 555L, 2L));
//		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ogrenci/save", HttpMethod.POST, body, String.class);
//		// if (response.getStatusCode() != HttpStatus.CREATED)
//		// {
//		// throw new Exception("Olmadı");
//		// }
//		Assertions.assertTrue(response.getStatusCode() == HttpStatus.CREATED);
//	}
//
////	@Test
////	public void delete()
////	{
////		HttpEntity<Ogrenci> body = new HttpEntity<Ogrenci>(new Ogrenci("Hasan", 555L, 2L));
////		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ogrenci/save", HttpMethod.POST, body, String.class);
////		// if (response.getStatusCode() != HttpStatus.CREATED)
////		// {
////		// throw new Exception("Olmadı");
////		// }
////		Assertions.assertTrue(response.getStatusCode() == HttpStatus.CREATED);
////	}
}