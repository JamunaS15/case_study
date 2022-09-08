package com.digitalbooks.reader.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalbooks.reader.readerDb.entity.ReaderMaster;
import com.digitalbooks.reader.readerDb.repository.ReaderMasterRepository;



@Service
public class ReaderAuthenticationService implements UserDetailsService{
	
	@Autowired
	private ReaderMasterRepository readerMasterRepo;
	
	public ReaderMaster signupAuthor(ReaderMaster reader) {
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		String password = encryptPassword(reader.getPassword());
		reader.setPassword(password);
		reader.setCreatedAt(timestamp1);
		readerMasterRepo.save(reader);
		return reader;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ReaderMaster author = readerMasterRepo.findByUsername(username);
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "AUTHOR"));
		if(author != null) {
			//String password = encryptPassword(author.getPassword());
			return new User(username, author.getPassword(), authorities);
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	private String encryptPassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encryptPassword = encoder.encode(password);
		return encryptPassword;
	}
}
