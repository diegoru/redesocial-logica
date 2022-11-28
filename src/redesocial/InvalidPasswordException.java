package redesocial;

@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException {

	@Override
	public String getMessage() {
		return "\nSenha incorreta, tente fazer o login novamente.";	
	}
}