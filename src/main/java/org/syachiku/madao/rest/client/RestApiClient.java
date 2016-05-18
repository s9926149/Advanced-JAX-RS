package org.syachiku.madao.rest.client;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.syachiku.madao.rest.MyResource;
import org.syachiku.madao.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		Message message1 = singleMessageTarget
				.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		Message message2 = singleMessageTarget
				.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		Message newMessage = new Message(4, "My New message from JAX-RS client.", "Madao");
		Response postResponse = messagesTarget
				.request()
				.post(Entity.json(newMessage));;
		
		if (postResponse.getStatus() != 201){
			System.out.println("Error");
		}
		
		Message createdMessage = postResponse.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());
		
		/*
		 * You can convert the retrieved content into String class, get the actual http payload, to debug, which is much less possible to go wrong.  
		*/
		/*
		 * WebTarget target = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/messages/1");
		Builder builder = target.request();
		Response response = builder.get();
		*/
		//Message message = response.readEntity(Message.class);
		
		Date test;
		MyResource mr = new MyResource();
		test = mr.testMethod();
	}

}
