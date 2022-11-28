package redesocial;

@SuppressWarnings("serial")
public class ExistLoginExcepetion extends RuntimeException {

	@Override
	public String getMessage() {
		return "\nLogin já existe, tente utilizar outro nome. Caso seja você, faça o login.";
	}
}