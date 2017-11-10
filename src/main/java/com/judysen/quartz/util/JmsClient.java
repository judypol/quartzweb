package com.judysen.quartz.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;


/**
 * 消息生产者,用于发送消息
 *
 * @author Mac
 */
@Service("jmsClient")
public class JmsClient {

//    @Autowired
//    private JmsTemplate jmsTemplate;


    //    public void sendMessage(Destination destination, final String message) throws Exception {
//        jmsTemplate.send(destination, new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage(message);
//                return textMessage;
//            }
//        });
//    }
    public void sendMessage(Destination destination, final String message) throws Exception {
//        jmsTemplate.send(destination, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage(message);
//                return textMessage;
//            }
//        });
    }
}

