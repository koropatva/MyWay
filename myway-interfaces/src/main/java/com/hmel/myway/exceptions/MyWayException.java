package com.hmel.myway.exceptions;

public class MyWayException extends Exception {
	  private static final long serialVersionUID = 1L;
	  public MyWayException() { super(); }
	  public MyWayException(String message) { super(message); }
	  public MyWayException(String message, Throwable cause) { super(message, cause); }
	  public MyWayException(Throwable cause) { super(cause); }

	}
