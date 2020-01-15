package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.config.UploadResponse;
import com.robin.reactmarket.customException.FileEmptyException;
import com.robin.reactmarket.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;
    @Value("${upload.file-url}")
    private String BASE_URL;

    @PostMapping("/upload")
    public Response uploadImg(MultipartFile file){
        try {
            String fileName = fileService.uploadImg(file);
            String url = BASE_URL+fileName;
            UploadResponse data = new UploadResponse(fileName,url);
            return new Response("200","图片上传成功",data);
        }catch (FileEmptyException e){
            return new Response("400","图片上传失败, 您还未选择图片!",e.getMessage());
        }catch (Exception e){
            return new Response("400","图片上传失败",e.toString());
        }

    }
}
