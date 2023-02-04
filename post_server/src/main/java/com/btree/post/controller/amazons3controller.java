package com.btree.post.controller;

import com.btree.post.service.imageserviceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class amazons3controller {

    private final imageserviceimpl imageservice;

    @PostMapping("/image")
    public ResponseEntity<List<String>> uploadimage(@RequestPart List<MultipartFile> multipartFile){
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageservice.uploadFile(multipartFile));
    }

    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteimage(@RequestParam String fileName){
        imageservice.deleteFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}
