package com.bootapi.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bootapi.restapi.helper.FileUploadHelper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileUploadController {

  @Autowired
  private FileUploadHelper fileUploadHelper;

  @PostMapping("/upload-file")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

  try {
    if (file.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
    }

    if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
          .body("Only JPEG or PNG image files are allowed");
    }

    boolean isUploaded = fileUploadHelper.uploadFile(file);

    if (isUploaded) {
      // Build download URL
      String filename = file.getOriginalFilename();
      String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
      String fileUrl = baseUrl + "/images/" + filename;

      return ResponseEntity.ok("File uploaded: " + fileUrl);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("File upload failed");
    }

  } catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Something went wrong: " + e.getMessage());
  }   
}
}