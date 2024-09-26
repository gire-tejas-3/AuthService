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

import com.microservices.dto.ClaimDto;

@RestController
@RequestMapping("/api/v1/claim")
public class ClaimController {

	private final WebClient client = WebClient.create("http://localhost:8082/api/v1/claim");

	@GetMapping("/all")
	public ResponseEntity<List<ClaimDto>> findAllClaim() {
		return new ResponseEntity<List<ClaimDto>>(
				client.get().uri("/all").retrieve().bodyToFlux(ClaimDto.class).collectList().block(), HttpStatus.OK);
	};

	@GetMapping("/{id}")
	public ResponseEntity<ClaimDto> findById(@PathVariable Integer id) {
		return new ResponseEntity<ClaimDto>(client.get().uri("/" + id).retrieve().bodyToMono(ClaimDto.class).block(),
				HttpStatus.OK);
	};

	@PostMapping
	public ResponseEntity<ClaimDto> createClaim(@RequestBody ClaimDto Claim) {
		return new ResponseEntity<ClaimDto>(
				client.post().uri("/").body(Claim, ClaimDto.class).retrieve().bodyToMono(ClaimDto.class).block(),
				HttpStatus.CREATED);
	};

	@PutMapping("/{id}")
	public ResponseEntity<ClaimDto> updateStatus(@PathVariable Integer id, @RequestParam String status)
			throws Exception {
		return new ResponseEntity<ClaimDto>(
				client.put().uri(builder -> builder.path("/" + id).queryParam("status", "{status}").build(status))
						.retrieve().bodyToMono(ClaimDto.class).block(),
				HttpStatus.ACCEPTED);
	};

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		client.delete().uri("/" + id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
