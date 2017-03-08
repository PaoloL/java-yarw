/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpeppers.motorvehicle.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author maverick
 */
@Path("/ping")
public class Ping {
    private String returnValue = "Ping: System OK";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return returnValue;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String pingXml() {
		return "<?xml version=\"1.0\"?>" + "<ping>" + returnValue + "</ping>";
	} 

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String pingHtml() {
		return "<html><title>" + returnValue + "</title>"
				+ "<body><h1>" + returnValue + "</h1></body></html> ";
	}

}
