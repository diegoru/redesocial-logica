package redesocial;

import java.util.Scanner;

public class RedeSocial {

	static Scanner entrada = new Scanner(System.in);
	static boolean logado = false;
	static boolean fechar = false;
	static int qtdePerfis = 0;
	static Perfil[] perfis = new Perfil[100];
	static Perfil perfilLogado;
	static int indexPost = -1;

	public static void main(String[] args) {
		System.out.print("\r\n"
				+ "  _____         _   ____           _   \r\n"
				+ " |_   ______  _| |_|  _ \\ ___  ___| |_ \r\n"
				+ "   | |/ _ \\ \\/ | __| |_) / _ \\/ __| __|\r\n"
				+ "   | |  __/>  <| |_|  __| (_) \\__ | |_ \r\n"
				+ "   |_|\\___/_/\\_\\\\__|_|   \\___/|___/\\__|\r\n"
				+ "\r\n");

		while (!fechar) {
			if (!logado) {
				menuPrincipal();
			} else {
				menuUsuario();
			}
		}
		entrada.close();
	}

	static void menuPrincipal() {
		System.out.print("\n-- Digite uma opção --\n\"C\" - Cadastrar-se\n\"E\" - Entrar\n\"F\" - Fechar\n");
		String opcao = entrada.nextLine().trim().toUpperCase();

		if (opcao.equals("C")) {
			cadastrar();
		} else if (opcao.equals("E")) {
			entrar();
		} else if (opcao.equals("F")) {
			System.out.println("Obrigado por nos visitar. Até logo!");
			fechar = true;
		} else {
			System.out.println("Opção inválida!");
		}
	}

	static void menuUsuario() {
		System.out.print("\n-- Digite uma opção --\n\"P\" - Postar\n\"T\" - Timeline\n\"S\" - Sair\n");
		String opcao = entrada.nextLine().trim().toUpperCase();

		if (opcao.equals("P")) {
			postar();
		} else if (opcao.equals("T")) {
			exibirTimeline();
		} else if (opcao.equals("S")) {
			logado = false;
			System.out.println("\nLogout realizado com sucesso!");
		} else {
			System.out.println("Opção inválida.");
		}
	}

	static void cadastrar() {
		try {
			System.out.println("\n-- Preencha as informações --");
			Perfil perfil = new Perfil();

			System.out.print("Digite seu nome: ");
			perfil.nome = entrada.nextLine();

			System.out.print("Login: ");
			perfil.login = entrada.nextLine();

			System.out.print("Senha: ");
			perfil.senha = entrada.nextLine();

			Validar.cadastro(perfil, perfis);
			perfil.nome = Validar.capitalize(perfil.nome);

			perfis[qtdePerfis++] = perfil;
			System.out.println("\nCadastro realizado com sucesso!");

		} catch (EmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (OnlyLetterException e) {
			System.out.println(e.getMessage());
		} catch (LimitCharacterException e) {
			System.out.println(e.getMessage());
		} catch (ExistLoginExcepetion e) {
			System.out.println(e.getMessage());
		} catch (HasSpaceException e) {
			System.out.println(e.getMessage());
		}
	}

	static void entrar() {
		try {
			System.out.print("Login: ");
			String login = entrada.nextLine();
			Validar.entrar(login, "login");
			int indexPerfil = Validar.confereLogin(perfis, login, qtdePerfis);

			System.out.print("Senha: ");
			String senha = entrada.nextLine();
			Validar.confereSenha(perfis[indexPerfil], senha);

			perfilLogado = perfis[indexPerfil];
			logado = true;
			System.out.println("\nSeja bem-vindo(a) " + perfilLogado.nome + "!");

		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
	}

	static void postar() {
		try {
			for (int i = 0; i < perfilLogado.posts.length; i++) {
				if (perfilLogado.posts[i] != null) {
					indexPost = i;
				}
			}

			Post p = new Post();

			System.out.print("Digite uma data(dd/mm/aaaa): ");
			p.data = entrada.nextLine().trim();

			System.out.print("Digite a hora(hh:mm): ");
			p.hora = entrada.nextLine().trim();

			System.out.print("Digite o conteúdo: ");
			p.post = entrada.nextLine().trim();

			Validar.postar(p);

			indexPost++;
			perfilLogado.posts[indexPost] = p;
			System.out.println("Post criado com sucesso!");
		} catch (EmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (InvalidDateException e) {
			System.out.println(e.getMessage());
		} catch (InvalidTimeException e) {
			System.out.println(e.getMessage());
		} catch (LimitCharacterException e) {
			System.out.println(e.getMessage());
		}
	}

	static void exibirTimeline() {
		System.out.println("\n-- Timeline --");
		if (indexPost > -1) {
			for (int i = 0; i <= indexPost; i++) {
				System.out.printf("%s às %s -- \"%s\"\n\n", perfilLogado.posts[i].data, perfilLogado.posts[i].hora,
						perfilLogado.posts[i].post);
			}
		} else {
			System.out.println("Nenhum post encontrado.");
		}
	}
}