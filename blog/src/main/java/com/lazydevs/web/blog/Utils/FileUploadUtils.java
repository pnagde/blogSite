package com.lazydevs.web.blog.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

    public static String saveFile(String fileName,
    MultipartFile multipartFile) throws IOException  {

        Path path=Paths.get("/resources/");
        if(!Files.notExists(path)){
            Files.createDirectories(path);
        }
        try {
            Files.copy(multipartFile.getInputStream(), path.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        } 
        return path.toString()+fileName;
    }
    
}
