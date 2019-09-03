package com.it.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.it.shop.domain.Commodity;
import com.it.shop.service.CommodityService;

public class FindAllCommoditysServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        
        CommodityService service = new CommodityService();
        List<Commodity> list = new ArrayList<Commodity>();
		PrintWriter out = new PrintWriter(response.getOutputStream());
		list = service.findAllCommoditys();
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		out.print(json);
		out.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
