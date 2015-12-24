package com.wjit.course.test;

import java.util.Date;
import java.util.Random;

public class RandomTest {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand=new Random(new Random().nextInt(1000));
		int i=new Random().nextInt(20);
		int j=rand.nextInt(10);
		System.out.println(i+"。。。。。"+j);
		
	}

}
