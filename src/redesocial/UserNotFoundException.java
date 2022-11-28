package redesocial;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	@Override
	public String getMessage() {
		return "\nO login que você inseriu não está cadastrado. Tente entrar novamente ou cadastre-se.";
	}
}