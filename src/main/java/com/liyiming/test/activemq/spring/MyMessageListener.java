/**
 * @author:liyiming
 * @date:2018年1月31日
 * Description:
 **/
package com.liyiming.test.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Title: M Description: Company:pusense
 * 
 * @author ：lyiming
 * @date ：2018年1月31日
 **/
public class MyMessageListener implements MessageListener{

	public void onMessage(Message arg0) {
		try {
			String message = ((TextMessage) arg0).getText();
			System.out.println("textmessage:" + message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
