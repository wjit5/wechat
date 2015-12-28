package com.wjit.course.test.custom;

import org.apache.log4j.chainsaw.Main;


public enum EnumTest {
	Green("绿色",1),Yellow("黄色",2),Red("红色",3);
	//成员变量
	private String name;
	private int index;
	
	//构造方法
	private EnumTest(String name,int index){
		this.name=name;
		this.index=index;
	}
	
	//普通方法
	public static String getName(int index){
		for(EnumTest t: EnumTest.values()){
			if(t.getIndex()==index){
				return t.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static void main(String[] args) {
		int i=1;
		System.out.println("i:"+i);
//		System.out.println("i++:"+ i++);
//		System.out.println("++i:"+ ++i);
//		System.out.println("i--:"+ i--);
//		System.out.println("--i:"+ --i);
		System.out.println(getName(2));
		
	}
	
}
