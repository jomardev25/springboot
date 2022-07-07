package com.jbignacio.springreactive.handler;

import java.net.URI;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.jbignacio.springreactive.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {

	public Mono<ServerResponse> flux(ServerRequest request){

		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
					Flux.just(1, 2, 3, 4, 5).log(), Integer.class
				);

	}


	public Mono<ServerResponse> mono(ServerRequest request){
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
					Mono.just(1).log(), Integer.class
				);
	}

	public Mono<ServerResponse> create(ServerRequest request){

		return request.bodyToMono(User.class)
				.flatMap(user ->
					Mono.just( new User(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate()) )
				)
				.flatMap(user -> ServerResponse.created(URI.create("/books/" + user.getId()))
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(user)));

	}

	public Mono<ServerResponse> show(ServerRequest request){

		Long id = Long.parseLong(request.pathVariable("id"));
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue( new User(id, "John", "Doe", new Date()) ));


	}

}
