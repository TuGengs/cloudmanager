package com.halfmoon.cloudmanager.util.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class IconFileUtil extends FileUtil {
	
	@Override
	public void compressAndSave(MultipartFile file, String path, int width, int height) throws IOException {
		
		Image oldIcon = ImageIO.read(file.getInputStream());
		
		BufferedImage newIcon = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		newIcon.getGraphics().drawImage(oldIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		ImageIO.write(newIcon, "jpg", new File(ROOT_DIR + path));
		
	}
	
}
