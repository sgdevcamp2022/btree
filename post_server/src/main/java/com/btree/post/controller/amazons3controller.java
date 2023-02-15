package com.btree.post.controller;

import com.btree.post.service.imageserviceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class amazons3controller {

    private final imageserviceimpl imageservice;

    @PostMapping(value = "/upload",consumes = "multipart/*")
    public ResponseEntity<List<String>> uploadimage(@RequestPart("files") List<MultipartFile> multipartFile){
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageservice.uploadFile(multipartFile));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteimage(@RequestParam String fileName){
        imageservice.deleteFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadimage(String fileurl) throws IOException{
        String filepath =fileurl.substring(52);
        return imageservice.download(filepath);
    }
}
