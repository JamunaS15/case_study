package com.digitalbooks.reader.readerDb.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscribe_book")
public class SubscribeBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	@Column(name= "reader_id")
	private String readerId;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "book_id")
	private int bookId;
	@Column(name = "transaction_id")
	private String transactionId;
	private int amount;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "unsubscribe")
	private Boolean unsubscribe;
	public SubscribeBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubscribeBook(int id, String readerId, String emailId, int bookId, String transactionId, int amount,
			LocalDateTime createdAt, Boolean unsubscribe) {
		super();
		this.id = id;
		this.readerId = readerId;
		this.emailId = emailId;
		this.bookId = bookId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.createdAt = createdAt;
		this.unsubscribe = unsubscribe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Boolean getUnsubscribe() {
		return unsubscribe;
	}
	public void setUnsubscribe(Boolean unsubscribe) {
		this.unsubscribe = unsubscribe;
	}
	@Override
	public String toString() {
		return "SubscribeBook [id=" + id + ", readerId=" + readerId + ", emailId=" + emailId + ", bookId=" + bookId
				+ ", transactionId=" + transactionId + ", amount=" + amount + ", createdAt=" + createdAt + "]";
	}
	

}
