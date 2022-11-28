package redesocial;

@SuppressWarnings("serial")
public class HasSpaceException extends RuntimeException {

	String nomeDoAtributo;

	public HasSpaceException(String nomeDoAtributo) {
		this.nomeDoAtributo = nomeDoAtributo;
	}

	@Override
	public String getMessage() {
		return String.format("\nO campo %s não pode possuir espaço.", this.nomeDoAtributo);
	}
}