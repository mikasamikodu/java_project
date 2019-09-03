package com.nantian.jax;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Test {
	
	@org.junit.Test
	public void main() {
		try {
			JAXBContext jax = JAXBContext.newInstance(Student.class);
			Marshaller mar = jax.createMarshaller();
			Student student = new Student("1","zhangsan",new Classroom("1","java","2001"));
			mar.marshal(student, System.out);
			System.out.println();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void tets() {
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><id>1</id><name>zhangsan</name><room><grade>2001</grade><id>1</id><name>java</name></room></student>";
			JAXBContext jas = JAXBContext.newInstance(Student.class);
			Unmarshaller unmar = jas.createUnmarshaller();
			Student student = (Student)unmar.unmarshal(new StringReader(xml));
			System.out.println(student.getName()+"��"+student.getRoom().getName()+"��");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
