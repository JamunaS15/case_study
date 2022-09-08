package com.digitalbooks.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.author.entities.AuthorMaster;
import com.digitalbooks.author.model.JwtRequest;
import com.digitalbooks.author.model.JwtResponse;
import com.digitalbooks.author.service.AuthorDetailsService;
import com.digitalbooks.author.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1/digitalbooks/author")
@CrossOrigin
public class AuthorAuthenticateController {
	

	@GetMapping
	public String loginAuthor() {
		return "log on";
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthorDetailsService userDetailsService;
	
	@Autowired
	private AuthorDetailsService authorService;
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest inputReq) throws Exception {

		authenticate(inputReq.getUsername(), inputReq.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(inputReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthorMaster author = authorService.getAuthor(inputReq.getUsername());
		System.out.println(" author details "+author);
		return ResponseEntity.ok(new JwtResponse(token, author.getAuthorId()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
