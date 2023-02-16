package com.btree.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class imageserviceimpl implements imageservice{
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public List<String> uploadFile(List<MultipartFile> multipartFile)throws IOException{
        List<String> fileNameList = new ArrayList<>();

        multipartFile.forEach(file->{
            String fileName= createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream=file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket,fileName,inputStream,objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"파일 업로드에 실패했습니다.");
            }

            fileNameList.add(fileName);
        });

        return fileNameList;
    }

    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket,fileName));
    }

    private String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName){
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"잘못된 형식의 파일("+fileName+"입니다.");
        }
    }

    public ResponseEntity<byte[]> download(String fileurl) throws IOException{
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket,fileurl));
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(contentType(fileurl));
        httpHeaders.setContentLength(bytes.length);
        String[] arr=fileurl.split("/");
        String type=arr[arr.length-1];
        String fileName= URLEncoder.encode(type,"UTF-8").replaceAll("\\+","%20");
        httpHeaders.setContentDispositionFormData("attachment",fileName);

        return new ResponseEntity<>(bytes,httpHeaders,HttpStatus.OK);

    }

    private MediaType contentType(String keyname){
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length-1];
        switch (type){
            case"txt":
                return MediaType.TEXT_PLAIN;
            case"png":
                return MediaType.IMAGE_PNG;
            case"jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
