package com.test;

public class Test1 {

	public static void main(String[] args) {
		int[] num1 = new int[] {1, 3};
		int[] num2 = new int[] {2, 4};
		Test2 test2 = new Test2();
		double result = test2.findMedianSortedArrays2(num1, num2);
		System.out.println(result);
	}

}
/*
2
4
8
262144
4
8
16
524288

 */