package com.digitalbooks.author.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.author.entities.AuthorBookDetails;
@Repository
public interface AuthorBookDetailsRepository extends JpaRepository<AuthorBookDetails, Integer>{
	@Query(nativeQuery = true, value = "select * from author_book_details a where a.author_id = ?1")
	List<AuthorBookDetails> findByAuthorId(int authorId);
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update author_book_details a set a.blocked = true where a.book_id = :bookId and a.author_id = :authorId")
	void authorBlockBook(int bookId, int authorId);
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update author_book_details a set a.blocked = false where a.book_id = :bookId and a.author_id = :authorId")
	void authorUnblockBook(int bookId, int authorId);
	
	@Query(nativeQuery = true, value = "select * from author_book_details a where a.author_id = :authorId and a.blocked = true")
	List<AuthorBookDetails> findByAuthorIdAndBlocked(int authorId);
	
	AuthorBookDetails findByBookId(int bookId);
}
