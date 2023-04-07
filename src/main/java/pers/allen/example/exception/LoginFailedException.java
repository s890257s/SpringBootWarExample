package pers.allen.example.exception;

public class LoginFailedException extends Exception {

	public LoginFailedException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
