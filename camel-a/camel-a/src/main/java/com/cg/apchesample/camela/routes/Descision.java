package com.cg.apchesample.camela.routes;



import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class Descision extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		Predicate fc= header("dummy").isEqualTo(1);
		Predicate sc= header("dummy").isEqualTo(2);
		
		from("timer:descision-timer?period=50000")
			.id("descision-route")
				.setHeader("dummy").constant(2).choice()
				.when(fc).log("first-condition").transform().constant("message from choice 1")
					.to("direct:foo")
				.when(sc).log("second-condition")
				.otherwise()
					.log("otherwise").endChoice();

		from("direct:foo").log("2nd route...").to("log:choice-logger");
	}

}
