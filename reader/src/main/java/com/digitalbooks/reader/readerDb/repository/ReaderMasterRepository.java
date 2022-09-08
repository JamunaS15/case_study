package com.digitalbooks.reader.readerDb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.reader.readerDb.entity.ReaderMaster;



@Repository
public interface ReaderMasterRepository extends JpaRepository<ReaderMaster, Integer>{
	@Query(nativeQuery = true, value = "select * from reader_master a where a.email_id = :email_id")
	ReaderMaster findByUsername(String email_id);
}
