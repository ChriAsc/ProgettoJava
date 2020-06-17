package it.univpm.SpringBootApp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.univpm.SpringBootApp.exceptions.InvalidFieldException;
import it.univpm.SpringBootApp.utils.StatBase;

/**
 * Classe di test al MainController con metodi relativi
 * @author Cristian Cingolani & Christian Ascani
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

	 @Autowired
	 private MockMvc mvc;

	 @MockBean
	 private MainController mainController;
	
	 @BeforeEach
	 void setUp() throws Exception {
		 mainController = new MainController();
	 }
	
	 @AfterEach
	 void tearDown() throws Exception {
	 }
	
	 @Test
	 void testGetMetaData() throws Exception {
		 RequestBuilder request = MockMvcRequestBuilders.get("/metadata");
		 MvcResult result = mvc.perform(request).andReturn();
		 assertEquals("[]", result.getResponse().getContentAsString());
	 }
	
	 @Test
	 void testGetData() throws Exception {
		 RequestBuilder request = MockMvcRequestBuilders.get("/data");
		 MvcResult result = mvc.perform(request).andReturn();
		 assertEquals("[]", result.getResponse().getContentAsString());
	 }
	 
	 @Test
	 void testGetStatistics() throws Exception {
		 assertThrows(InvalidFieldException.class, ()->mainController.getStatistics(""));
	 }
	 
	 @Test
	 void testGetStatisticsPlaceorLocation() throws Exception {
		 assertThrows(InvalidFieldException.class, ()->mainController.getStatistics("place"));
		 assertThrows(InvalidFieldException.class, ()->mainController.getStatistics("location_place"));
	 }

	 @Test
	 void testFiltering() throws Exception {
		 assertThrows(InvalidFieldException.class, ()->mainController.filtering(""));
	 }
	 
}
