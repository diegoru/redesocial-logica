package redesocial;

@SuppressWarnings("serial")
public class OnlyLetterException extends RuntimeException {

	String nomeDoAtributo;
	
	public OnlyLetterException(String nomeDoAtributo) {
		this.nomeDoAtributo = nomeDoAtributo;	
	}
	
	@Override
	public String getMessage() {
		return String.format("\nO campo %s só deve possuir letras.", this.nomeDoAtributo);
	}	
}