package com.masai.exception;

import java.time.LocalDateTime;

public class MyErrorDetails {
	
	private LocalDateTime timestamp;
	private Integer statusCode;
	private String message;
	private String description;
	
	public MyErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	public MyErrorDetails(LocalDateTime timestamp, Integer statusCode, String message, String description) {
		super();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MyErrorDetails [timestamp=" + timestamp + ", statusCode=" + statusCode + ", message=" + message
				+ ", description=" + description + "]";
	}

	
	
	

}
