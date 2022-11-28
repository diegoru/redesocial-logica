package redesocial;

@SuppressWarnings("serial")
public class LimitCharacterException extends RuntimeException {
	
	String nomeDoAtributo;
	int qtdeCharacterMin;
	
	public LimitCharacterException(String nomeDoAtributo, int qtdeCharacterMin) {
		this.nomeDoAtributo = nomeDoAtributo;
		this.qtdeCharacterMin = qtdeCharacterMin;
	}
	
	@Override
	public String getMessage() {
		return String.format("\n%s deve possuir no mínimo %d caracteres.", this.nomeDoAtributo, this.qtdeCharacterMin);	
	}	
}
