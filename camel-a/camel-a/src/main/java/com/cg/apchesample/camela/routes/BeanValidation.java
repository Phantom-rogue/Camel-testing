package com.cg.apchesample.camela.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.springframework.stereotype.Component;
import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;

import com.cg.apchesample.camela.Exceptions.GeneralException;
import com.cg.apchesample.entities.Book;
//@Component
public class BeanValidation extends RouteBuilder{
	

	@Override
    public void configure() throws Exception{
		onException(BeanValidationException.class)
        .log("This is error message: ${exception}");   

        Book book = Book.builder().title("Wolf children").
        		description("CHAPTER IV. The Rabbit Sends in a trembling voice to its children")
        		.author(null).image("http://placeimg.com/480/640/any").genre("slice of life")
        		.isbn("dfsdfsd").published("1978-07-12").publisher("Est Eius").build();
               

        from("timer:foo?period=100000")
        .doTry()
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
                .log("recieved body")
                .doFinally()
                .log("${body}")
                .log("mock:end");
        
        
        
     
        
        

    }

}
