package com.digitalbooks.author.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.author.entities.AuthorMaster;

@Repository
public interface AuthorMasterRepository extends JpaRepository<AuthorMaster, Integer>{
	@Query(nativeQuery = true, value = "select * from author_master a where a.email_id = :email_id and a.password = :password")
	AuthorMaster findByUsernameAndPassword(String email_id, String password);
	
	@Query(nativeQuery = true, value = "select * from author_master a where a.email_id = :email_id")
	AuthorMaster findByUsername(String email_id);
}
