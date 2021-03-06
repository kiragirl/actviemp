/**
 * @author:liyiming
 * @date:2018年1月29日
 * Description:
 **/
package com.liyiming.test.activemq.filter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: AA Description: Company:pusense
 * http://blog.csdn.net/qh_java/article/details/60873376
 * @author ：lyiming
 * @date ：2018年1月29日
 **/
public class Conmuser{

	// 单例模式
	// 1、连接工厂
	private ConnectionFactory connectionFactory;
	// 2、连接对象
	private Connection connection;
	// 3、Session对象
	private Session session;
	// 4、生产者
	private MessageConsumer messageConsumer;
	// 5、目的地址
	private Destination destination;
	// 消息选择器
	public final String SELECTOR_1 = "age > 25";
	public final String SELECTOR_2 = " age > 20 and color='black'";

	public Conmuser() {
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
			this.connection = connectionFactory.createConnection();
			this.connection.start();
			// 设置自动签收模式
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.destination = this.session.createQueue("first");
			// 在构造消费者的时候，指定了 消息选择器
			// 有选择性的消费消息
			this.messageConsumer = this.session.createConsumer(destination, SELECTOR_1);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public Session getSession() {
		return this.session;
	}

	// 用于监听消息队列的消息
	class MyLister implements MessageListener{

		public void onMessage(Message message) {
			try {
				if (message instanceof TextMessage) {
					TextMessage ret = (TextMessage) message;
					System.out.println("results;" + ret.getText());
				}
				if (message instanceof MapMessage) {
					MapMessage ret = (MapMessage) message;
					System.out.println(ret.toString());
					System.out.println(ret.getString("name"));
					System.out.println(ret.getInt("age"));
				}
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		}

	}

	// 用于异步监听消息
	public void receiver() {
		try {
			this.messageConsumer.setMessageListener(new MyLister());
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Conmuser conmuser = new Conmuser();
		conmuser.receiver();

	}
}
