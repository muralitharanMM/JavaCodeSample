package com.murali.jms;

import javax.naming.Context;
import javax.naming.InitialContext;                                                                           
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;

import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.TopicSession;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
                                                                            
public class Publisher
{
    public static void main(String[] args) throws Exception
    {
    	Hashtable env = new Hashtable();
    	env.put(Context.INITIAL_CONTEXT_FACTORY, 
    	    "java.naming.factory.initial\", \"org.jnp.interfaces.NamingContextFactory");
    	env.put("java.naming.provider.url", "localhost:1099");
    	//env.put(Context.SECURITY_PRINCIPAL, "joeuser");
    	//env.put(Context.SECURITY_CREDENTIALS, "joepassword");

    	Context ctx = new InitialContext(env);
    	
       // get the initial context
       //InitialContext ctx = new InitialContext();
                                                                           
       // lookup the topic object
       Topic topic = (Topic) ctx.lookup("topic/topic0");
                                                                           
       // lookup the topic connection factory
       TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
           lookup("topic/connectionFactory");
                                                                           
       // create a topic connection
       TopicConnection topicConn = connFactory.createTopicConnection();
                                                                           
       // create a topic session
       TopicSession topicSession = topicConn.createTopicSession(false, 
           Session.AUTO_ACKNOWLEDGE);
                                                                           
       // create a topic publisher
       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                                                                           
       // create the "Hello World" message
       TextMessage message = topicSession.createTextMessage();
       message.setText("Hello World");
                                                                           
       // publish the messages
       topicPublisher.publish(message);
                                                                           
       // print what we did
       System.out.println("Message published: " + message.getText());
                                                                           
       // close the topic connection
       topicConn.close();
    }
}
