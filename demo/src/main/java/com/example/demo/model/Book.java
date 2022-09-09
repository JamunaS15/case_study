package com.example.demo.model;

public class Book {
	private String bookName;
	private String author;
	private int price;
	private double rating;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookName, String author, int price, double rating) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.rating = rating;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", author=" + author + ", price=" + price + ", rating=" + rating + "]";
	}
	
	
}
