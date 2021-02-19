package org.acme.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.acme.dto.Meta;
import org.acme.dto.ResponseError;
import org.acme.dto.ResponseErrorContainer;
import org.acme.dto.ResponseErrorList;

import io.vertx.core.json.JsonObject;

@Produces(MediaType.APPLICATION_JSON)
@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<MyApplicationException> 
{
	@Override
	public Response toResponse(MyApplicationException exception) 
	{
		
		ResponseError  error = new ResponseError("teste", "Ocorreu um erro", exception.getMessage());
		List<ResponseError> errors = new ArrayList<ResponseError>();
		errors.add(error);
		
		ResponseErrorList responseErrorList = ResponseErrorList.builder().errors(errors).build();
		Meta meta = new Meta();
		
		//ResponseErrorContainer.builder().errors(responseErrorList).meta(meta).build();
		
		return Response.status(Status.BAD_REQUEST).entity(ResponseErrorContainer.builder().errors(responseErrorList).meta(meta).build()).build();
		
	}
}