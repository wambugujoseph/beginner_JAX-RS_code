package com.jose.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jose.messanger.model.Message;
import com.jose.messanger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	
	//check why BeanParam is not working
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size) {
		if(year>0) {
			return messageService.getAllMessagesForYear(year);
		}
		if(start >0 && size>0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	} 
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage =  messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		 return Response.created(uri)
			//status(Status.CREATED)
			.entity(newMessage)
			.build();
		
		//return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(Message message, @PathParam("messageId") long id) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
}
