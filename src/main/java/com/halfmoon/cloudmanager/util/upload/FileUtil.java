package com.halfmoon.cloudmanager.util.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileUtil {

	public static final String ROOT_DIR = "/data/image";

	public String makePath(MultipartFile file, String prefix) {

		StringBuffer path = new StringBuffer();
		int hashCode = file.getOriginalFilename().hashCode();

		path.append('/').append(prefix).append('/').append(String.valueOf(Math.abs(hashCode / 10000))).append('/')
				.append(String.valueOf(Math.abs(hashCode % 10000))).append('/');

		String dir = ROOT_DIR + path.toString();
		File dir_file = new File(dir);
		if (!dir_file.exists()) {
			dir_file.mkdirs();
		}

		return path.toString();

	}

	public String generateFileName(MultipartFile file) {

		return UUID.randomUUID().toString().concat(".jpg");

	}
	
	public abstract void compressAndSave(MultipartFile file, String path, int width, int height) throws IOException;

}
