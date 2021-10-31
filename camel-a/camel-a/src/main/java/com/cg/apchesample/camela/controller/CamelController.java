package com.cg.apchesample.camela.controller;

import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamelController {
private final FluentProducerTemplate producerTemplate;



public CamelController(FluentProducerTemplate producerTemplate) {

	this.producerTemplate = producerTemplate;
}



@GetMapping("/messages")
public String getMessages() {
return producerTemplate.to("direct:Message-Route").request(String.class);
}

@PostMapping("/message")
public String setMessage(@RequestBody String body) {
return producerTemplate.withBody(body).withHeader("test","1").to("direct:sent-messages").request(String.class);
}
}
