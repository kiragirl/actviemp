/**
 * @author:liyiming
 * @date:2018年1月30日
 * Description:
 **/
package com.liyiming.test.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * Title: A Description: Company:pusense
 * spring+activemq的同步消息接收
 * @author ：lyiming
 * @date ：2018年1月30日
 **/
public class QueueConsumer{

	private static JmsTemplate jt = null;

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
		// 获取JmsTemplate对象
		jt = (JmsTemplate) ctx.getBean("jmsTemplate");
		// 接收消息
		String msg = (String) jt.receiveAndConvert();
		System.out.println("receive msg = " + msg);
	}
}
