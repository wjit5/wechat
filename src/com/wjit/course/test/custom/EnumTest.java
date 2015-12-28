package com.wjit.course.test.custom;

import org.apache.log4j.chainsaw.Main;


public enum EnumTest {
	Green("��ɫ",1),Yellow("��ɫ",2),Red("��ɫ",3);
	//��Ա����
	private String name;
	private int index;
	
	//���췽��
	private EnumTest(String name,int index){
		this.name=name;
		this.index=index;
	}
	
	//��ͨ����
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
