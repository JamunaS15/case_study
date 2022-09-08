package com.digitalbooks.reader.readerDb.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "reader_master")
	public class ReaderMaster {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "reader_id")
		private int readerId;
		@Column(name = "reader_name")
		private String readerName;
		@Column(name = "email_id")
		private String emailId;
		private String password;
		@Column(name = "created_at")
		private Timestamp createdAt;
		public ReaderMaster() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ReaderMaster(int readerId, String readerName, String emailId, String password, Timestamp createdAt) {
			super();
			this.readerId = readerId;
			this.readerName = readerName;
			this.emailId = emailId;
			this.password = password;
			this.createdAt = createdAt;
		}
		public int getReaderId() {
			return readerId;
		}
		public void setReaderId(int readerId) {
			this.readerId = readerId;
		}
		public String getReaderName() {
			return readerName;
		}
		public void setReaderName(String readerName) {
			this.readerName = readerName;
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
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
		@Override
		public String toString() {
			return "ReaderMaster [readerId=" + readerId + ", readerName=" + readerName + ", emailId=" + emailId
					+ ", password=" + password + ", createdAt=" + createdAt + "]";
		}
}
