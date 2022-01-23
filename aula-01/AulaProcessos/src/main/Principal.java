package main;

import controller.MetodosController;

public class Principal {
	public static void main(String[] args) {
		MetodosController metodos = new MetodosController();

		System.out.println(metodos.returnSO());

		// chama um processo - necessario colocar o caminho completo do processo a ser chamado
		//metodos.callProcess("notepad");
		
		//chama e le/printa o que o processo retorna no terminal
		//metodos.readProcess("notepad");
		
		//mata um processo por nome ou PID
		//metodos.killProcess("notepad.exe");
	}
}
