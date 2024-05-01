package com.example.demo.models;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class ImageUploadService {

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    public String uploadImage(MultipartFile file, String filename) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try {
            // Save the file to the server's file system
            String filePath = UPLOAD_DIR + filename + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return "File uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file.";
        }
    }
}
