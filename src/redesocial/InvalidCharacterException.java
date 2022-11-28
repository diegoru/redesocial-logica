package redesocial;

@SuppressWarnings("serial")
public class InvalidCharacterException extends RuntimeException {
	
	String nomeDoAtributo;
	
	public InvalidCharacterException(String nomeDoAtributo) {
		this.nomeDoAtributo = nomeDoAtributo;
	}
	
	@Override
	public String getMessage() {	
		return String.format("\n%s só aceita letras, números, sublinhados e pontos.", this.nomeDoAtributo);
	}
}