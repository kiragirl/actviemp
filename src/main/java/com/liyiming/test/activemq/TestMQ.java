/**
* @author:liyiming
* @date:2018年1月26日
* Description:
**/
package com.liyiming.test.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * 	Title: MQ
 *	Description:
 *	Company:pusense
 * 	@author ：lyiming
 * 	@date ：2018年1月26日
 **/
public class TestMQ {  
    public static void main(String[] args) {  
        int i =0;  
        //链接工厂  
        ConnectionFactory connectionFactory = null;  
        // 链接对象  
        Connection connection = null;  
        // 会话对象  
        Session session = null;  
        // 目的地  
        Destination destination = null;  
        // 消息生产者  
        MessageProducer producer = null;  
        connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");  
        try {  
            connection = connectionFactory.createConnection();  
            connection.start();  
            //第一个参数是否开启事务 true开启 ,false不开启事务，如果开启记得手动提交  
            //参数二，表示的是签收模式，一般使用的有自动签收和客户端自己确认签收  
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
            destination = session.createQueue("test-destination");  
            //为目的地创建消息生产者  
            producer = session.createProducer(destination);  
            //消息是否为持久性的，这个不设置也是可以的，默认是持久的  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            while(true) {  
                TestBean tbean = new TestBean();  
                tbean.setAge(25);  
                tbean.setName("hellojava" +i);  
                producer.send(session.createObjectMessage(tbean));  
                i++;  
                if( i>10) {  
                    break;  
                }  
            }  
            System.out.println("呵呵消息已发送");  
        } catch (JMSException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } finally {  
            try {  
                producer.close();  
                session.close();  
                connection.close();  
            } catch (JMSException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
}  
