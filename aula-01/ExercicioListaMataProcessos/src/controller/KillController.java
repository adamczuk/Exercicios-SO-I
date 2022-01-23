package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}

	private String os() {
		return System.getProperty("os.name");
	}

	private void executaComando(String comando) {
		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream stream = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(stream);
			BufferedReader saida = new BufferedReader(leitor);
			String linha = saida.readLine();

			while (linha != null) {
				System.out.println(linha);
				linha = saida.readLine();
			}

			stream.close();
			leitor.close();
			saida.close();
		} catch (Exception e) {
		}

	}

	public void listaProcessos() {
		String comando = "";
		if (os().contains("Windows")) {
			comando = "TASKLIST /FO TABLE";
		} else if (os().contains("Linux")) {
			comando = "ps -ef";
		} else {
			System.err.println("SO nao suportado");
		}

		if (comando != "") {
			executaComando(comando);
		}
	}

	public void mataPid(int pid) {

		String comando = "";
		if (os().contains("Windows")) {
			comando = "TASKKILL /PID ";
		} else if (os().contains("Linux")) {
			comando = "kill -9 ";
		} else {
			System.err.println("SO nao suportado");
		}

		if (comando != "") {
			executaComando(comando + pid);
		}
	}

	public void mataNome(String nome) {
	String comando = "";
		if (os().contains("Windows")) {
			comando = "TASKKILL /IM ";
			if (!(nome.contains(".exe"))) {
				nome = nome + ".exe";
			}
		} else if (os().contains("Linux")) {
			comando = "pkill -f ";
		} else {
			System.err.println("SO nao suportado");
		}

		if (comando != "") {
			executaComando(comando + nome);
		}

	}
}
