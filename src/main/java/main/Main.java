package main;

import vista.Menu;

public class Main {
	
	/*
	 * 5. Crear la clase Main con método public static void en el package main, la cual usará
	una instancia para iniciar el menú.
	 */
public static void main(String[] args) {
	Menu menu=new Menu();
	menu.iniciarMenu();
}
}
