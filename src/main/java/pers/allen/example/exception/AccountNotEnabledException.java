package pers.allen.example.exception;

public class AccountNotEnabledException extends Exception {

	public AccountNotEnabledException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
