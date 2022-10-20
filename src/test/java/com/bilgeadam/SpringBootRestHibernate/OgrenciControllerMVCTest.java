package com.bilgeadam.SpringBootRestHibernate;

// controllers belirtmezsem bütün controller 'lar ayağa kalkar
// controlleradvice verdiklerim malesef @component olduğu için ayağa kalkmaya çalışıyor
// @WebMvcTest(controllers = OgrenciController.class)
// junit4 kullanırsanız
// @RunWith(SpringJUnit4ClassRunner.class)
public class OgrenciControllerMVCTest
{
//	@Autowired
//	public MockMvc mock;
//
//	@MockBean
//	// autowire değil taklit
//	public OgrenciService ogrenciService;
//
//	@MockBean
//	// autowire değil taklit
//	public DersService asd;
//
//	@MockBean
//	// autowire değil taklit
//	public DersOgrenciService qwe;
//
//	@MockBean
//	// autowire değil taklit
//	public OgretmenService sdf;
//
//	@MockBean
//	// autowire değil taklit
//	public KonuService xcv;
//
//	@Test
//	public void getByIdTest() throws Exception
//	{
//		// farz et ki veritabanına gitti geldi
//		Ogrenci ogrenci = new Ogrenci(1L, "SALIM", 154L, 2L);
//		// servisin davranışını mock 'ladık
//		Mockito.when(ogrenciService.getById(1L)).thenReturn(ogrenci);
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/ogrenci/getById/1");
//
//		ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.ogr_NUMBER").value("154");
//		ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("SALIM");
//		ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.year").value("3");
//		ResultMatcher okMatcer = MockMvcResultMatchers.status().isOk();
//		// bu şekilde objectMapper ile string 'den pojoya çevirebilirim
//		// mock.perform(request).andReturn().getResponse().getContentAsString();
//		mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(yearMatcher).andExpect(okMatcer);
//	}
}
