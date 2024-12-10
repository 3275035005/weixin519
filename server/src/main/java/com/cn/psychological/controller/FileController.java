package com.cn.psychological.controller;
import com.cn.psychological.service.FileService;
import com.cn.psychological.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件上传和下载入口
 */
@RestController
@RequestMapping("/files")
public class FileController {


    @Value("${file.servicePath}")
    private String baseUrl;

    @Autowired
    private FileService fileService;
    /**
     * 图片上传
     * @param
     * @return
     */
    @PostMapping("/file")
    public R uploadFile(HttpServletRequest request, MultipartFile file) {
        String uuid= System.currentTimeMillis()+".";
        String originalFilename = file.getOriginalFilename();

        String fileName= uuid + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        try {
            if (fileService.uploadFile(file,fileName)) {
                return R.ok().data("row",baseUrl+"/download?fileName="+fileName);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return R.error();
    }

    /**
     * 文件下载
     * @param imageName
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void getImage(@RequestParam("fileName") String imageName,
                         HttpServletResponse response) throws IOException {
        File fileDir = new File(System.getProperty("user.dir")+"\\image");
        File image=new File(fileDir.getAbsolutePath() +"/"+imageName);
        if (image.exists()){
            FileInputStream fileInputStream=new FileInputStream(image);
            byte[] bytes=new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes)>0){
                OutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }

}
