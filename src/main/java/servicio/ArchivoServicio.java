package servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.CategoriaEnum;
import modelo.Cliente;

/**
 * E) Aplicar instancias al Paradigma Orientado a Objetos (POO) (2 Puntos)
 * 
 * 13. Crear la clase ArchivoServicio en el package servicio que extiende a la
 * clase Exportador. Esta contiene los siguientes requisitos:
 *
 */
public class ArchivoServicio extends Exportador {
//	Peticion en el requisito 13
//	● Crear el método exportar que será una herencia proveniente de la clase
//	Exportador, cuyos parámetros serán los mismos que se van a implementar en
//	el paso 8.
	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {

		this.exportar(fileName, listaClientes);

	}

//	● Crear el método cargarDatos que recibe por parámetro un String fileName, el
//	cual indica el nombre del archivo a cargar. Se deben realizar las
//	implementaciones correspondientes al interior del método usando FileReader
//	y BufferedReader (para lectura de archivos).

	public ArchivoServicio() {
		super();
	}

	public List<Cliente> cargarDatos(String fileName) {
//		● Crear el método cargarDatos que recibe por parámetro un String fileName, el
//		cual indica el nombre del archivo a cargar. Se deben realizar las
//		implementaciones correspondientes al interior del método usando FileReader
//		y BufferedReader (para lectura de archivos).

		List<Cliente> clientesnuevos = new ArrayList<Cliente>();
		
		try {
			
			File archivo = new File(fileName);
			FileReader lectorDeArchivo=new FileReader(archivo);
			BufferedReader lectorDeBuffer = new BufferedReader(lectorDeArchivo); 
			
			Scanner escanerDeLectorDelBuffer = new Scanner(lectorDeBuffer);
			try {
				
				while(escanerDeLectorDelBuffer.hasNext()) {
					String Linea=escanerDeLectorDelBuffer.nextLine();
					Scanner delimitar=new Scanner(Linea);
					
					delimitar.useDelimiter("\\s*,\\s*");
					Cliente clientenuevo = new Cliente("","","","",CategoriaEnum.Activo);
					clientenuevo.setRunCliente(delimitar.next());
					clientenuevo.setNombreCliente(delimitar.next());
					clientenuevo.setApellidoCliente(delimitar.next());
					clientenuevo.setAniosCliente(delimitar.next());
					clientenuevo.setNombreCategoria(CategoriaEnum.valueOf(delimitar.next()));
					clientesnuevos.add(clientenuevo);
					
					delimitar.close();
				}
				escanerDeLectorDelBuffer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error al leer las lineas del archivo");
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo con la ruta ingresada no es valido, por favor intente nuevamente");
		}
		
		return clientesnuevos;

	}
}
