package com.example.demo.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageService
{
    private static String UPLOADED_FOLDER = "C://temp//"; // Change this to your desired upload directory

    public String uploadImage(MultipartFile file) 
    {
        if (file.isEmpty()) 
        {
            return "Please select a file to upload.";
        }

        try 
        {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return "File uploaded successfully: " + file.getOriginalFilename();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }
    
}
