package com.digitalbooks.author.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
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

import com.digitalbooks.author.entities.AuthorMaster;
import com.digitalbooks.author.repository.AuthorMasterRepository;

@Service
public class AuthorDetailsService implements UserDetailsService{
		@Autowired
		private AuthorMasterRepository authorRepo;
		
	public AuthorMaster authenticateAuthor(String username, String password) {
		AuthorMaster author = authorRepo.findByUsernameAndPassword(username, password);
		System.out.println(" author "+author.toString());
		return author;
	}
	
	public AuthorMaster signupAuthor(AuthorMaster author) {
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		String password = encryptPassword(author.getPassword());
		author.setPassword(password);
		author.setDataTime(timestamp1);
		authorRepo.save(author);
		return author;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthorMaster author = authorRepo.findByUsername(username);
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
	
	public List<AuthorMaster> getAllAuthours(){
		List<AuthorMaster> list = authorRepo.findAll();
		return list;
	}
	public AuthorMaster getAuthor(String emailId) {
		AuthorMaster author = authorRepo.findByUsername(emailId);
		return author;
	}
}
