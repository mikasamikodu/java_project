package org.example.mywsdl;

import javax.jws.WebService;

@WebService(endpointInterface="org.example.mywsdl.MyService",
			targetNamespace="http://www.example.org/mywsdl/",
			wsdlLocation="META-INF/wsdl/mywsdl.wsdl")
public class MyServiceImpl implements MyService{

	@Override
	public int add(int a, int b) {
		System.out.println(a+b);
		return a+b;
	}

	@Override
	public int divede(int num1, int num2) {
		System.out.println(num1/num2);
		return num1/num2;
	}

	
}
