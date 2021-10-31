package com.cg.apchesample.camela.routes;
import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;
import static org.apache.camel.Exchange.HTTP_METHOD;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.cg.apchesample.entities.Book;
@Component
public class RestRoute extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		
		Book book = Book.builder().title("Wolf children").
        		description("CHAPTER IV. The Rabbit Sends in a trembling voice to its children")
        		.author("james").image("http://placeimg.com/480/640/any").genre("slice of life")
        		.isbn("dfsdfsd").published("1978-07-12").publisher("Est Eius").build();
		
		onException(BeanValidationException.class)
        .log("This is error message: ${exception}"); 
		
		from("direct:Message-Route")
		.doTry()
		.log("Entered get message route")
		.setHeader(HTTP_METHOD).constant(HttpMethod.GET)
		.process(exchange->{exchange.getIn().setHeader(HTTP_METHOD, exchange);
			exchange.getIn().setHeader("dummy", "true");})
		
		.setBody().constant(book)
		.to("bean-validator:validateAddress")
		.doCatch(BeanValidationException.class)
        
        .process(new Processor() {
        	public void process(Exchange exchange) throws Exception {
                log.info("Handling exception");
                String exception = exchange.getProperty(EXCEPTION_CAUGHT, String.class);
                log.info("Exception is :{}",exception);
                
            }
        })
        .doFinally().log("The end ;)");
		
		
		
		from("direct:sent-messages")
		.log("Recieving messages...")
		.log("headers:${headers}").process(exchange->{
			String body=exchange.getIn().getBody(String.class);
			exchange.getMessage().setBody("Message sent! \n Message Body: "+body);
		});
	}

}
