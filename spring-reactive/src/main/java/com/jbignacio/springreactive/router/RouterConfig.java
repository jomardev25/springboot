package com.jbignacio.springreactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.jbignacio.springreactive.handler.RequestHandler;

@Configuration
public class RouterConfig {

	@Bean
	RouterFunction<ServerResponse> routes(RequestHandler handler){
		return RouterFunctions
				.route(GET("/functional/flux").and(accept(MediaType.APPLICATION_JSON)), handler::flux)
				.andRoute(GET("/functional/mono").and(accept(MediaType.APPLICATION_JSON)), handler::mono)
				.andRoute(POST("/functional/users").and(accept(MediaType.APPLICATION_JSON)), handler::create)
				.andRoute(GET("/functional/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::show);
	}

}
