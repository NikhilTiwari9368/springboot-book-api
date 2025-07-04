package com.bootapi.restapi.helper;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.InputStream;
import java.nio.file.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

  public final String UPLOAD_DIR;

  public FileUploadHelper() throws Exception {
    // Resolve "static/images" from classpath
    File resourceFolder = new ClassPathResource("static/images").getFile();
    this.UPLOAD_DIR = resourceFolder.getAbsolutePath();
  }

  public boolean uploadFile(MultipartFile multipartFile) {
    boolean f = false;
    try {
      Files.createDirectories(Paths.get(UPLOAD_DIR));

      String filename = Paths.get(multipartFile.getOriginalFilename()).getFileName().toString();
      Path target = Paths.get(UPLOAD_DIR, filename);

      InputStream in = multipartFile.getInputStream();
      Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
      f = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return f;
  }
 
  public String getUploadDir() {
    return UPLOAD_DIR;
  }
}
