package com.example.demo.models;

public class SignupError {

	private boolean success;
	private String error;
	private String message;
	
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean b) {
		this.success = b;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
