package com.digitalbooks.reader.bookDb.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalbooks.reader.bookDb.entity.BookList;
@Repository
public interface BooklistRepository extends JpaRepository<BookList, Integer>{
	List<BookList> findByBlocked(boolean blocked);
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update book_list b set b.blocked = true where b.book_id = :bookId")
	void updateBlockedBook(int bookId);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.rating > :rating")
	List<BookList> findByRating(double rating);
	
	@Query(value = "select * from book_list b where b.book_title like %:bookTitle%", nativeQuery = true)
	List<BookList> findByBookTitle(String bookTitle);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.price between :low and :high")
	List<BookList> findByPrice(int low, int high);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.rating > :rating and b.book_title like %:bookTitle%")
	List<BookList> findByRatingAndBookTitle(double rating, String bookTitle);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.rating > :rating and b.price between :low and :high")
	List<BookList> findByRatingAndPrice(double rating, int low, int high);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.book_title like %:bookTitle% and b.price between :low and :high")
	List<BookList> findByBookTitleAndPrice(String bookTitle, int low, int high);
	
	@Query(nativeQuery = true, value = "select * from book_list b where b.rating > :rating and b.price between :low and :high and b.book_title like %:bookTitle%")
	List<BookList> findByRatingAndPriceAndBookTitle(double rating, int low, int high, String bookTitle);
	
	@Query(value = "SELECT * FROM book_list b WHERE b.book_id IN :ids", nativeQuery = true)
	List<BookList> findByBookIds(@Param("ids") Collection<Integer> ids);
}
