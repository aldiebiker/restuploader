package com.restuploader.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RestuploaderControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FileUploaderService fileUploaderService;
	
	MockMultipartFile multipartFile;
	
	@Before
	public void setUp() {
		multipartFile = new MockMultipartFile("fileName", "test.txt",
                "text/plain", "Spring Framework".getBytes());
	}
	
	@Test
	public void returnSuccessOnHappyPath() throws Exception {
        this.mvc.perform(fileUpload("/files/").file(multipartFile))
                .andExpect(status().is2xxSuccessful());

	}
	

	@Test
	public void returnFailureOnInvalidPath() throws Exception {
        this.mvc.perform(fileUpload("/files/upload").file(multipartFile))
                .andExpect(status().is4xxClientError());

	}
	
	@Test
	public void returnFailureOnInvalidParamName() throws Exception {
        this.mvc.perform(fileUpload("/files/upload").file(multipartFile))
                .andExpect(status().is4xxClientError());

	}
}