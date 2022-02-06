package servicio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;

/**
 * 12. Crear una clase ExportadorTxt en el package servicio,
 *
 */
public class ExportadorTxt extends Exportador {
//	que contenga un método
//	exportar, cuyos parámetros serán String fileName y una List<Cliente> listaClientes.

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {
		listaClientes.forEach(elementosDeLaLista -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
				writer.write(elementosDeLaLista.getRunCliente() + " " + elementosDeLaLista.getNombreCliente() + " "
						+ elementosDeLaLista.getApellidoCliente() + " " + elementosDeLaLista.getAniosCliente() + " "
						+ elementosDeLaLista.getNombreCategoria() + "\n");
				writer.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		});
		System.out.println("Datos de clientes exportados correctamente ");
	}
}
