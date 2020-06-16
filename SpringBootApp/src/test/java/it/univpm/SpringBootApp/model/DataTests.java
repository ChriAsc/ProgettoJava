package it.univpm.SpringBootApp.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DataTests {

	private Data album = null;
	
	@BeforeEach
	void setUp() throws Exception {
		album = new Data("1234567890", false, 8, null,
				"Un album di prova", "Prova", "", "Loreto, Italy",
				"Prova", null, "friends", "wall", null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1() {
		assertEquals("1234567890", album.getid());
		assertEquals(false, album.getcan_upload());
		assertEquals(8, album.getcount());
		assertEquals(null, album.getcreated_time());
		assertEquals("Un album di prova", album.getdescription());
		assertEquals("Prova", album.getevent());
		assertEquals("", album.getlink());
		assertEquals("Loreto, Italy", album.getlocation());
		assertEquals("Prova", album.getname());
		assertEquals(null, album.getplace());
		assertEquals("friends", album.getprivacy());
		assertEquals("wall", album.gettype());
		assertEquals(null, album.getupdated_time());
	}

	@Test
	void test2() {
		assertAll("valori", ()->assertEquals("1234567890", album.getid()),
				()->assertEquals(false, album.getcan_upload()),
				()->assertEquals(8, album.getcount()),
				()->assertEquals(null, album.getcreated_time()));
	}
	

	@Test
	void test4() {
		assertNotNull(album.toString());
	}
}
