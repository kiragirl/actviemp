/**
 * @author:liyiming
 * @date:2018年1月26日
 * Description:
 **/
package com.liyiming.test.activemq;

import java.io.Serializable;

/**
 * Title: T Description: Company:pusense
 * 
 * @author ：lyiming
 * @date ：2018年1月26日
 **/
public class TestBean implements Serializable{

	private static final long serialVersionUID = -3495452078933738641L;
	private int age;
	private String name;

	public TestBean() {
	};

	public TestBean(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
