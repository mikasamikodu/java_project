package com.nantian.test;

import java.util.ArrayList;
import java.util.Iterator;

public class Test1 {

	public static void main(String[] args) {
		ArrayList<String> array = new ArrayList<String>();
		array.add("h");
		array.add("e");
		array.add("l");
		array.add("l");
		array.add("o");
		Iterator<String> iterator = array.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println();
	}

}
