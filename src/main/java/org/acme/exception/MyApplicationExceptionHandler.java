package org.acme.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.vertx.core.json.JsonObject;

@Produces(MediaType.APPLICATION_JSON)
@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<MyApplicationException> 
{
	@Override
	public Response toResponse(MyApplicationException exception) 
	{
		JsonObject json = new JsonObject()
    	        .put("Errors", exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(json).build();	
	}
}