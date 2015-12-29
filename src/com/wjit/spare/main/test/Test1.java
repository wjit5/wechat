package com.wjit.spare.main.test;

public class Test1 {
	static void f(float f,Character... args){
		System.out.println("float");
	} 
	static void f(char c,Character... args){
		System.out.println("char");
	}
	public static void main(String[] args){
		f(1,'a');
		f('a','b');
		Object[] obj=new Object[]{"1","2","3"};
		System.out.println((obj[1].toString()));
		Integer i=13;
		
		System.out.println(i.toString(i));
		
	}
	
}
