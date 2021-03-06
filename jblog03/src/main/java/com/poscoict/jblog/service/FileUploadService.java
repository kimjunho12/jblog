package com.poscoict.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.exception.FileUploadException;

@Service
public class FileUploadService {

	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";

	public String restore(MultipartFile multipartFile) {
		String url = null;

		try {
			if (multipartFile.isEmpty()) {
				return url;
			}

			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.indexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);

			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(data);
			os.close();

			url = URL_BASE + "/" + saveFileName;

		} catch (IOException e) {
			throw new FileUploadException("file upload error : " + e);
		}

		return url;
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

}
