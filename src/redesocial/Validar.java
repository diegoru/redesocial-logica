package redesocial;

public class Validar {

	static void cadastro(Perfil perfil, Perfil[] perfis) {
		if (perfil.nome == null || perfil.nome.trim().isEmpty() || perfil.nome.isBlank()) {
			throw new EmptyStringException("nome");
		} else if (!tamanhoPalavra(perfil.nome, 2)) {
			throw new LimitCharacterException("Nome/sobrenome", 2);
		} else if (!somenteLetras(perfil)) {
			throw new OnlyLetterException("nome");
		}

		if (perfil.login == null || perfil.login.trim().isEmpty() || perfil.login.isBlank()) {
			throw new EmptyStringException("login");
		} else if (perfil.login.length() < 2) {
			throw new LimitCharacterException("Login", 2);
		} else if (hasSpace(perfil.login)) {
			throw new HasSpaceException("login");
		}

		if (perfil.senha == null || perfil.senha.trim().isEmpty() || perfil.senha.isBlank()) {
			throw new EmptyStringException("senha");
		} else if (perfil.senha.length() < 6) {
			throw new LimitCharacterException("Senha", 6);
		} else if (hasSpace(perfil.senha)) {
			throw new HasSpaceException("senha");
		}

		for (int i = 0; i < perfis.length; i++) {
			if (perfis[i] != null) {
				if (perfis[i].login.toLowerCase().equals(perfil.login.toLowerCase())) {
					throw new ExistLoginExcepetion();
				}
			}
		}
	}

	static void entrar(String texto, String campo) {
		if (texto == null || texto.trim().isEmpty() || texto.isBlank()) {
			throw new EmptyStringException(campo);
		}
	}

	static int confereLogin(Perfil[] perfis, String login, int qtdePerfis) {
		for (int i = 0; i < qtdePerfis; i++) {
			if (perfis[i].login.equals(login)) {
				return i;
			}
		}
		throw new UserNotFoundException();
	}

	static void confereSenha(Perfil perfil, String senha) {
		boolean confere = false;

		if (perfil.senha.equals(senha)) {
			confere = true;
		}

		if (confere == false) {
			throw new InvalidPasswordException();
		}
	}

	static void postar(Post post) {
		if (post.data == null || post.data.trim().isEmpty() || post.data.isBlank()) {
			throw new EmptyStringException("data");
		} else if (!validaData(post.data)) {
			throw new InvalidDateException();
		}

		if (post.hora == null || post.hora.trim().isEmpty() || post.hora.isBlank()) {
			throw new EmptyStringException("hora");
		} else if (!validaHora(post.hora)) {
			throw new InvalidTimeException();
		}

		if (post.post == null || post.post.trim().isEmpty() || post.post.isBlank()) {
			throw new EmptyStringException("post");
		} else if (!tamanhoPost(post.post, 4)) {
			throw new LimitCharacterException("Post", 4);
		}
	}

	static boolean tamanhoPalavra(String texto, int tamanho) {
		String[] palavras = texto.trim().split(" ");
		for (String palavra : palavras) {
			if (palavra.length() < tamanho) {
				return false;
			}
		}
		return true;
	}

	static boolean tamanhoPost(String texto, int tamanho) {
		int caracter = 0;
		for (Character c : texto.toCharArray()) {
			if (!Character.isWhitespace(c)) {
				caracter++;
			}
		}
		if (caracter < tamanho) {
			return false;
		}
		return true;
	}

	static boolean somenteLetras(Perfil perfil) {
		for (Character letra : perfil.nome.toCharArray()) {
			if (!Character.isLetter(letra) && !Character.isWhitespace(letra)) {
				return false;
			}
		}
		return true;
	}

	static boolean hasSpace(String texto) {
		for (char c : texto.toCharArray()) {
			if (c == ' ') {
				return true;
			}
		}
		return false;
	}

	static String capitalize(String texto) {
		String[] arrumaTexto = texto.trim().split(" ");
		String textoArrumado = "";

		for (int i = 0; i < arrumaTexto.length; i++) {
			textoArrumado += arrumaTexto[i].substring(0, 1).toUpperCase()
					.concat(arrumaTexto[i].substring(1).toLowerCase()) + " ";
		}
		return textoArrumado.trim();
	}

	static boolean validaData(String data) {
		try {
			int barra = 0;
			int numero = 0;
			int dia = Integer.parseInt(data.substring(0, 2));
			int mes = Integer.parseInt(data.substring(3, 5));
			int ano = Integer.parseInt(data.substring(6, 10));
			boolean bisexto = (ano % 4 == 0) && (ano % 100 != 0 || ano % 400 == 0);
			int maiorDia = 0;
			if (mes == 2) {
				if (bisexto) {
					maiorDia = 29;
				} else {
					maiorDia = 28;
				}
			} else if (mes <= 7) {
				if (mes % 2 == 0) {
					maiorDia = 30;
				} else {
					maiorDia = 31;
				}
			} else if (mes > 7) {
				if (mes % 2 == 0) {
					maiorDia = 31;
				} else {
					maiorDia = 30;
				}
			}

			boolean intervaloDia = dia <= maiorDia ? true : false;
			boolean intervaloMes = mes <= 12 ? true : false;
			boolean limit = data.length() == 10 ? true : false;

			for (Character c : data.toCharArray()) {
				if (c.equals('/')) {
					barra++;
				} else if (Character.isDigit(c)) {
					numero++;
				}
			}

			if (intervaloDia && intervaloMes && limit && barra == 2 && numero == 8) {
				if (data.substring(2, 3).equals("/") && data.substring(5, 6).equals("/")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	static boolean validaHora(String horario) {
		try {
			int doisPontos = 0;
			int numero = 0;
			boolean hora = Integer.parseInt(horario.substring(0, 2)) <= 23 ? true : false;
			boolean minuto = Integer.parseInt(horario.substring(3, 5)) <= 59 ? true : false;
			boolean limit = horario.length() == 5 ? true : false;

			for (Character c : horario.toCharArray()) {
				if (c.equals(':')) {
					doisPontos++;
				} else if (Character.isDigit(c)) {
					numero++;
				}
			}

			if (hora && minuto && limit && doisPontos == 1 && numero == 4) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}