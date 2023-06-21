package fr.fms;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.fms.business.IbusinessImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class ParcCinemaControllerTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	IbusinessImpl business;
	 @Test
	    void test_get_welcome() throws Exception {
	    	when(business.great()).thenReturn("Hello, Mock");
	    	
	    	this.mvc.perform(get("/greating"))
	    		.andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content()
	            .string(containsString("Hello, Mock")));
	    }
}
