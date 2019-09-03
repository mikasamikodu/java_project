package com.dao;

import java.util.List;

import com.entity.Sa_Opperson_Oporg;

public interface EmpDeptDao {
	String importName(List<Object> obj);
	 String findSoo(List<Object> objlist);
	 String validationOgn(List<Object> objlist);
	String validationOgnImport(Sa_Opperson_Oporg soo);
}
