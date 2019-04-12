package com.jose.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jose.messanger.model.ErrorMessage;

	//@Provider
	//by removing @provider  deregisters the error h
	public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

		@Override
		public Response toResponse(Throwable ex) {
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500,"the documentatiuon is not available");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build();
		}

	}
