package com.digitalbooks.book.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalbooks.book.entity.BookList;
@Repository
public interface BooklistRepository extends JpaRepository<BookList, Integer>{
	List<BookList> findByBlocked(boolean blocked);
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update book_list b set b.blocked = true where b.book_id = :bookId")
	void updateBlockedBook(int bookId);
	
	//@Transactional
	@Query(nativeQuery = true, value = "select * from book_list m where m.book_title like %:title%")
	List<BookList> findByTitle(@Param("title") String title);
}
