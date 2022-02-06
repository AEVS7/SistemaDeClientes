package servicio;

import java.util.List;

import modelo.Cliente;

/**
 * D) Aplicar Polimorfismo y Herencia según el Paradigma Orientado a Objetos
 * (POO) (2 Puntos)
 * 
 * 10. Crear una clase abstracta de nombre Exportador en package Servicio,
 */
public abstract class Exportador {

// que contenga
//	un método abstracto para exportar, cuyos parámetros serán String fileName y
//	List<Cliente> listaClientes.
	public abstract void exportar(String fileName, List<Cliente> listaClientes) ;
	
}




/*
 * Hint: Los pasos 8, 9 y 10 buscan tener una clase abstracta (Exportador), con un solo
método exportar, cuyas clases ExportadorCsv y ExportadorTxt extienden de esta
clase e implementan su método. Por lo tanto, los métodos declarados serán
pertenecientes a cada Exportador bajo el concepto de herencia. Al utilizarlo, se
instancia alguno de los exportadores en la clase menu ocupando polimorfismo.
 */
