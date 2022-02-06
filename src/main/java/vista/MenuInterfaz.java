package vista;

public interface MenuInterfaz {

	//	● listarClientes, muestra lista de clientes agregados, ya sea por importación o
	//	agregando a mano.
	void listarClientes();

	//	● agregarCliente, solicita ingreso de datos y llena objeto de tipo Cliente.
	void agregarCliente();

	//	● editarCliente, permite la edición de algún cliente en caso de requerirlo 
	//	o cambiar el estado del cliente.
	void editarCliente();

	//	● cargarDatos, ejecuta la carga de datos del archivo “DBClientes.csv”.
	void cargarDatos();

	//	● exportarDatos, llama a método para exportar clientes en formato “.txt” o
	//	“.csv”.
	void exportarDatos();

	//	● terminarPrograma, el cual finaliza la ejecución del sistema
	void terminarPrograma();

}