package com.btree.post.controller;

import com.btree.post.dto.ImageResponseDto;
import com.btree.post.service.imageserviceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/api/s3")
public class amazons3controller {

    private final imageserviceimpl imageservice;

    @PostMapping(value = "/upload",consumes = "multipart/*")
    public ResponseEntity<ImageResponseDto> uploadimage(@RequestPart(value = "files") MultipartFile multipartFile) throws IOException {
        System.out.println("test");
        String fileName= imageservice.uploadFile(multipartFile);
        ImageResponseDto imageResponseDto = new ImageResponseDto(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteimage(@RequestParam String fileName){
        imageservice.deleteFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadimage(@RequestParam String fileurl) throws IOException{
        System.out.println("test : " + fileurl);
        return imageservice.download(fileurl);
    }
}
