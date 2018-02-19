package com.halfmoon.cloudmanager.util.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class PhotoFileUtil extends FileUtil {

	@Override
	public void compressAndSave(MultipartFile file, String path, int width, int height) throws IOException {
		
		Image oldIcon = ImageIO.read(file.getInputStream());
		int originalWidth = oldIcon.getWidth(null);
		int originalHeight = oldIcon.getHeight(null);
		int compressedWidth = originalWidth;
		int compressedHeight = originalHeight;
		
		double compressRate = 1;	// 压缩率

		// 图片宽高应不超过规定大小，否则做等比压缩处理
		if(originalWidth > width || originalHeight > height) {
			
			if(originalHeight / originalWidth < height * 1.0 / width) {
				compressRate = height * 1.0 / originalHeight;
			}
			else {
				compressRate = width * 1.0 / originalWidth;
			}
			
			compressedWidth = (int)(originalWidth * compressRate);
			compressedHeight = (int)(originalHeight * compressRate);
			
		}
		
		
		BufferedImage newIcon = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		newIcon.getGraphics().drawImage(oldIcon.getScaledInstance(compressedWidth, compressedHeight, Image.SCALE_SMOOTH), 0, 0, null);
		
		ImageIO.write(newIcon, "jpg", new File(ROOT_DIR + path));
		
	}
}
