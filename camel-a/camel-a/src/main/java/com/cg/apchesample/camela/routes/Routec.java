package com.cg.apchesample.camela.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
//@Component
public class Routec extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer:first-timer").
		transform().constant("message").log("1... ${body} ").
		to("direct:test");
		
		
		
	}

}
