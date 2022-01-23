package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MetodosController {

	public MetodosController() {
		super();
	}

//	retorna informacoes do SO onde o processo esta executando
	public String returnSO() {

		String os = System.getProperty("os.name"); // retorna o nome do SO
		String versao = System.getProperty("os.version"); // retorna a versao do SO
		String arch = System.getProperty("os.arch"); // retorna arquitetura do SO

		return os + " " + versao + " " + arch;
	}

//	chama um processo
	public void callProcess(String process) {
		try {
//			Runtime.getRuntime().exec("cmd /c " + process); //chama um processo como admin no Windows
//			Runtime.getRuntime().exec("sudo " + process); //chama um processo como admin no Linux
			Runtime.getRuntime().exec(process); // chama um processo sem direitos de admin independente do SO
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Windows e Linux - error=2: caminho/processo nao localizado
//		Windows error=740 - requer elevacao
	}

//	le a saida de um processo em terminal
	//OBS: não permite matar porque fica lendo a saida dele até o processo morrer sozinho
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process); // recebe dados do processo
			InputStream stream = p.getInputStream();// recebe um fluxo de bits de tudo que o processo retorna no
													// terminal
			InputStreamReader leitor = new InputStreamReader(stream);// converte esse fluxo de dados para String, assim
																		// conseguimos ler
			BufferedReader saida = new BufferedReader(leitor); // necessario colocar em buffer porque pode estourar
																// String
			String linha = saida.readLine();// recebe a primeira linha do buffer, e tira ela do buffer
			while (linha != null) {
				System.out.println(linha);
				linha = saida.readLine();
			}
			
			stream.close();
			leitor.close();
			saida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	mata processo por PID ou nome
	public void killProcess(String param) {
		String kill = "";
		int pid = 0;
		
		try {
			pid = Integer.parseInt(param);
			kill = "TASKKILL /PID " + pid; //mata processo por PID no Windows
		}catch (NumberFormatException e) {
			kill = "TASKKILL /IM " + param; //mata processo por nome no Windows
		}
		
		callProcess(kill);
	}

}
