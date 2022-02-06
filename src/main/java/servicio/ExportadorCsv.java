package servicio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;

/**
 *11. Crear una clase ExportadorCsv en el package servicio, 

 *
 */
public class ExportadorCsv extends Exportador{
//	que contenga un método
//	exportar, cuyos parámetros serán String fileName y una List<Cliente> listaClientes.
	
	@Override
	public void exportar(String fileName , List<Cliente> listaClientes){
//		Se deben realizar las implementaciones correspondientes al interior del método
//		usando PrintWriter y Filewriter para la exportación de archivos.
		
			listaClientes.forEach(elementosDeLaLista -> {
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
						writer.write(elementosDeLaLista.getRunCliente() + "," + elementosDeLaLista.getNombreCliente() + ","
								+ elementosDeLaLista.getApellidoCliente() + "," + elementosDeLaLista.getAniosCliente() + ","
								+ elementosDeLaLista.getNombreCategoria() + "\n");
						writer.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
					
				});
				System.out.println("Datos de clientes exportados correctamente ");
			}

}