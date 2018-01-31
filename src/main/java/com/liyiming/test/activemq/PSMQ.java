/**
 * @author:liyiming
 * @date:2018年1月29日
 * Description:
 **/
package com.liyiming.test.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: PSMQ Description: Company:pusense
 * 发布/订阅消息模型实例 消息发布端
 * @author ：lyiming
 * @date ：2018年1月29日
 **/
public class PSMQ{

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建话题
		Topic topic = session.createTopic("myTopic.messages");
		// 为话题创建消息生产者
		MessageProducer producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		while (true) {
			TextMessage message = session.createTextMessage();
			message.setText("message_" + System.currentTimeMillis());
			producer.send(message);
			System.out.println("Sent message: " + message.getText());
		}
	}
}
