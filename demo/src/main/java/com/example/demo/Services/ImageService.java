package com.example.demo.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class ImageService {
    private static final String UPLOADED_FOLDER = Paths.get("demo/src/main/resources/static/Images/").toAbsolutePath().toString() + "/";

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        // Create the directory if it doesn't exist
        File uploadDir = new File(UPLOADED_FOLDER);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Get the original filename
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IOException("Failed to store file with no name.");
        }

        // Define the full path where the file will be stored
        String filePath = UPLOADED_FOLDER + originalFilename;
        Path path = Paths.get(filePath);

        // Save the file
        file.transferTo(path.toFile());

        // Return the relative path for saving in the database
        return  originalFilename;
    }
}

