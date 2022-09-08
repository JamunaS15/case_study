package com.digitalbooks.author.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="author_master")
public class AuthorMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;
	private String authorName;
	private String emailId;
	private String password;
	private Timestamp createdAt;
	public AuthorMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorMaster(int authorId, String authorName, String emailId, String password, Timestamp createdAt) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.emailId = emailId;
		this.password = password;
		this.createdAt = createdAt;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDataTime() {
		return createdAt;
	}
	public void setDataTime(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "AuthorMaster [authorId=" + authorId + ", authorName=" + authorName + ", emailId=" + emailId
				+ ", password=" + password + ", createdAt=" + createdAt + "]";
	}
	
	
}
