package com.digitalbooks.reader.model;

import java.time.LocalDateTime;

public class ReaderResponse {
	private String emailId;
	private int bookId;
	private String transactionId;
	private LocalDateTime createdAt;
	private boolean unsubscribe;
	private String bookTitle;
	private int price;
	private double rating;
	private String description;
	private boolean blocked;
	public ReaderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReaderResponse(String emailId, int bookId, String transactionId, LocalDateTime createdAt,
			boolean unsubscribe, String bookTitle, int price, double rating, String description, boolean blocked) {
		super();
		this.emailId = emailId;
		this.bookId = bookId;
		this.transactionId = transactionId;
		this.createdAt = createdAt;
		this.unsubscribe = unsubscribe;
		this.bookTitle = bookTitle;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.blocked = blocked;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isUnsubscribe() {
		return unsubscribe;
	}
	public void setUnsubscribe(boolean unsubscribe) {
		this.unsubscribe = unsubscribe;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	@Override
	public String toString() {
		return "ReaderResponse [emailId=" + emailId + ", bookId=" + bookId + ", transactionId=" + transactionId
				+ ", createdAt=" + createdAt + ", unsubscribe=" + unsubscribe + ", title=" + bookTitle + ", price=" + price
				+ ", rating=" + rating + ", content=" + description + ", blocked=" + blocked + "]";
	}
	
	
	
}
