package com.jbignacio.springreactive.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
@DirtiesContext
public class ReactiveControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	/*
	 * @BeforeEach public void setUp() { webTestClient = webTestClient.mutate()
	 * .responseTimeout(Duration.ofMillis(30000)) .build(); }
	 */

	@Test
	public void fluxOfIntegerApproch1() {
		Flux<Integer> fluxOfInteger = webTestClient.get().uri("/reactive").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
				.returnResult(Integer.class).getResponseBody();

		StepVerifier.create(fluxOfInteger).expectNext(1).expectNext(2).expectNext(3).expectNext(4).expectNext(5)
				.verifyComplete();
	}

	@Test
	public void fluxOfIntegerApproch2() {
		webTestClient.get()
			.uri("/reactive")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(Integer.class)
			.hasSize(5);
	}

	@Test
	public void fluxOfIntegerApproch3() {
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

		EntityExchangeResult<List<Integer>> actual = webTestClient.get()
											.uri("/reactive")
											.accept(MediaType.APPLICATION_JSON)
											.exchange()
											.expectStatus().isOk()
											.expectHeader().contentType(MediaType.APPLICATION_JSON)
											.expectBodyList(Integer.class)
											.returnResult();
		assertEquals(expected, actual.getResponseBody());
	}

	@Test
	public void fluxOfIntegerApproch4() {
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

		webTestClient.get()
			.uri("/reactive")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(Integer.class)
			.consumeWith(response -> {
				assertEquals(expected, response.getResponseBody());
			});
	}

	@Test
	public void fluxOfIntegerInfinite() {
		Flux<Long> fluxOfInteger = webTestClient.get()
										.uri("/reactive")
										.accept(MediaType.APPLICATION_JSON)
										.exchange()
										.expectStatus()
										.isOk().expectHeader()
										.contentType(MediaType.APPLICATION_JSON)
										.returnResult(Long.class).getResponseBody();
		StepVerifier.create(fluxOfInteger)
			.expectNext(1L)
			.expectNext(2L)
			.expectNext(3L)
			.thenCancel()
			.verify();

	}

	@Test
	public void monoOfLong() {

		webTestClient.get()
			.uri("/mono")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Integer.class)
			.consumeWith(response -> {
				assertEquals(1, response.getResponseBody());
			});
	}

}
