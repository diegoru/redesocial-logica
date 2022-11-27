package redesocial;

@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Senha incorreta, tente fazer o login novamente.";	
	}
}