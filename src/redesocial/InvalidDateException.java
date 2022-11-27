package redesocial;

@SuppressWarnings("serial")
public class InvalidDateException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Data inválida.";
	}
}