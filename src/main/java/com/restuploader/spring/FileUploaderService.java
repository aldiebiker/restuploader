package com.restuploader.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 
 * @author senthil
 *
 */
@Service
public class FileUploaderService {

	@Value("${destination.path}")
	private String PATH_DESTINATION;

	public void save(InputStream aInputStream, String aFileName) throws IOException {
		// Do some sanity checks
		Assert.isTrue(Files.isDirectory(Paths.get(PATH_DESTINATION)), "Not a directory." + PATH_DESTINATION);
		Assert.isTrue(Files.isWritable(Paths.get(PATH_DESTINATION)), "Not a writable directory." + PATH_DESTINATION);
		Path filePath = Paths.get(PATH_DESTINATION, aFileName);
		Files.copy(aInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	}

}

