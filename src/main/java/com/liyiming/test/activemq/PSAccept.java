/**
 * @author:liyiming
 * @date:2018年1月29日
 * Description:
 **/
package com.liyiming.test.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: PS Description: Company:pusense
 * 消息订阅端 
 * @author ：lyiming
 * @date ：2018年1月29日
 **/
public class PSAccept{

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建话题
		Topic topic = session.createTopic("myTopic.messages");
		// 为话题创建消费者
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener(){
			public void onMessage(Message message) {
				TextMessage tm = (TextMessage) message;
				try {
					System.out.println("Received message: " + tm.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
