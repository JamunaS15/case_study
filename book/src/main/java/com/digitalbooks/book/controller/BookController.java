package com.digitalbooks.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.book.entity.BookList;
import com.digitalbooks.book.exception.BookNotFoundException;
import com.digitalbooks.book.service.BookDetailsService;

@RestController
@RequestMapping("/api/v1/digitalbooks/book")
public class BookController {
	@Autowired
	private BookDetailsService bookService;
	
	@GetMapping
	public ResponseEntity<?> getAllBooks() throws BookNotFoundException{
		List<BookList> listBooks = bookService.listAllBooks();
		return ResponseEntity.ok(listBooks);
	}
	
	@GetMapping("/blockedList")
	public ResponseEntity<?> getAllBlockedBooks() throws BookNotFoundException{
		List<BookList> listBooks = bookService.listAllBlockedBooks();
		return ResponseEntity.ok(listBooks);
	}
	
	@GetMapping("/unblockList")
	public ResponseEntity<?> getAllUnblockBooks() throws BookNotFoundException{
		List<BookList> listBooks = bookService.listAllUnblockedBooks();
		return ResponseEntity.ok(listBooks);
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody BookList book){
		BookList createdBook = bookService.createBook(book);
		return ResponseEntity.ok(createdBook);
	}
	
	@GetMapping("/searchBooks/title")
	public ResponseEntity<?> searchBooks(@RequestParam String title){
		System.out.println(" title in controller "+title);
		List<BookList> books = bookService.findBooksByTitle(title);
		return ResponseEntity.ok(books);
	}
}
