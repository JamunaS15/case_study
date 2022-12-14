package com.digitalbooks.reader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.reader.bookDb.entity.BookList;
import com.digitalbooks.reader.exception.ReaderNotFoundException;
import com.digitalbooks.reader.model.BookReq;
import com.digitalbooks.reader.model.ReaderResponse;
import com.digitalbooks.reader.readerDb.entity.ReaderMaster;
import com.digitalbooks.reader.readerDb.entity.SubscribeBook;
import com.digitalbooks.reader.service.ReaderAuthenticationService;
import com.digitalbooks.reader.service.ReaderBookDetailsService;

@RestController
@RequestMapping("/api/v1/digitalbooks/reader")
@CrossOrigin
public class ReaderController {
	@Autowired
	private ReaderBookDetailsService readerBookService;
	
	@Autowired
	private ReaderAuthenticationService readerAuthService;
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribeBook(@RequestBody SubscribeBook subscribeBook){
		SubscribeBook subscribe = readerBookService.readerSubscribeBook(subscribeBook);
		return ResponseEntity.ok(subscribe);
	}
	
	@GetMapping("/unsubscribe/{transactionId}")
	public ResponseEntity<?> unsubscribeBook(@PathVariable String transactionId){
		String res = readerBookService.readerUnsubscribeBook(transactionId);
		Map<String, String> map = new HashMap<>();
		map.put("details",res);
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/subscribeList/{emailId}")
	public ResponseEntity<?> subscribeList(@PathVariable String emailId) throws ReaderNotFoundException{
		List<ReaderResponse> listBooks = readerBookService.subscribedBooks(emailId);
		return ResponseEntity.ok(listBooks);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ReaderMaster> newReader(@RequestBody ReaderMaster reader){
		ReaderMaster newreader = readerAuthService.signupAuthor(reader);
		return ResponseEntity.ok(newreader);
	} 
	@GetMapping("/blockedMail/{bookId}")
	public ResponseEntity<?> blockedMailSend(@PathVariable int bookId){
		readerBookService.blockedMailMessage(bookId);
		return ResponseEntity.ok("mail send successfully");
	}
	
	@PostMapping("/searchBooks")
	public ResponseEntity<?> searchBooks(@RequestBody BookReq bookReq){
		List<BookList> book = readerBookService.searchBooks(bookReq);
		return ResponseEntity.ok(book);
	}
}
