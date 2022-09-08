package com.digitalbooks.reader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.reader.model.JwtRequest;
import com.digitalbooks.reader.model.JwtResponse;
import com.digitalbooks.reader.readerDb.entity.ReaderMaster;
import com.digitalbooks.reader.readerDb.repository.ReaderMasterRepository;
import com.digitalbooks.reader.service.ReaderAuthenticationService;
import com.digitalbooks.reader.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1/digitalbooks/reader")
public class ReaderAuthenticationController {
	
	@Autowired
	private ReaderMasterRepository readerRepo;
	
	@GetMapping
	public List<ReaderMaster> getString() {
		List<ReaderMaster> readerList = readerRepo.findAll();
		return readerList;
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ReaderAuthenticationService readerAuthService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest inputReq) throws Exception {

		authenticate(inputReq.getUsername(), inputReq.getPassword());

		final UserDetails userDetails = readerAuthService
				.loadUserByUsername(inputReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
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
