package com.digitalbooks.author.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.author.entities.AuthorBookDetails;
import com.digitalbooks.author.entities.AuthorMaster;
import com.digitalbooks.author.exception.AuthorNotFoundException;
import com.digitalbooks.author.service.AuthorBookDetailsService;
import com.digitalbooks.author.service.AuthorDetailsService;

@RestController
//@CrossOrigin(origins = {"https://hoppscotch.io", "http://localhost:4200"})
@CrossOrigin
@RequestMapping("/api/v1/digitalbooks/author")
public class AuthorController {
	
	@Autowired
	private AuthorDetailsService authorService;
	
	@Autowired
	private AuthorBookDetailsService authorBookService;
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupAuthor(@RequestBody AuthorMaster author){
		AuthorMaster authorSignup = authorService.signupAuthor(author);
		return ResponseEntity.ok(authorSignup);
	}
	
	@GetMapping("/listBooks/{authorId}")
	public ResponseEntity<?> listAuthorBooks(@PathVariable int authorId) throws AuthorNotFoundException{
		List<AuthorBookDetails> listBooks = authorBookService.listAuthorCreatedBooks(authorId);
		return ResponseEntity.ok(listBooks);
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody AuthorBookDetails authorBookDetail){
		AuthorBookDetails createBook = authorBookService.createBook(authorBookDetail);
		return ResponseEntity.ok(createBook);
	}
	
	@GetMapping("/listBlocked/{authorId}")
	public ResponseEntity<?> listAuthorBlockedBooks(@PathVariable int authorId) throws AuthorNotFoundException{
		List<AuthorBookDetails> listBooks = authorBookService.listAuthorBlockedBooks(authorId);
		return ResponseEntity.ok(listBooks);
	}
	
	@GetMapping("/blockBook/{authorId}/{bookId}")
	public ResponseEntity<?> blockBook(@PathVariable int authorId, @PathVariable int bookId){
		AuthorBookDetails book = authorBookService.authorBlockBook(bookId, authorId);
		//kafkaTemplate.send("blocked-book", bookId);
		Map<String,String> response = new HashMap<>();
		//response.put("details",res);
		return ResponseEntity.ok(book);
	}
	
	@GetMapping("/unblockBook/{authorId}/{bookId}")
	public ResponseEntity<?> unblockBook(@PathVariable int authorId, @PathVariable int bookId){
		AuthorBookDetails book = authorBookService.authorUnblockBook(bookId, authorId);
		Map<String,String> response = new HashMap<>();
		//response.put("details",res);
		return ResponseEntity.ok(book);
	}
	
	@GetMapping("/getAllAuthors")
	public ResponseEntity<?> listAllAuthors(){
		List<AuthorMaster> list = authorService.getAllAuthours();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/getAuthor")
	public ResponseEntity<?> getAuthor(@RequestBody AuthorMaster author){
		author = authorService.getAuthor(author.getEmailId());
		return ResponseEntity.ok(author);
	}
}
