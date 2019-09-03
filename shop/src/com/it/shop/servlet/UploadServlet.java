package com.it.shop.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean flag = ServletFileUpload.isMultipartContent(request);
		if(!flag) {
			throw new RuntimeException("你的form表单类型不是multipart/form-data!");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()) {
					processFormField(fileItem);
				}else {
					processUploadField(fileItem);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}

	private void processUploadField(FileItem fileItem) {
		String filename = fileItem.getName();
		try {
			InputStream is = fileItem.getInputStream();
			
			String realPath = this.getServletContext().getRealPath("/upload");
			File derectory = new File(realPath);
			
			if(!derectory.exists()) {
				derectory.mkdirs();
			}
			File file = new File(derectory, filename);
			FileOutputStream os = new FileOutputStream(file);
			
			int len = 0;
			byte[] b = new byte[512];
			while((len=is.read(b))!=-1) {
				os.write(b, 0, len);
				System.out.println(b.toString());
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processFormField(FileItem fileItem) {
		String name = fileItem.getFieldName();
		String value = fileItem.getString();
		System.out.println(name+"="+value);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
