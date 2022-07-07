package com.jbignacio.springreactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactiveTest {

	@Test
	public void fluxTest() {

		Flux<String> stringFlux = Flux.just("Spring", "Programming", "Reactive")
									//.concatWith(Flux.error(new RuntimeException("An error occured.")))
									.concatWith(Flux.just("After Error"))
									.log();

		stringFlux
			.map(str -> str.concat(" flux"))
			.subscribe(
					System.out::println,
					(error) -> System.err.println("Exception is " + error),
					() -> System.out.println("Completed.")
			);

	}

	@Test
	public void fluxTestWithoutError() {
		Flux<String> stringFlux = Flux.just("Programming", "Spring", "Reactive")
				.log();

		StepVerifier.create(stringFlux)
					.expectNext("Programming")
					.expectNext("Spring")
					.expectNext("Reactive")
					.verifyComplete();
	}


	@Test
	public void fluxTestEleementWithoutError() {
		Flux<String> stringFlux = Flux.just("Programming", "Spring", "Reactive")
				.log();

		StepVerifier.create(stringFlux)
					.expectNext("Programming", "Spring", "Reactive")
					.verifyComplete();
	}

	@Test
	public void fluxTestWitError() {
		Flux<String> stringFlux = Flux.just("Programming", "Spring", "Reactive")
				.concatWith(Flux.error(new RuntimeException("An error occured.")))
				.log();

		StepVerifier.create(stringFlux)
					.expectNext("Programming")
					.expectNext("Spring")
					.expectNext("Reactive")
					.expectErrorMessage("An error occured.")
					//.expectError(RuntimeException.class)
					.verify();
	}

	@Test
	public void fluxTestElementCountWitError() {
		Flux<String> stringFlux = Flux.just("Programming", "Spring", "Reactive", "Reactive PG")
				.concatWith(Flux.error(new RuntimeException("An error occured.")))
				.log();

		StepVerifier.create(stringFlux)
					.expectNextCount(4L)
					.expectError(RuntimeException.class)
					.verify();
	}

	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Mono String")
									.log();

		stringMono.subscribe(System.out::println);

		StepVerifier.create(stringMono)
					.expectNext("Mono String")
					.verifyComplete();
	}

	@Test
	public void monoTestWithError() {
		StepVerifier.create(Mono.error(new RuntimeException("An error occured.")).log())
					.verifyError();
	}

}
