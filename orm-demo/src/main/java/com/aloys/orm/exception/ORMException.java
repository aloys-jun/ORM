package com.aloys.orm.exception;

public class ORMException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	 public ORMException(){
		 super();
	 }
	 
	 public ORMException(String message) {
	     super(message);
	 }
}
