package com.digitalbooks.reader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.digitalbooks.reader.bookDb.entity.BookList;
import com.digitalbooks.reader.exception.BookNotFoundException;
import com.digitalbooks.reader.bookDb.repository.BooklistRepository;

@Service
public class BookDetailsService {
	@Autowired
	private BooklistRepository BookListRepo;
	
	public List<BookList> listAllBooks() throws BookNotFoundException{
		List<BookList> listBooks = BookListRepo.findAll();
		if(listBooks != null)
			return listBooks;
		else
			throw new BookNotFoundException("No books are available");
	}
	
	public List<BookList> listAllBlockedBooks() throws BookNotFoundException{
		List<BookList> listBooks = BookListRepo.findByBlocked(true);
		if(listBooks != null)
			return listBooks;
		else
			throw new BookNotFoundException("No blocked books are available");
	}
	
	public List<BookList> listAllUnblockedBooks() throws BookNotFoundException{
		List<BookList> listBooks = BookListRepo.findByBlocked(false);
		if(listBooks != null)
			return listBooks;
		else
			throw new BookNotFoundException("No unblocked books are available");
	}
	
	public BookList createBook(BookList book) {
		BookListRepo.save(book);
		return book;
	}
	
//	@KafkaListener(
//			topics = "blocked-book", 
//			groupId="group_id", 
//			containerFactory = "userKafkaListenerFactory"
//		)
//	public void updateBlockedBook(int bookId) {
//		System.out.println(" Kafka in book : "+bookId);
//		BookListRepo.updateBlockedBook(bookId);
//	}
}
