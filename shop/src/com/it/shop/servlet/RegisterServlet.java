package com.it.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.it.shop.domain.User;
import com.it.shop.exception.UserException;
import com.it.shop.service.UserService;
import com.it.shop.util.UUIDUtil;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//鑾峰彇琛ㄥ崟鏁版嵁
		response.setHeader("Access-Control-Allow-Origin", "*");  
        /* 鏄熷彿琛ㄧず鎵�鏈夌殑寮傚煙璇锋眰閮藉彲浠ユ帴鍙楋紝 */  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		User user = new User();
		PrintWriter out = new PrintWriter(response.getOutputStream());
		try {
			BeanUtils.populate(user, request.getParameterMap());//浣跨敤beanutils鐨刾opulate鏂规硶灏唌ap涓暟鎹皝瑁呭埌瀵硅薄涓�
			user.setId(UUIDUtil.getUUID());
			//澶勭悊涓氬姟閫昏緫
			System.out.println(user.getUaccount()+"--"+user.getUpassword());
			UserService service = new UserService();
			service.regist(user);
			request.getSession().setAttribute("user", user);
			out.print(1);
			out.flush();
		}catch (UserException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (UserException e) {
//			e.printStackTrace();
//			out.print(e.getMessage());
//			out.flush();
//		}
//		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}

}
