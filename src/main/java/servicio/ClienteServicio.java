package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.CategoriaEnum;
import modelo.Cliente;
import vista.Rut;

//C) Utilizar Arreglos y archivos (2 Puntos)
//9. Crear la clase ClienteServicio en el package servicio con los siguientes requisitos:
public class ClienteServicio implements Rut {
//	● Crear un atributo llamado List<Cliente> listaClientes.
	private List<Cliente> listaClientes = new ArrayList<Cliente>();

//	● Generar un constructor de ClienteServicio que tenga esta listaClientes como
//	una nueva ArrayList.

	public ClienteServicio(ArrayList<Cliente> listaClientes) {
		super();
		this.listaClientes = listaClientes;
	}

//	● Generar el método público sin valor de listarClientes 
	public void listarClientes() {
//		e implementar el
//		ciclo más idóneo para recorrer cada uno de los clientes
		if(this.getListaClientes().isEmpty()) {System.out.println("No existen clientes por mostrar, "
				+ "la lista se encuentra vacia.\n");}else {
		this.getListaClientes().stream()
				.forEach(cliente -> System.out.println("-------------Datos del Cliente-------------\r\n\n"
						+ "RUN del Cliente: " + cliente.getRunCliente() + "\n" + "Nombre del Cliente: "
						+ cliente.getNombreCliente() + "\n" + "Apellido del Cliente: " + cliente.getApellidoCliente()
						+ "\n" + "Años como Cliente: " + cliente.getAniosCliente() + "\n" + "Categoría del Cliente: "
						+ cliente.getNombreCategoria() + "\n\n" + "-------------------------------------------\r\n"
						+ ""));
	}}

//	● Generar un public void del método agregarCliente y pasarle los parámetros de
//	la clase Cliente. Utiliza este método para guardar clientes en una instancia de
//	cliente.

	public void agregarCliente(Cliente cliente) {
		if (!validaRut(cliente.getRunCliente())) {
			;
		} else {
			this.agregarCliente(cliente.getRunCliente(), cliente.getNombreCliente(), cliente.getApellidoCliente(),
					cliente.getAniosCliente(), cliente.getNombreCategoria());
		}
	}

	public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente,
			CategoriaEnum nombreCategoria) {
		
		Cliente name = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);

		if (chequearRutEnLista(runCliente)) {
			System.out.println("No se puede agregar el rut: "+runCliente+" ya que este existe.\n");
		} else {
			name.getNombreCategoria();
			this.getListaClientes().add(name);
			System.out.println("El cliente fue agregado Con Exito\n-------------------------------------");
		}
	};

	// ● Generar un public void del método editarCliente y pasarle los parámetros de
//	la clase Cliente.
	public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente,
			CategoriaEnum nombreCategoria) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRunCliente().equals(runCliente)) {
				agregarCliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
			} else {
				System.out.println("El run ingresado no existe , por favor , ingrese un rut valido \n");
			}
		}
	}

//	● Crear un getter de listaCliente y que pueda retornar una listaClientes.
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	@Override
	public boolean chequearRutEnLista(String runIngresado) {
		boolean valido = false;
		for (Cliente cliente : this.listaClientes) {
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
		for (Cliente cliente : this.listaClientes) {
			if (cliente.getRunCliente().equals(rutDelCliente)) {
				clienteBuscado = cliente;
			}
			;
		}
		return clienteBuscado;

	}

}
