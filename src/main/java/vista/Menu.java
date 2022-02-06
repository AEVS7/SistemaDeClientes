package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.Exportador;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidades.Utilidad;

/*
 * 6. Crear clase Menu en el package vistas, que debe contener los siguientes atributos:
 */
//Hint: El único método que no se debe sobreescribir es iniciarMenu, ya que contiene su
//implementación al inicio de la clase para formar el menú.

public class Menu extends Exportador implements MenuInterfaz, Rut {
	private ArrayList<Cliente> arrayDeClientes = new ArrayList<>();
	ClienteServicio clienteservicio = new ClienteServicio(arrayDeClientes);
	ArchivoServicio archivoservicio = new ArchivoServicio();

//	● archivoServicio, instancia de ArchivoServicio
	ArchivoServicio archivoServicio = new ArchivoServicio();

//	exportadorCsv, instancia de ExportarCsv.
	ExportadorCsv exportadorCsv = new ExportadorCsv();

//	● exportarTxt, instancia de ExportarTxt.
	ExportadorTxt exportarTxt = new ExportadorTxt();

//	● Definir un String fileName = “Clientes” (para exportar el archivo)
	private String fileName = "Clientes";

//	 ● Definir un String fileName1 = “DBClientes.csv” (para importar el archivo)
	private String fileName1 = "DBClientes.csv";

//	scanner, instancia de Scanner para recibir valores a través del teclado.
	protected Scanner valoresATravesDelTeclado = new Scanner(System.in);

//	iniciarMenu, muestra el menu principal y recibe la entrada del teclado a través
//	del scanner. Contiene la lógica para denotar los demás métodos en base a la
//	entrada del teclado
	private Boolean menuActivado = true;

	public final void iniciarMenu() {

		while (menuActivado) {
			System.out.println("1. Listar Clientes\r\n" + "2. Agregar Cliente\r\n" + "3. Editar Cliente\r\n"
					+ "4. Cargar Datos\r\n" + "5. Exportar Datos\r\n" + "6. Salir\r\n");

			System.out.println("Ingrese una opción:");
			String seleccion;
			seleccion = valoresATravesDelTeclado.nextLine();

			try {

				switch (seleccion) {
				case "1":
					listarClientes();
					break;
				case "2":
					agregarCliente();
					break;
				case "3":
					editarCliente();
					break;
				case "4":
					cargarDatos();
					break;
				case "5":
					exportarDatos();
					break;
				case "6":
					terminarPrograma();
					break;

				default:
					System.out.println("La Seleccion No Es Valida");
					break;

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

//	7. La clase Menu debe contener los siguientes métodos para la construcción y

	// selección del menu:
//	   ● listarCliente.
//	   ● agregarCliente.
//	   ● editarCliente.
//	   ● importarDatos.
//	   ● exportarDatos.
//	   ● terminarPrograma.
//	------------------------------------------------------------
//	8. Se deben sobreescribir los métodos nombrados previamente en el punto 7, dentro de
//	la misma clase Menu de la siguiente manera:

//	● listarClientes, muestra lista de clientes agregados, ya sea por importación o
//	agregando a mano.
	@Override
	public void listarClientes() {

//		Se deben listar a todos los clientes existentes del negocio con su respectiva categoría. En el
//		ejemplo, se muestra la lista para 2 clientes.

//		for (Cliente cliente : arrayDeClientes) {
//			Utilidad.printLine("-------------Datos del Cliente-------------\r\n\n" + "RUN del Cliente: "
//					+ cliente.getRunCliente() + "\n" + "Nombre del Cliente: " + cliente.getNombreCliente() + "\n"
//					+ "Apellido del Cliente: " + cliente.getApellidoCliente() + "\n" + "Años como Cliente: "
//					+ cliente.getAniosCliente() + "\n" + "Categoría del Cliente: " + cliente.getNombreCategoria()
//					+ "\n\n" + "-------------------------------------------\r\n" + "");
//		}
		clienteservicio.listarClientes();
	};

//	● agregarCliente, solicita ingreso de datos y llena objeto de tipo Cliente.
	@Override
	public void agregarCliente() {
//		Agregar Cliente
//		Se debe permitir agregar clientes nuevos e incorporarlos en el listado. Por defecto, deberá
//		venir la opción ”Activo” en la categoría Cliente, cuando éste sea nuevo.
		System.out.println("-------------Crear Cliente-------------");
		System.out.println("Ingresa RUN del Cliente:");
		String rut = valoresATravesDelTeclado.nextLine();
		boolean validezDelRut = validaRut(rut);
		while (!validezDelRut) {
			System.out.println("El RUN ingresado no es valido, " + "por favor ingreselo nuevamente");
			rut = valoresATravesDelTeclado.nextLine();
			validezDelRut = validaRut(rut);
		}
		;
		System.out.println("Ingresa Nombre del Cliente:");
		String nombre = valoresATravesDelTeclado.nextLine();
		System.out.println("Ingresa Apellido del Cliente:");
		String apellido = valoresATravesDelTeclado.nextLine();
		System.out.println("Ingresa años como Cliente:");
		String anios = valoresATravesDelTeclado.nextLine();
		System.out.println("\n---------------------------------------");
		clienteservicio.agregarCliente(rut, nombre, apellido, anios, CategoriaEnum.Activo);

	};

//	● editarCliente, permite la edición de algún cliente en caso de requerirlo 
//	o cambiar el estado del cliente.
//	editarCliente, permite la edición de algún cliente en caso de requerirlo o
//	cambiar el estado del cliente.
	@Override
	public void editarCliente() {
		boolean menuEditarClienteActivado = true;
		while (menuEditarClienteActivado) {
			System.out.println("-------------Editar Cliente-------------\r\n" + "Seleccione qué desea hacer:\r\n"
					+ "1.-Cambiar el estado del Cliente\r\n" + "2.-Editar los datos ingresados del Cliente\r\n"
					+ "Ingrese opcion:\r\n");
			String elecccionDeQueSeDeseaEditar = valoresATravesDelTeclado.nextLine();

			switch (elecccionDeQueSeDeseaEditar) {
			case "1":
				boolean menuEditarEstadoActivado = true;
				while (menuEditarEstadoActivado) {

					System.out.println("Ingresa RUN del Cliente:");
					String rutIngresado = valoresATravesDelTeclado.nextLine();
					boolean validezDelRut = validaRut(rutIngresado);
					while (!validezDelRut) {
						System.out.println("El RUN ingresado no es valido, " + "por favor ingreselo nuevamente");
						rutIngresado = valoresATravesDelTeclado.nextLine();
						validezDelRut = validaRut(rutIngresado);
					}
					;
					boolean menuActivarODesactivarCliente = true;
					Cliente clienteBuscado = obtenerClienteConElRut(rutIngresado);
					CategoriaEnum estado = clienteBuscado.getNombreCategoria();
					CategoriaEnum otroEstado = (obtenerClienteConElRut(rutIngresado).getNombreCategoria()
							.equals(CategoriaEnum.Inactivo)) ? CategoriaEnum.Activo : CategoriaEnum.Inactivo;
					while (menuActivarODesactivarCliente) {
						System.out.println("-----Actualizando estado del Cliente----\r\n" + "El estado actual es:"
								+ estado + "\r\n" + "1.-Si desea cambiar el estado del Cliente a " + otroEstado + "\r\n"
								+ "2.-Si desea mantener el estado del cliente " + estado + "\r\n"
								+ "Ingrese opcion:\r\n" + "----------------------------------------");
						String opcionDeActivacionDeCliente = valoresATravesDelTeclado.nextLine();
						switch (opcionDeActivacionDeCliente) {
						case "1":
							clienteBuscado.setNombreCategoria(otroEstado);
							System.out.println(
									"El estado del cliente ha sido cambiado correctamente\n---------------------------");
							return;
						case "2":
							clienteBuscado.setNombreCategoria(otroEstado);
							System.out.println(
									"Tal como ha indicado,El estado del cliente no se ha modificado\\n---------------------------");
							return;

						default:
							continue;
						}
					}

				}

				break;

			case "2":
				boolean menuEditarDatosActivado = true;
				while (menuEditarDatosActivado) {

					System.out.println("Ingresa RUN del Cliente:");
					String rutDeClienteAEditarDatos = valoresATravesDelTeclado.nextLine();
					boolean validezDelRut = validaRut(rutDeClienteAEditarDatos);
					while (!validezDelRut) {
						System.out.println("El RUN ingresado no es valido, " + "por favor ingreselo nuevamente");
						rutDeClienteAEditarDatos = valoresATravesDelTeclado.nextLine();
						validezDelRut = validaRut(rutDeClienteAEditarDatos);
					}
					;

					boolean menuActualizarDatos = true;
					while (menuActualizarDatos) {
						System.out.println("----Actualizando datos del Cliente-----\r\n" + "1.-El RUN del Cliente es: "
								+ obtenerClienteConElRut(rutDeClienteAEditarDatos).getRunCliente() + "\r\n"
								+ "2.-El Nombre del Cliente es: "
								+ obtenerClienteConElRut(rutDeClienteAEditarDatos).getNombreCliente() + "\r\n"
								+ "3.-El Apellido del Cliente es: "
								+ obtenerClienteConElRut(rutDeClienteAEditarDatos).getApellidoCliente() + "\r\n"
								+ "4.-Los años como Cliente son: "
								+ obtenerClienteConElRut(rutDeClienteAEditarDatos).getAniosCliente() + "\r\n"
								+ "Ingrese opcion a editar de los datos del cliente:\r\n"
								+ "----------------------------------------\r\n" + "");
						String datoDeQueSeDeseaEditar = valoresATravesDelTeclado.nextLine();
						switch (datoDeQueSeDeseaEditar) {
						case "1":
							System.out.println(
									"Ingrese nuevo RUN del cliente: \n--------------------------------------------");
							String datoRutAEditar = valoresATravesDelTeclado.nextLine();
							boolean validezDelRutAlEditar = validaRut(datoRutAEditar);
							while (!validezDelRutAlEditar) {
								System.out.println("El RUN ingresado no es valido, " + "por favor ingreselo nuevamente");
								datoRutAEditar = valoresATravesDelTeclado.nextLine();
								validezDelRut = validaRut(datoRutAEditar);
							}
							;
							obtenerClienteConElRut(rutDeClienteAEditarDatos).setRunCliente(datoRutAEditar);
							System.out.println(
									"-------------------------------------------\nDatos cambiados con éxito\n");
							return;
						case "2":
							System.out.println(
									"Ingrese nuevo nombre del cliente: \n--------------------------------------------");
							String datoNombreAEditar = valoresATravesDelTeclado.nextLine();
							obtenerClienteConElRut(rutDeClienteAEditarDatos).setNombreCliente(datoNombreAEditar);
							System.out.println(
									"-------------------------------------------\nDatos cambiados con éxito\n");
							return;
						case "3":
							System.out.println(
									"Ingrese nuevo apellido del cliente: \n--------------------------------------------");
							String datoApellidoAEditar = valoresATravesDelTeclado.nextLine();
							obtenerClienteConElRut(rutDeClienteAEditarDatos).setApellidoCliente(datoApellidoAEditar);
							System.out.println(
									"-------------------------------------------\nDatos cambiados con éxito\n");
							return;
						case "4":
							System.out.println(
									"Ingrese nueva cantidad de años del cliente: \n--------------------------------------------");
							String datoAniosAEditar = valoresATravesDelTeclado.nextLine();
							obtenerClienteConElRut(rutDeClienteAEditarDatos).setAniosCliente(datoAniosAEditar);
							System.out.println(
									"-------------------------------------------\nDatos cambiados con éxito\n");
							return;
						default:
							System.out.println(
									"La Opcion para editar los datos Individuales de clientes es Incorrecta,por favor intentelo nuevamente\n-----------------------");
							continue;
						}
					}
				}

				break;

			default:
				System.out.println("La Opcion ingresada es invalida, por favor intente nuevamente\n");
				break;
			}

		}
	}

//	● cargarDatos, ejecuta la carga de datos del archivo “DBClientes.csv”.
	@Override
	public void cargarDatos() {
		System.out.println("Ingresa la ruta en donde se encuentra el archivo " + "\\" + this.fileName1 + ":\n");
		String rutaDelArchivoConLosDatosQueSeQuierenImportar = valoresATravesDelTeclado.next();
		List<Cliente> array = archivoservicio
				.cargarDatos(rutaDelArchivoConLosDatosQueSeQuierenImportar + "\\" + this.fileName1);
		for (Cliente cliente : array) {
			clienteservicio.agregarCliente(cliente);

		}
	};

//	● exportarDatos, llama a método para exportar clientes en formato “.txt” o
//	“.csv”.
	@Override
	public void exportarDatos() {

		boolean menuExportarActivo = true;
		while (menuExportarActivo) {
			Utilidad.printLine("Exportar Datos-----------\r\n" + "Seleccione el formato a exportar:\r\n"
					+ "1.-Formato csv\r\n" + "2.-Formato txt\r\n" + "Ingrese una opción para exportar:\r\n"
					+ "----------------------------------");

			String eleccionDeFormatoAExportar = valoresATravesDelTeclado.nextLine();
			switch (eleccionDeFormatoAExportar) {
			case "1":
				System.out.println("Ingresa la ruta en donde desea exportar el archivo clientes.csv:\r\n");
				String rutaDeExportacionIngresadaPorElUsuario1 = valoresATravesDelTeclado.nextLine();
				exportar(rutaDeExportacionIngresadaPorElUsuario1 + "\\" + this.fileName+".csv" , this.arrayDeClientes);
				menuExportarActivo = false;
				break;

			case "2":
				System.out.println("Ingresa la ruta en donde desea exportar el archivo clientes.txt:\r\n" + "");
				String rutaDeExportacionIngresadaPorElUsuario2 = valoresATravesDelTeclado.nextLine();
				exportar(rutaDeExportacionIngresadaPorElUsuario2 + "\\" + this.fileName+".txt" , this.arrayDeClientes);
				menuExportarActivo = false;
				break;

			default:
				System.out.println("opcion De Exportar Datos-incorrecta, por favor,ingrese nuevamente la opcion");
				break;
			}

		}
	};

//	● terminarPrograma, el cual finaliza la ejecución del sistema
	
	@Override
	public void terminarPrograma() {
		menuActivado = false;
	};

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {
		// TODO Auto-generated method stub
		if (fileName.endsWith(".txt")) {
			this.exportarTxt.exportar(fileName, listaClientes);
		} else {
			this.exportadorCsv.exportar(fileName, listaClientes);
		}
	}

	@Override
	public boolean chequearRutEnLista(String runIngresado) {
		boolean valido = false;
		for (Cliente cliente : clienteservicio.getListaClientes()) {
			if (cliente.getRunCliente().equals(runIngresado)) {
				valido = true;
			}
		}
		return valido && validaRut(runIngresado);

	}

//	 Valida rut de la forma XXXXXXXX-X 
	@Override
	public Boolean validaRut(String rut) {
		Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
		Matcher matcher = pattern.matcher(rut);
		if (matcher.matches() == false)
			return false;
		String[] stringRut = rut.split("-");
		return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
	}

//	 Valida el dígito verificador
	@Override
	public String dv(String rut) {
		Integer M = 0, S = 1, T = Integer.parseInt(rut);
		for (; T != 0; T = (int) Math.floor(T /= 10))
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		return (S > 0) ? String.valueOf(S - 1) : "k";
	}

	public Cliente obtenerClienteConElRut(String rutDelCliente) {
		Cliente clienteBuscado = new Cliente("", "", "", "", CategoriaEnum.Activo);
		for (Cliente cliente : clienteservicio.getListaClientes()) {
			if (cliente.getRunCliente().equals(rutDelCliente)) {
				clienteBuscado = cliente;
			}
			;
		}
		return clienteBuscado;

	}
}
