package com.digitalbooks.author.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.author.entities.AuthorBookDetails;
import com.digitalbooks.author.exception.AuthorNotFoundException;
import com.digitalbooks.author.repository.AuthorBookDetailsRepository;

@Service
public class AuthorBookDetailsService {
	
	@Autowired
	private AuthorBookDetailsRepository authorBookRepo;
	
	public AuthorBookDetails createBook(AuthorBookDetails createBook) {
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		createBook.setUpdateAt(timestamp1);
		authorBookRepo.save(createBook);
		return createBook;
	}
	
	public List<AuthorBookDetails> listAuthorCreatedBooks(int authorId) throws AuthorNotFoundException{
		List<AuthorBookDetails> authorBooksList = authorBookRepo.findByAuthorId(authorId);
		if(authorBooksList != null)
			return authorBooksList;
		else
			throw new AuthorNotFoundException("Author id "+ authorId + " is not found");
	}
	
	public AuthorBookDetails authorBlockBook(int bookId,int authorId) {
		authorBookRepo.authorBlockBook(bookId, authorId);
		AuthorBookDetails book = authorBookRepo.findByBookId(bookId);
		String res = "blocking failure";
		//if(blocked)
			res = "blocked successfully";
		return book;
	}
	
	public AuthorBookDetails authorUnblockBook(int bookId,int authorId) {
		authorBookRepo.authorUnblockBook(bookId, authorId);
		AuthorBookDetails book = authorBookRepo.findByBookId(bookId);
		String res = "unblocking failure";
		//if(unblocked)
			res = "unblocked successfully";
		return book;
	}
	
	public List<AuthorBookDetails> listAuthorBlockedBooks(int authorId) throws AuthorNotFoundException{
		List<AuthorBookDetails> authorBooksList = authorBookRepo.findByAuthorIdAndBlocked(authorId);
		if(authorBooksList != null)
			return authorBooksList;
		else
			throw new AuthorNotFoundException("Author id : "+authorId +" is not found");
	}
	
	
}
