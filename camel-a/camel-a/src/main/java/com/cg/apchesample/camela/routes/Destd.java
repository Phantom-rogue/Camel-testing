package com.cg.apchesample.camela.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import com.cg.apchesample.entities.BookList;
//@Component
public class Destd extends RouteBuilder{
JacksonDataFormat jd= new JacksonDataFormat(BookList.class);

	@Override
	public void configure() throws Exception {
		from("direct:test")
		.log("2....")
		.log("message in 2... ${body}")
		.to("https://fakerapi.it/api/v1/books?_quantity=1")
		.unmarshal(jd)
		.log("${body}")
		.marshal(jd)
		.log("${body}");
		
	}

}
