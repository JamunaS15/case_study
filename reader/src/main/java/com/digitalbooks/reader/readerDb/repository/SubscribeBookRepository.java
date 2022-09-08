package com.digitalbooks.reader.readerDb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.reader.readerDb.entity.SubscribeBook;

@Repository
public interface SubscribeBookRepository extends JpaRepository<SubscribeBook, Integer>{
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update subscribe_book s set s.unsubscribe = true where s.transaction_id = :transactionId")
	void updateUnsubscribeBook(String transactionId);
	
	SubscribeBook findByTransactionId(String transactionId);
	
	@Query(nativeQuery = true, value = "select * from subscribe_book s where s.email_id = :emailId and unsubscribe = false")
	List<SubscribeBook> findByEmailId(String emailId);
	
	//@Query(nativeQuery = true, value = "select * from book_master s where s.")
	
	List<SubscribeBook> findByBookId(int bookId);
}
