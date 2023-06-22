package com.Glab.LaboIntelligent.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    public ErrorObject(Integer statusCode, String message, Date timestamp) {
		super();
		this.statusCode = statusCode;
		this.message = message;
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public ErrorObject() {
		super();
	}
	private String message;
    private Date timestamp;
}
