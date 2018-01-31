/**
* @author:liyiming
* @date:2018年1月31日
* Description:
**/
package com.liyiming.test.activemq.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 	Title: SimpleJMSReceiver2
 *	Description:
 *	Company:pusense
 * 	@author ：lyiming
 * 	@date ：2018年1月31日
 **/
public class SimpleJMSReceiver2{
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/ApplicationContext3C2.xml");
		while (true) {
		}
	}
}
