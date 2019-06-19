package com.restuploader.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RestuploaderControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FileUploaderService fileUploaderService;

	private MockMultipartFile multipartFile;

	@Before
	public void setUp() {
		multipartFile = new MockMultipartFile("fileName", "test.txt", "text/plain", "Spring Framework".getBytes());
	}

	@Test
	public void returnSuccessOnHappyPath() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.multipart("/files/").file(multipartFile)).andExpect(status().is(200));

	}

	@Test
	public void returnFailureOnInvalidPath() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.multipart("/files/upload").file(multipartFile))
				.andExpect(status().is(404));
	}

	@Test
	public void returnFailureOnInvalidParamName() throws Exception {
		MockMultipartFile multipartWrongFileName = new MockMultipartFile("file", "test.txt", "text/plain",
				"Spring Framework".getBytes());
		this.mvc.perform(MockMvcRequestBuilders.multipart("/files/").file(multipartWrongFileName))
				.andExpect(status().is(400));
	}

}