package it.univpm.SpringBootApp.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.SpringBootApp.exceptions.InvalidFieldException;

/**
 * Classe di test al metodo getStatistics di StatBase nel caso in cui venga passato un campo vuoto (?field=)
 * oppure un campo sbagliato
 * @author Cristian Cingolani & Christian Ascani
 *
 */
class StatBaseTest {
	
	private String s;
	
	@BeforeEach
	void setUp() throws Exception {
		s = "prova";
	}
	
	@Test
	void testGetStatistics() throws InvalidFieldException {
		assertThrows(InvalidFieldException.class, ()->StatBase.getStatistics(""));
	}
	
	@Test
	void testGetStatistics2() throws InvalidFieldException {
		assertThrows(InvalidFieldException.class, ()->StatBase.getStatistics(s));
	}
}
