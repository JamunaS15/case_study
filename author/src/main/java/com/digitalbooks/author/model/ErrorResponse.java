package com.digitalbooks.author.model;

import java.time.LocalDateTime;

public class ErrorResponse {
	private int status;
	private String errorType;
	private String message;
	private Exception e;
	private LocalDateTime time;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(int status, String errorType, String message, Exception e, LocalDateTime time) {
		super();
		this.status = status;
		this.errorType = errorType;
		this.message = message;
		this.e = e;
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
}
