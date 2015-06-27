package com.hmel.myway.exceptions;

public class PhoneDictionaryException extends Exception {
  private static final long serialVersionUID = 1L;
  public PhoneDictionaryException() { super(); }
  public PhoneDictionaryException(String message) { super(message); }
  public PhoneDictionaryException(String message, Throwable cause) { super(message, cause); }
  public PhoneDictionaryException(Throwable cause) { super(cause); }

}
