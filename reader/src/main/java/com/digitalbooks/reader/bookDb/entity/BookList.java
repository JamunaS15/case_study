package com.digitalbooks.reader.bookDb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_list")
public class BookList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int bookId;
	private int price;
	private double rating;
	private String description;
	private boolean blocked;
	@Column(name = "book_title")
	private String bookTitle;
	public BookList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookList(int bookId, int price, double rating, String description, boolean blocked, String bookTitle) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.blocked = blocked;
		this.bookTitle = bookTitle;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	@Override
	public String toString() {
		return "BookList [bookId=" + bookId + ", price=" + price + ", rating=" + rating
				+ ", description=" + description + ", blocked=" + blocked + "]";
	}
	
	
}
