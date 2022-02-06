package utilidades;

/**
 * 14. Crear una clase Utilidad en package utilidades, que contenga métodos reutilizables
para el menú como limpiar pantalla, mostrar mensajes, etc.
 *
 */
public class Utilidad {

	public void limpiarPantalla() {
		for (int i = 0; i <30; i++) {
			System.out.println("");
		}
	}
	
	public static void printLine(String mensaje) {
		System.out.println(mensaje);
	}
}
