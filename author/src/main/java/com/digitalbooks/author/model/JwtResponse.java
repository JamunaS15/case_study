package com.digitalbooks.author.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private int authorId;
	
	public JwtResponse(String jwttoken, int authorId) {
		this.jwttoken = jwttoken;
		this.authorId = authorId;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
}
