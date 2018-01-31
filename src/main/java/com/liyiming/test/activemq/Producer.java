/**
 * @author:liyiming
 * @date:2018年1月26日
 * Description:
 **/
package com.liyiming.test.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: PR Description: Company:pusense
 * 
 * @author ：lyiming
 * @date ：2018年1月26日
 **/
public class Producer{

	public static void main(String[] args) {
		int i = 0;
		// 链接工厂
		ActiveMQConnectionFactory connectionFactory = null;
		// 链接对象
		Connection connection = null;
		// 会话
		Session session = null;
		// 队列（目的地、生产者发送消息的目的地）
		Queue queue = null;
		// 消息生产者
		MessageProducer producer = null;
		connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			// 第一个参数是否开启事务 true开启 ,false不开启事务，如果开启记得手动提交
			// 参数二，表示的是签收模式，一般使用的有自动签收和客户端自己确认签收
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue("test_queue");
			// 为队列创建消息生产者
			producer = session.createProducer(queue);
			// 消息是否为持久性的，这个不设置也是可以的，默认是持久的
			// producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			// //消息设置为持久的发送后及时服务关闭了再次开启消息也不会丢失。
			// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// //发送后如果服务关闭再次开启则消息会丢失。
			while (true) {
				// 创建消息
				TextMessage message = session.createTextMessage();
				message.setText("测试队列消息" + i);
				// 发送消息到目的地
				//message发送的消息，deliveryMode是否持久化，priority优先级，timeToLive 消息过期时间  
				//producer.send(message, deliveryMode, priority, timeToLive); 
				producer.send(message);
				i++;
				if (i > 10) {
					break;
				}
			}
			session.commit();
			System.out.println("呵呵消息发送结束");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			/*try {
				producer.close();
				session.close();
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
