package com.microservices.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.dto.PolicyDto;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {

	private final WebClient client = WebClient.create("http://localhost:8081/api/v1/policy");

	@GetMapping("/all")
	public ResponseEntity<List<PolicyDto>> findAllPolicy() {
		return new ResponseEntity<List<PolicyDto>>(
				client.get().uri("/all").retrieve().bodyToFlux(PolicyDto.class).collectList().block(), HttpStatus.OK);
	};

	@GetMapping("/{id}")
	public ResponseEntity<PolicyDto> findById(@PathVariable Integer id) {
		return new ResponseEntity<PolicyDto>(client.get().uri("/" + id).retrieve().bodyToMono(PolicyDto.class).block(),
				HttpStatus.OK);
	};

	@PostMapping
	public ResponseEntity<PolicyDto> createPolicy(@RequestBody PolicyDto policy) {
		return new ResponseEntity<PolicyDto>(
				client.post().body(policy, PolicyDto.class).retrieve().bodyToMono(PolicyDto.class).block(),
				HttpStatus.CREATED);
	};

	@PutMapping("/{id}")
	public ResponseEntity<PolicyDto> updateStatus(@PathVariable Integer id, @RequestParam String status)
			throws Exception {
		return new ResponseEntity<PolicyDto>(
				client.put().uri(builder -> builder.path("/" + id).queryParam("status", "{status}").build(status))
						.retrieve().bodyToMono(PolicyDto.class).block(),
				HttpStatus.ACCEPTED);
	};

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		client.delete().uri("/" + id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
