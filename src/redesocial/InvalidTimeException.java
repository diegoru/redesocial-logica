package redesocial;

@SuppressWarnings("serial")
public class InvalidTimeException extends RuntimeException {

	@Override
	public String getMessage() {
		return "\nHora inválida.";
	}
}