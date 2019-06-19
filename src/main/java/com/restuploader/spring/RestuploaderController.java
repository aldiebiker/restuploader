package com.restuploader.spring;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author senthil
 * @param <T>
 *
 */

@RestController
@RequestMapping("/")
public class RestuploaderController<T> {

	private Logger logger = LoggerFactory.getLogger(RestuploaderController.class);

	@Autowired
	FileUploaderService fileUploaderService;

	@PostMapping("/files")
	public ResponseEntity<T> uploadFile(@RequestParam("fileName") MultipartFile file) {
		try {
			callService(file, file.getOriginalFilename());
		} catch (IOException | IllegalArgumentException | IllegalStateException e) {
			logger.info(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(HttpStatus.OK);
	}

	private void callService(MultipartFile part, String fileName) throws IOException {
		try (InputStream inStream = part.getInputStream()) {
			fileUploaderService.save(inStream, fileName);
		}
	}

}

