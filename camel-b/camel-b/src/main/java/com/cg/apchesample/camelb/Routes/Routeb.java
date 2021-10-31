package com.cg.apchesample.camelb.Routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class Routeb extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:foo")
		.to("log:recieved-messages");
		
	}

}
