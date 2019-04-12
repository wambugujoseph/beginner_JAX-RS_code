package com.jose.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectAnnotation {
	
	@GET
	@Path("annotation")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
										 	@HeaderParam("customHeaderValue") String header,
										 	@CookieParam("name") String cookie) {
		return"Matrix Param: "+ matrixParam+" Header Param: " + header + " Cookie "+ cookie;
	}
	
	//@FormParam used in getting parameter form Html form

	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		
		String path = uriInfo.getAbsolutePath().toString();
		String cookie = headers.getCookies().toString();
		return "Path: " + path + "\n Cookie: " + cookie;
	}
}
