package com.nantian.dao;

import javax.jws.WebService;
import javax.ws.rs.GET;
//import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nantian.entity.Student;

@WebService
//@Path("/student")
public interface Data {
	@GET
	@Produces(MediaType.APPLICATION_XML)
//	@Path("/add/{student}")
	public int add(Student student);
	@GET
	@Produces(MediaType.APPLICATION_XML)
//	@Path("/delete/{id}")
	public int delete(String id);
	@GET
	@Produces(MediaType.APPLICATION_XML)
//	@Path("/select1/{id}")
	public Student select1(String id);
	@GET
	@Produces(MediaType.APPLICATION_XML)
//	@Path("/select2/{name}")
	public Student select2(String name);
}
