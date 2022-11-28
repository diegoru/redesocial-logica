package redesocial;

@SuppressWarnings("serial")
public class EmptyStringException extends RuntimeException {

	String nomeDoAtributo;

	public EmptyStringException(String nomeDoAtributo) {
		this.nomeDoAtributo = nomeDoAtributo;
	}

	@Override
	public String getMessage() {
		return String.format("\nO campo %s precisa ser preenchido.", this.nomeDoAtributo);
	}
}