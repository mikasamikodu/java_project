package com.itheima.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) throws Exception{
        System.out.println("upload");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> files = upload.parseRequest(request);//解析request,得到内容
        for(FileItem item:files){
            if(item.isFormField()){///判断是不是普通表单项

            }else{
                String name = item.getName();//得到文件名
                item.write(new File( path, name));//实现文件上传
                item.delete();//删除临时文件
            }
        }
        return "success";
    }
    @RequestMapping("/upload2")
    public String upload2(HttpServletRequest request, MultipartFile upload) throws Exception{
        System.out.println("upload2");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String name = upload.getOriginalFilename();//得到文件名
        upload.transferTo(new File(path, name));
        return "success";
    }
    @RequestMapping("/upload3")
    public String upload3(MultipartFile upload) throws Exception{
        System.out.println("upload3");
        String path = "http://localhost:8080/day02_springmvc_fileupload/";

        String name = upload.getOriginalFilename();//得到文件名
		Client client = Client.create();
		WebResource webResource = client.resource(path + name);
		webResource.put(upload.getBytes());
        return "success";
    }
}
