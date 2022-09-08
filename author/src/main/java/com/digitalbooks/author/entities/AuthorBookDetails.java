package com.digitalbooks.author.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_book_details")
public class AuthorBookDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String title;
	private int price;
	private int authorId;
	private boolean blocked;
	private Timestamp updateAt;
	private String content;
	public AuthorBookDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorBookDetails(int bookId, String title, int price, int authorId, boolean blocked, Timestamp updateAt, String content) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.authorId = authorId;
		this.blocked = blocked;
		this.updateAt = updateAt;
		this.content = content;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "AuthorBookDetails [bookId=" + bookId + ", title=" + title + ", price=" + price + ", authorId="
				+ authorId + ", blocked=" + blocked + ", updatedAt=" + updateAt + "]";
	}
	
	
}
