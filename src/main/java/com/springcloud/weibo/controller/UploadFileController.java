package com.springcloud.weibo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.weibo.service.UploadFileService;

@Controller
@RequestMapping("/private")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/upload.do", method = {RequestMethod.POST})
    public void uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
                           HttpServletResponse response) {
        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
        System.out.println(filename);

        String path = request.getRealPath("/WEB-INF/uploadFile");
        File temp = new File(path, filename);
        int len;
        byte[] buffer = new byte[1024];

        try {
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(temp);
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            response.getWriter().print("success");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().print("error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/batchUpload.do", method = {RequestMethod.POST})
    public void batchUploadFile(@RequestParam("file") CommonsMultipartFile[] files,
                                HttpServletResponse response) {
        JSONObject data = uploadFileService.batchUploadFile(files);
        try {
            response.getWriter().write(data.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
