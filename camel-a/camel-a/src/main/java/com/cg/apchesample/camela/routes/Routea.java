package com.cg.apchesample.camela.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Routea extends RouteBuilder{

	
	@Autowired
	DateBean dateTime;
	@Autowired
	SimpleLogger sl;
	@Override
	public void configure() throws Exception {
		//listen to queue
		//save to database or log it
		
		
		from("timer:first-timer")
		.log("${body}")
		.transform().constant("My message")
		.log("${body}")
		.bean(sl)
		.log("${body}")
		.process(new SimpleLoggingProcessor())
//		.transform().constant("My message")
		.bean(dateTime)
		.to("log:first-timer");
		
	}
	
	

}
@Component
class DateBean {
public LocalDateTime getDate() {
	return LocalDateTime.now();
}
}
@Component
class SimpleLogger{
	Logger logger= LoggerFactory.getLogger(SimpleLogger.class);
	
	public void process(String message) {
		logger.info("Here is the message: {}",message);
	}
}

 class SimpleLoggingProcessor implements Processor {
	 Logger logger= LoggerFactory.getLogger(SimpleLogger.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("Processed message: {}",exchange.getMessage().getBody());
	}

}
