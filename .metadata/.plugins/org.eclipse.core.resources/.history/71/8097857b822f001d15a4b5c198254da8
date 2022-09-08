package com.digitalbooks.reader.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.digitalbooks.reader.bookDb.entity.BookList;
import com.digitalbooks.reader.bookDb.repository.BooklistRepository;
import com.digitalbooks.reader.exception.ReaderNotFoundException;
import com.digitalbooks.reader.model.BookReq;
import com.digitalbooks.reader.model.ReaderResponse;
import com.digitalbooks.reader.readerDb.entity.SubscribeBook;
import com.digitalbooks.reader.readerDb.repository.SubscribeBookRepository;

@Service
public class ReaderBookDetailsService {
	@Autowired
	private SubscribeBookRepository subscribeRepo;
	
	@Autowired
	private BooklistRepository bookrepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public SubscribeBook readerSubscribeBook(SubscribeBook subscribeBook) {
		LocalDateTime date = LocalDateTime.now();
		subscribeBook.setCreatedAt(date);
		String transId = randomString();
		subscribeBook.setTransactionId(transId);
		subscribeBook.setUnsubscribe(false);
		subscribeRepo.save(subscribeBook);
		return subscribeBook;
	}
	
	public String readerUnsubscribeBook(String transactionId) {
		String res = "";
		SubscribeBook subBook = subscribeRepo.findByTransactionId(transactionId);
		LocalDateTime date = subBook.getCreatedAt();
		LocalDateTime now = LocalDateTime.now();
		if(date.isBefore(now.plusDays(1)) && date.isAfter(now.minusDays(1))) {
			subscribeRepo.updateUnsubscribeBook(transactionId);
			res = "unsubscribes successfully";
		}
		else
			res = "can't unsubscribe ";
		return res;
	}
	
	public List<ReaderResponse> subscribedBooks(String emailId) throws ReaderNotFoundException {
		List<SubscribeBook> listBooks = subscribeRepo.findByEmailId(emailId);
		List<ReaderResponse> resList = new ArrayList<>();
		if(listBooks != null) {
			
			Collection<Integer> list = new ArrayList<>();
			for(SubscribeBook b : listBooks)
				list.add(b.getBookId());
			List<BookList> bookList = bookrepo.findByBookIds(list);
			for(SubscribeBook subList: listBooks) {
				int bookId = subList.getBookId();
				for(BookList booklist: bookList) {
					if(booklist.getBookId() == bookId) {
						ReaderResponse res = new ReaderResponse();
						res.setBookId(bookId);
						res.setEmailId(emailId);
						res.setTransactionId(subList.getTransactionId());
						res.setUnsubscribe(subList.getUnsubscribe());
						res.setCreatedAt(subList.getCreatedAt());
						res.setBookTitle(booklist.getBookTitle());
						res.setDescription(booklist.getDescription());
						res.setBlocked(booklist.isBlocked());
						res.setPrice(booklist.getPrice());
						res.setRating(booklist.getRating());
						resList.add(res);
					}
				}
			}
			return resList;
		}
		else
			throw new ReaderNotFoundException("Email id "+ emailId +" Not found");
	}
	public List<BookList> searchBook(double title) {
		System.out.println(" book title "+title);
		List<BookList> book = bookrepo.findByRating(title);
		List<BookList> books = bookrepo.findByBookTitle("Tirukural");
		//BookMaster books = bookRepo.findByPriceAndTitle(800, title);
		System.out.println(" search book : "+books.toString() + " list " + books.size());
		return book;
	}
	
	public List<BookList> searchBooks(BookReq bookReq){
		List<BookList> listBooks = new ArrayList();
		if(bookReq.getRating() != 0.0 && bookReq.getLowPrice() != 0 && bookReq.getHighPrice() != 0 && bookReq.getTitle() != null)
			listBooks = bookrepo.findByRatingAndPriceAndBookTitle(bookReq.getRating(), bookReq.getLowPrice(), bookReq.getHighPrice(), bookReq.getTitle());
		else if(bookReq.getRating() != 0.0 && bookReq.getLowPrice() != 0 && bookReq.getHighPrice() != 0)
			listBooks = bookrepo.findByRatingAndPrice(bookReq.getRating(), bookReq.getLowPrice(), bookReq.getHighPrice());
		else if(bookReq.getRating() != 0.0 && bookReq.getTitle() != null)
			listBooks = bookrepo.findByRatingAndBookTitle(bookReq.getRating(), bookReq.getTitle());
		else if(bookReq.getTitle() != null && bookReq.getLowPrice() != 0 && bookReq.getHighPrice() != 0)
			listBooks = bookrepo.findByBookTitleAndPrice(bookReq.getTitle(), bookReq.getLowPrice(), bookReq.getHighPrice());
		else if(bookReq.getTitle() != null)
			listBooks = bookrepo.findByBookTitle(bookReq.getTitle());
		else if(bookReq.getRating() != 0.0)
			listBooks = bookrepo.findByRating(bookReq.getRating());
		else if(bookReq.getLowPrice() != 0 && bookReq.getHighPrice() != 0)
			listBooks = bookrepo.findByPrice(bookReq.getLowPrice(), bookReq.getHighPrice());
		return listBooks;
	}
	
	@KafkaListener(
			topics = "blocked-book", 
			groupId="group_id", 
			containerFactory = "userKafkaListenerFactory"
		)
	public void blockedMailMessage(int bookId) {
		System.out.println(" kafka book id "+bookId);
		List<SubscribeBook> list = subscribeRepo.findByBookId(bookId);
		if(list.isEmpty()) {
			
		} else {
			for(SubscribeBook book: list) {
				String toMail = book.getEmailId();
				sendMail(toMail);
			}
		}
	}
	
	
	private void sendMail(String receiverMail) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
		String str = "Dear reader, \n      Your subscribed book was blocked by author";
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("Blocked book details");
		mimeMessageHelper.setFrom("jamunasuresh15@gmail.com");
		mimeMessageHelper.setTo("jamunasep3@gmail.com");
		// mimeMessageHelper.setCc(null);
		mimeMessageHelper.setText(str, true);

		javaMailSender.send(mimeMessageHelper.getMimeMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String randomString() {
		String chars = "zyxwvutsrqponml9876";
		Random rnd = new Random();
		String str = "";
		StringBuilder sb = new StringBuilder(8);
		for(int i = 0; i < 8; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		str = sb.toString();
		return str;
	}
}
